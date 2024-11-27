package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import java.util.List;

public class Level2Screen {
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

        // Initialize ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        // Initialize Box2D world
        world = new World(new Vector2(0, -9.8f), true);

        // Create ground body
        createGroundBody();

        // Initialize pigs list
        pigs = new ArrayList<>();

        // Add pigs to the list
        pigs.add(new Pig(world, pig, 465, 150));
        pigs.add(new Pig(world, pig4, 407, 288));
        pigs.add(new Pig(world, pig4, 507, 280));

        // Initialize sticks list
        sticks = new ArrayList<>();

        // Add sticks to the list
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
        sticks.add(new Stick(world, rockstone, 505, 277, 10, 70));
        sticks.add(new Stick(world, rockstone, 543, 277, 10, 70));
        sticks.add(new Stick(world, rockstone, 525, 345, 98, 10));

        // Initialize birds list
        birds = new ArrayList<>();

        // Add birds to the list
        birds.add(new Bird(world, redbirdTexture, 105, 170, 30, 30));
        birds.add(new Bird(world, yellowbirdTexture, 65, 150, 40, 40));
        birds.add(new Bird(world, yellowbirdTexture, 75, 150, 40, 40));

        // Add other elements as actors
        addElementToStage(stand, 100, 150, 100, 50);
        addElementToStage(slingshot, 145, 170, 58, 58);
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
        world.step(1/60f, 6, 2);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();

        // Draw the ground line
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1); // Black color
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 5); // Draw a rectangle as a line
        shapeRenderer.end();

        // Render pigs
        batch.begin();
        for (Pig pig : pigs) {
            pig.render(batch);
        }
        // Render sticks
        for (Stick stick : sticks) {
            stick.render(batch);
        }
        // Render birds
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

    public interface Level2ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
