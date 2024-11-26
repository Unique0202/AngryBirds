package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level2Screen {
    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level2ScreenListener listener;

    public Level2Screen(Texture background, Texture stand, Texture stick, Texture pig, Texture pig4, Texture glass, Texture rockstone, Texture stone, Texture redbird, Texture yellowbird, Texture slingshot, Texture pause, Texture download, Level2ScreenListener listener) {
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
        addElementToStage(stand, 100, 150, 100, 50);
        addElementToStage(redbird, 105, 170, 30, 30);
        addElementToStage(yellowbird, 65, 150, 40, 40);
        addElementToStage(yellowbird, 75, 150, 40, 40);
        addElementToStage(slingshot, 145, 170, 58, 58);
        addElementToStage(stick, 400, 150, 60, 60);
        addElementToStage(stick, 500, 150, 60, 60);
        addElementToStage(stick, 400, 210, 10, 70);
        addElementToStage(stick, 415, 210, 20, 70);
        addElementToStage(stick, 440, 210, 10, 70);
        addElementToStage(stick, 395, 280, 65, 10);
        addElementToStage(stick, 395, 355, 65, 10);
        addElementToStage(stick, 400, 288, 10, 70);
        addElementToStage(stick, 445, 288, 10, 70);
        addElementToStage(rockstone, 505, 207, 10, 70);
        addElementToStage(rockstone, 543, 207, 10, 70);
        addElementToStage(rockstone, 500, 270, 68, 10);
        addElementToStage(rockstone, 505, 277, 10, 70);
        addElementToStage(rockstone, 543, 277, 10, 70);
        addElementToStage(rockstone, 505, 207, 10, 70);
        addElementToStage(rockstone, 500, 345, 68, 10);
        addElementToStage(rockstone, 505, 207, 10, 70);
        addElementToStage(stone, 450, 200, 27, 27);
        addElementToStage(stone, 475, 200, 27, 27);
        addElementToStage(pig, 465, 150, 35, 35);
        addElementToStage(pig, 407, 288, 35, 35);
        addElementToStage(pig, 510, 280, 35, 35);






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

    public interface Level2ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
