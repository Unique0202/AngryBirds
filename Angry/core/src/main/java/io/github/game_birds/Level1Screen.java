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

public class Level1Screen {
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

        // Initialize ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        // Initialize Box2D world
        world = new World(new Vector2(0, -9.8f), true);

        // Create ground body
        createGroundBody();

        // Initialize pigs list
        pigs = new ArrayList<>();

        // Add pigs to the list
        pigs.add(new Pig(world, pigTexture, 480, 170));
        pigs.add(new Pig(world, pig2Texture, 480, 100));
        pigs.add(new Pig(world, pig2Texture, 480, 250));

        // Initialize sticks list
        sticks = new ArrayList<>();

        // Add sticks to the list
        sticks.add(new Stick(world, stickTexture, 400, 100, 15, 60));
        sticks.add(new Stick(world, stickTexture, 560, 100, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 160, 175, 15));
        sticks.add(new Stick(world, stickTexture, 420, 175, 15, 60));
        sticks.add(new Stick(world, stickTexture, 540, 175, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 235, 175, 15));
        sticks.add(new Stick(world, stickTexture, 440, 250, 15, 60));
        sticks.add(new Stick(world, stickTexture, 520, 250, 15, 60));
        sticks.add(new Stick(world, horiStickTexture, 480, 310, 175, 15));

        // Initialize birds list
        birds = new ArrayList<>();

        // Add birds to the list
        birds.add(new Bird(world, redbirdTexture, 110, 240, 30, 30));
        birds.add(new Bird(world, redbirdTexture, 40, 105, 30, 30));
        birds.add(new Bird(world, redbirdTexture, 60, 105, 30, 30));
        birds.add(new Bird(world, redbirdTexture, 80, 105, 30, 30));

        // Add other elements as actors
        addElementToStage(rock, 100, 100, 100, 100);
        addElementToStage(slingshot, 115, 180, 50, 100);
        addElementToStage(hut, 700, 100, 100, 100);
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

    public interface Level1ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
