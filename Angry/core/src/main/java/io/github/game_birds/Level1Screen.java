package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    private Image background;
    private Image rock;
    private Image slingshot;
    private Image redbirdImage;
    private Image block;
    private Image stick;
    private Image pig;
    private Image hut;
    private Image pig2;
    private Image pause;
    private Image download;
    private Image cloud; // Added cloud image
    private int countofbirds = 2;
    private Music cloudMusic;

    private Image stickImage;
    private float stickX = 460, stickY = 150; // Stick position
    private float stickWidth = 15, stickHeight = 60; // Stick size
    private float stickRotation = 0; // Rotation angle
    private boolean isStickFalling = false; // To control animation state
    private float fallTime = 0;
    float stickRotationSpeed = 1.0f; // Timer for smooth rotation

    private Boolean isstickfalling = false;
    private Circle pauseBounds = new Circle(0, 375, 100);
    private Circle downloadBounds = new Circle(580, 410, 60);
    private Level1ScreenListener listener;
    private Stage stage;
    private Circle boundary;
    private Image draggableRedBird;
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
        this.background = new Image(background);
        this.rock = new Image(rock);
        this.slingshot = new Image(slingshot);
        this.redbirdImage = new Image(redbirdTexture);
        this.block = new Image(block);
        this.stick = new Image(stick);
        this.pig = new Image(pig);
        this.hut = new Image(hut);
        this.pig2 = new Image(pig2);
        this.pause = new Image(pause);
        this.download = new Image(download);
        this.pauseBounds = pauseBounds;
        this.downloadBounds = downloadBounds;
        this.listener = listener;
        cloudMusic = Gdx.audio.newMusic(Gdx.files.internal("cloudMusic.mp3"));

        boundary = new Circle(127, 197, 40);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        draggableRedBird = new Image(redbirdTexture);
        draggableRedBird.setSize(17, 17);
        draggableRedBird.setPosition(127 - draggableRedBird.getWidth() / 2, 197 - draggableRedBird.getHeight() / 2); // Initial position
        stage.addActor(draggableRedBird);

        blockBreakTextures = new Texture[] {
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
                countofbirds--;

                // Apply velocity based on the initial drag position
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

        // Play music only when the cloud is visible (showCloud is true)
        if (showCloud) {
            if (!cloudMusic.isPlaying()) {
                cloudMusic.play();  // Play music when cloud is visible
                cloudMusic.setLooping(false);  // Ensure the music doesn't loop
            }

            // Stop the music after 2 seconds
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    cloudMusic.stop();  // Stop the music after 2 seconds
                }
            }, 2f);
        }

        // Other update logic (e.g., touch events, bird flying, etc.)
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (pauseBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.pauseButton();
            } else if (downloadBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.downloadButton();
            }
        }

        // Logic for bird movement and interaction
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
    }

    TextureRegion stickRegion = new TextureRegion(stick.getTexture());
    public void render(SpriteBatch batch) {
        batch.draw(stickRegion, stickX, stickY, stickWidth / 2, stickHeight / 2, stickWidth, stickHeight, 1, 1, stickRotation);
        background.draw(batch, 1);
        rock.draw(batch, 1);
        slingshot.draw(batch, 1);

        // Initially draw 2 birds based on the count of birds
        if (countofbirds >= 2) {
            batch.draw(redbirdImage.getTexture(), 60, 100, 30, 30);
        }
        if (countofbirds >= 1) {
            batch.draw(redbirdImage.getTexture(), 90, 100, 30, 30);
        }

        block.draw(batch, 1);
        stick.draw(batch, 1);
        pig.draw(batch, 1);
        hut.draw(batch, 1);
        pig2.draw(batch, 1);
        pause.draw(batch, 1);
        download.draw(batch, 1);

        if (blockHit) {
            batch.draw(blockBreakAnimation.getKeyFrame(animationTime, false), blockX, blockY, blockWidth, blockHeight);
        }
    }
}

interface Level1ScreenListener {
    void pauseButton();
    void downloadButton();
}
