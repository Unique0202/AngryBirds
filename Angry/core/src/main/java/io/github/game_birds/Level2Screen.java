// core/src/main/java/io/github/game_birds/Level2Screen.java
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

public class Level2Screen implements ContactListener {
    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level2ScreenListener listener;
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
    private int birdCount = 1;
    private int pigCount;
    private boolean allPigsGone;

    public Level2Screen(Texture background, Texture stand, Texture stick, Texture pig, Texture pig4, Texture glass, Texture rockstone, Texture stone, Texture redbirdTexture, Texture yellowbirdTexture, Texture slingshot, Texture pause, Texture download, Level2ScreenListener listener) {
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
        pigs.add(new Pig(world, pig, 465, 150, 2000));
        pigs.add(new Pig(world, pig4, 407, 288, 3000));
        pigs.add(new Pig(world, pig4, 507, 280, 3000));

        pigCount = pigs.size();
        allPigsGone = false;

        sticks = new ArrayList<>();
        sticks.add(new Stick(world, stick, 420, 150, 60, 60));
        sticks.add(new Stick(world, stick, 520, 150, 60, 60));
        sticks.add(new Stick(world, stick, 400, 210, 10, 70));
        sticks.add(new Stick(world, stick, 415, 210, 20, 70));
        sticks.add(new Stick(world, stick, 440, 210, 10, 70));
        sticks.add(new Stick(world, stick, 415, 280, 75, 10));
        sticks.add(new Stick(world, stick, 415, 355, 75, 10));
        sticks.add(new Stick(world, stick, 390, 320, 10, 70));
        sticks.add(new Stick(world, stick, 440, 320, 10, 70));
        sticks.add(new Stick(world, rockstone, 500, 207, 10, 70));
        sticks.add(new Stick(world, rockstone, 543, 207, 10, 70));
        sticks.add(new Stick(world, rockstone, 520, 210, 20, 70));
        sticks.add(new Stick(world, rockstone, 525, 270, 98, 10));
        sticks.add(new Stick(world, rockstone, 505, 277, 15, 70));
        sticks.add(new Stick(world, rockstone, 543, 277, 15, 70));
        sticks.add(new Stick(world, rockstone, 525, 345, 108, 10));

        birds = new ArrayList<>();
        Bird bird = new Bird(world, redbirdTexture, 130, 260, 30, 30);
        birds.add(bird);

        initialBirdPosition = new Vector2(110, 240);
        isDraggingBird = false;

        addElementToStage(stand, 100, 150, 100, 50);
        addElementToStage(slingshot, 145, 170, 58, 58);

        inputDelayTimer = INPUT_DELAY;
        font = new BitmapFont();
    }

    private void createGroundBody() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 140));
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(Gdx.graphics.getWidth(), 5.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;

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

        if (pigCount == 0) {
            listener.switchToVictoryScreen();
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
                        Thread.sleep(10000);
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

    public interface Level2ScreenListener {
        void pauseButton();
        void downloadButton();
        void switchToVictoryScreen();
    }
}
