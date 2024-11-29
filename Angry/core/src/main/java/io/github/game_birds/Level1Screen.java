// core/src/main/java/io/github/game_birds/Level1Screen.java
package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level1Screen implements ContactListener {
    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level1ScreenListener listener;
    private ShapeRenderer shapeRenderer;
    private List<Pig> pigs;
    private World world;
    private List<Stick> sticks;
    private List<Bird> birds;
    private Body groundBody;
    private Vector2 initialBirdPosition;
    private boolean isDraggingBird;
    private float inputDelayTimer;
    private static final float INPUT_DELAY = 0.5f;
    private BitmapFont font;
    private int birdCount = 0;
    private int pigCount;
    private boolean allPigsGone;
    private RetryScreen retryScreen;

    public Level1Screen(Texture background, Texture rock, Texture slingshot, Texture redbirdTexture, Texture block, Texture stickTexture, Texture horiStickTexture, Texture pigTexture, Texture hut, Texture pig2Texture, Texture pause, Texture download, Level1ScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        pauseButton = createButton(pause, -20, 390, 150, 75);
        downloadButton = createButton(download, 535, 395, 120, 60);

        pauseButton.addListener(event -> {
            if (pauseButton.isPressed()) {
                listener.pauseButton();
                return true;
            }
            return false;
        });

        downloadButton.addListener(event -> {
            if (downloadButton.isPressed()) {
                listener.downloadButton();
                return true;
            }
            return false;
        });

        stage.addActor(pauseButton);
        stage.addActor(downloadButton);

        shapeRenderer = new ShapeRenderer();
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(this);

        createGroundBody();

        pigs = new ArrayList<>();
        pigs.add(new Pig(world, pigTexture, 460, 170, 2000));
        pigs.add(new Pig(world, pig2Texture, 460, 100, 2500));
        pigs.add(new Pig(world, pig2Texture, 460, 250, 2500));

        pigCount = pigs.size();
        allPigsGone = false;

        sticks = new ArrayList<>();
        sticks.add(new Stick(world, stickTexture, 400, 100, 15, 60));
        sticks.add(new Stick(world, stickTexture, 560, 100, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 160, 175, 15));
        sticks.add(new Stick(world, stickTexture, 420, 175, 15, 60));
        sticks.add(new Stick(world, stickTexture, 540, 175, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 235, 175, 15));
        sticks.add(new Stick(world, stickTexture, 440, 250, 15, 60));
        sticks.add(new Stick(world, stickTexture, 520, 250, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 310, 175, 15));

        birds = new ArrayList<>();
        Bird bird = new Bird(world, redbirdTexture, 130, 260, 30, 30);
        birds.add(bird);

        initialBirdPosition = new Vector2(110, 240);
        isDraggingBird = false;

        addElementToStage(rock, 100, 100, 100, 100);
        addElementToStage(slingshot, 115, 180, 50, 100);
        addElementToStage(hut, 700, 100, 100, 100);

        inputDelayTimer = INPUT_DELAY;
        font = new BitmapFont();

    }

    private void createGroundBody() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 90));
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(Gdx.graphics.getWidth(), 5.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        groundBody.createFixture(fixtureDef);
        groundBox.dispose();
    }

    private ImageButton createButton(Texture texture, float x, float y, float width, float height) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        ImageButton button = new ImageButton(drawable);
        button.setPosition(x, y);
        button.setSize(width, height);
        return button;
    }

    private void addElementToStage(Texture texture, float x, float y, float width, float height) {
        Image image = new Image(texture);
        image.setPosition(x, y);
        image.setSize(width, height);
        stage.addActor(image);
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
        world.step(1 / 60f, 6, 2);

        if (inputDelayTimer > 0) {
            inputDelayTimer -= Gdx.graphics.getDeltaTime();
        } else {
            handleInput();
        }

        Iterator<Pig> pigIterator = pigs.iterator();
        while (pigIterator.hasNext()) {
            Pig pig = pigIterator.next();
            if (pig.isDead()) {
                world.destroyBody(pig.getBody());
                pigIterator.remove();
                pigCount--;
            }
        }
        // Remove sticks that have collided 20 times
        Iterator<Stick> stickIterator = sticks.iterator();
        while (stickIterator.hasNext()) {
            Stick stick = stickIterator.next();
            if (stick.getCollisionCount() >= 20) {
                world.destroyBody(stick.getBody());
                stickIterator.remove();
            }
        }


        if (pigCount == 0) {
            listener.switchToVictoryScreen();
        } else if (birdCount == 5 && pigCount > 0) {
            listener.switchToRetryScreen();
        }
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            stage.screenToStageCoordinates(touchPos);

            Bird bird = birds.get(0);
            Vector2 birdPos = bird.getPosition();

            if (isDraggingBird || birdPos.dst(touchPos) < 50) {
                isDraggingBird = true;
                bird.setPosition(touchPos.x, touchPos.y);
                bird.setBodyType(BodyDef.BodyType.StaticBody);
            } else {
                for (Stick stick : sticks) {
                    if (stick.getBounds().contains(touchPos.x, touchPos.y)) {
                        stick.setBodyType(BodyDef.BodyType.DynamicBody);
                    }
                }
            }
        } else if (isDraggingBird) {
            isDraggingBird = false;
            Bird bird = birds.get(0);
            bird.setBodyType(BodyDef.BodyType.DynamicBody);

            Vector2 launchVelocity = new Vector2(initialBirdPosition).sub(bird.getPosition()).scl(5);
            bird.setLinearVelocity(launchVelocity);

            if (birdCount < 5) {
                new Thread(() -> {
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Bird newBird = new Bird(world, bird.getTexture(), initialBirdPosition.x, initialBirdPosition.y, bird.getWidth(), bird.getHeight());
                    birds.add(0, newBird);
                    birdCount++;
                }).start();
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 5);
        shapeRenderer.end();

        batch.begin();
        for (Pig pig : pigs) {
            pig.render(batch, font);
        }
        for (Stick stick : sticks) {
            stick.render(batch);
        }
        for (Bird bird : birds) {
            bird.render(batch);
        }
        batch.end();
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        background.dispose();
        stage.dispose();
        shapeRenderer.dispose();
        world.dispose();
        font.dispose();
        for (Pig pig : pigs) {
            pig.dispose();
        }
        for (Stick stick : sticks) {
            stick.dispose();
        }
        for (Bird bird : birds) {
            bird.dispose();
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getBody().getUserData() instanceof Pig) {
            ((Pig) fixtureA.getBody().getUserData()).handleCollision();
        } else if (fixtureB.getBody().getUserData() instanceof Pig) {
            ((Pig) fixtureB.getBody().getUserData()).handleCollision();
        }

        if (fixtureA.getBody().getUserData() instanceof Stick) {
            ((Stick) fixtureA.getBody().getUserData()).incrementCollisionCount();
        } else if (fixtureB.getBody().getUserData() instanceof Stick) {
            ((Stick) fixtureB.getBody().getUserData()).incrementCollisionCount();
        }

    }

    @Override
    public void endContact(Contact contact) {
        // No action needed
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // No action needed
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // No action needed
    }
    public int getPigCount() {
        return pigCount;
    }

    public interface Level1ScreenListener {
        void pauseButton();
        void downloadButton();
        void switchToVictoryScreen();
        void switchToRetryScreen();
        void retryButton();
    }
}
