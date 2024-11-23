package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level1Screen {
    private Texture background = new Texture("level1_back.jpg");
    private Texture rock = new Texture("rock.png");
    private Texture slingshot = new Texture("slingshot.png");
    private Texture redbirdTexture = new Texture("redbird.png");
    private Texture block = new Texture("block.png");
    private Texture stick = new Texture("stick.png");
    private Texture pig = new Texture("pig.png");
    private Texture hut = new Texture("hut.png");
    private Texture pig2 = new Texture("pig2.png");
    private Texture pause = new Texture("pause.png");
    private Texture download = new Texture("download.png");
    private Texture cloud = new Texture("cloud.png"); // Added cloud texture

    private Texture stickTexture;
    private float stickX = 460, stickY = 150; // Stick position
    private float stickWidth = 15, stickHeight = 60; // Stick size
    private float stickRotation = 0; // Rotation angle
    private boolean isStickFalling = false; // To control animation state
    private float fallTime = 0;
    float stickRotationSpeed = 1.0f; //
    // Timer for smooth rotation

    private Boolean isstickfalling = false;
    private Circle pauseBounds = new Circle(0, 375, 100);
    private Circle downloadBounds = new Circle(580, 410, 60);
    private Level1ScreenListener listener;
    private Image draggableRedBird;
    private Stage stage;
    private Circle boundary;
    private Boolean isDragging = false;
    private Boolean isdragstopped = false;
    private float velocityX = 0;
    private float velocityY = 0;
    private float birdX, birdY;
    private final float gravity = -9.8f;
    private boolean isBirdFlying = false;
    private float animationTime = 0f;
    private boolean blockHit = false;
    private float blockX = 450, blockY = 100;
    private int blockWidth = 50, blockHeight = 50;
    private Texture[] blockBreakTextures;
    private Animation<TextureRegion> blockBreakAnimation;
    private boolean showCloud = false;
    private boolean removeHut = false;

    public Level1Screen(Texture background, Texture rock, Texture slingshot, Texture redbirdTexture, Texture block, Texture stick, Texture pig, Texture hut, Texture pig2, Texture pause, Texture download, Circle pauseBounds, Circle downloadBounds, Level1ScreenListener listener) {
        this.background = background;
        this.rock = rock;
        this.slingshot = slingshot;
        this.redbirdTexture = redbirdTexture;
        this.block = block;
        this.stick = stick;
        this.pig = pig;
        this.hut = hut;
        this.pig2 = pig2;
        this.pause = pause;
        this.download = download;
        this.pauseBounds = pauseBounds;
        this.downloadBounds = downloadBounds;
        this.listener = listener;

        boundary = new Circle(127, 197, 40);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        draggableRedBird = new Image(redbirdTexture);
        draggableRedBird.setSize(17, 17);
        draggableRedBird.setPosition(127 - draggableRedBird.getWidth() / 2, 197 - draggableRedBird.getHeight() / 2); // Initial position
        stage.addActor(draggableRedBird);

        blockBreakTextures = new Texture[]{
            new Texture("block1.png"),
            new Texture("block2.png"),
            new Texture("block3.png")
        };

        TextureRegion[] blockBreakFrames = new TextureRegion[blockBreakTextures.length];
        for (int i = 0; i < blockBreakTextures.length; i++) {
            blockBreakFrames[i] = new TextureRegion(blockBreakTextures[i]);
        }

        blockBreakAnimation = new Animation<TextureRegion>(0.1f, blockBreakFrames);

        draggableRedBird.addListener(new DragListener() {
            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                isDragging = true;
                isdragstopped = false;
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                float newX = draggableRedBird.getX() + x - draggableRedBird.getWidth() / 2;
                float newY = draggableRedBird.getY() + y - draggableRedBird.getHeight() / 2;

                float centerX = boundary.x;
                float centerY = boundary.y;
                float radius = boundary.radius;

                float dx = newX + draggableRedBird.getWidth() / 2 - centerX;
                float dy = newY + draggableRedBird.getHeight() / 2 - centerY;
                float distanceSquared = dx * dx + dy * dy;

                if (distanceSquared <= radius * radius) {
                    draggableRedBird.setPosition(newX, newY);
                }
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                isdragstopped = true;
                isDragging = false;

                float x1 = draggableRedBird.getX();
                float y1 = draggableRedBird.getY();
                velocityX = (127 - x1) * 3;
                velocityY = (197 - y1) * 3;

                birdX = x1;
                birdY = y1;

                isBirdFlying = true;
            }
        });
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (pauseBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.pauseButton();
            } else if (downloadBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.downloadButton();
            }
        }

        if (isBirdFlying) {
            birdX += velocityX * deltaTime;
            birdY += velocityY * deltaTime;
            velocityY += gravity * deltaTime;

            if (birdY <= 0) {
                birdY = 0;
                isBirdFlying = false;
            }
            if (birdX >= 450 && birdX <= 600 && birdY >= 130 && birdY <= 280) {
                blockHit = true;
                removeHut = true;
                isBirdFlying = false;

                // Show cloud for 2 seconds
                showCloud = true;
                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        showCloud = false;
                    }
                }, 2f);
            }
            if (birdX >= 460 && birdX <= 475 && birdY >= 150 && birdY <= 210) {
                isstickfalling = true; // Trigger stick fall
                isBirdFlying = false;
            }
        }
        if (isstickfalling) {
            if (stickRotation < 90) {
                stickRotation += stickRotationSpeed * deltaTime; // Gradually increase rotation
            } else {
                stickRotation = 90; // Cap the rotation at 90 degrees
            }
        }
    }
    TextureRegion stickRegion = new TextureRegion(stick);
    public void render(SpriteBatch batch) {
        batch.draw(stickRegion, stickX, stickY, stickWidth / 2, stickHeight / 2, stickWidth, stickHeight, 1, 1, stickRotation);        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(rock, 110, 100, 70, 70);
        batch.draw(slingshot, 110, 160, 50, 50);
        batch.draw(redbirdTexture, 60, 100, 30, 30);
        batch.draw(redbirdTexture, 90, 100, 30, 30);
        batch.draw(redbirdTexture, 30, 100, 30, 30);
        batch.draw(block, 450, 100, 50, 50);
        batch.draw(block, 500, 100, 50, 50);
        batch.draw(block, 550, 100, 50, 50);



        batch.draw(pause, pauseBounds.x, pauseBounds.y, pauseBounds.radius, pauseBounds.radius);
        batch.draw(download, downloadBounds.x, downloadBounds.y, downloadBounds.radius, downloadBounds.radius);
        if (!removeHut) {
            batch.draw(pig2, 493, 160, 60, 60);
            batch.draw(hut, 450, 130, 150, 150);
        }


        if (blockHit) {
            TextureRegion currentFrame = blockBreakAnimation.getKeyFrame(animationTime, false);
            batch.draw(currentFrame, blockX, blockY, blockWidth, blockHeight);
        } else {
            batch.draw(block, blockX, blockY, blockWidth, blockHeight);
        }

        if (showCloud) {
            batch.draw(cloud, 455, 130, 100, 100);
        }

        if (isBirdFlying) {
            batch.draw(redbirdTexture, birdX, birdY, 17, 17);
        } else if (!isDragging) {
            draggableRedBird.setPosition(127 - draggableRedBird.getWidth() / 2, 197 - draggableRedBird.getHeight() / 2);
            draggableRedBird.draw(batch, 1);
        }


        stage.draw();
    }

    public void dispose() {
        background.dispose();
        rock.dispose();
        slingshot.dispose();
        redbirdTexture.dispose();
        block.dispose();
        stick.dispose();
        pig.dispose();
        hut.dispose();
        pig2.dispose();
        pause.dispose();
        download.dispose();
        cloud.dispose();
        stage.dispose();
    }

    public interface Level1ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
