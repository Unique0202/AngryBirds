package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level3Screen {
    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level3ScreenListener listener;

    public Level3Screen(Texture background, Texture stand2, Texture slingshot, Texture redbird, Texture yellowbird, Texture blackbird, Texture icestone, Texture pig, Texture wooden, Texture rockstone, Texture pig3, Texture stick, Texture stone, Texture pig2, Texture glass, Texture pause, Texture download, Level3ScreenListener listener) {
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

        // Add other elements as actors
        addElementToStage(stand2, 100, 100, 100, 100);
        addElementToStage(slingshot, 200, 100, 50, 100);
        addElementToStage(redbird, 300, 100, 30, 30);
        addElementToStage(yellowbird, 350, 100, 30, 30);
        addElementToStage(blackbird, 400, 100, 30, 30);
        addElementToStage(icestone, 450, 100, 100, 50);
        addElementToStage(pig, 500, 100, 50, 50);
        addElementToStage(wooden, 550, 100, 100, 100);
        addElementToStage(rockstone, 600, 100, 100, 100);
        addElementToStage(pig3, 650, 100, 50, 50);
        addElementToStage(stick, 700, 100, 50, 100);
        addElementToStage(stone, 750, 100, 100, 100);
        addElementToStage(pig2, 800, 100, 50, 50);
        addElementToStage(glass, 850, 100, 100, 50);
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
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    public interface Level3ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
