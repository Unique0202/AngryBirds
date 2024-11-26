package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level1Screen {
    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level1ScreenListener listener;

    public Level1Screen(Texture background, Texture rock, Texture slingshot, Texture redbirdTexture, Texture block, Texture stick, Texture hori_stick, Texture pig, Texture hut, Texture pig2, Texture pause, Texture download, Level1ScreenListener listener) {
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
        addElementToStage(rock, 100, 100, 100, 100);

        addElementToStage(slingshot, 115, 180, 50, 100);
        addElementToStage(redbirdTexture, 10, 105, 30, 30);
        addElementToStage(redbirdTexture, 30, 105, 30, 30);
        addElementToStage(redbirdTexture, 50, 105, 30, 30);
        addElementToStage(redbirdTexture, 110, 240, 30, 30);
        addElementToStage(stick, 400, 100, 15, 60);
        addElementToStage(stick, 560, 100, 15, 60);
        addElementToStage(hori_stick, 400, 160, 175, 15);
        addElementToStage(stick, 400, 175, 15, 60);
        addElementToStage(stick, 560, 175, 15, 60);
        addElementToStage(hori_stick, 400, 235, 175, 15);
        addElementToStage(stick, 400, 175, 15, 60);
        addElementToStage(stick, 560, 175, 15, 60);
        addElementToStage(hori_stick, 400, 235, 175, 15);
        addElementToStage(stick, 440, 250, 15, 60);
        addElementToStage(stick, 520, 250, 15, 60);
        addElementToStage(hori_stick, 400, 310, 175, 15);
        addElementToStage(pig, 600, 100, 50, 50);
        addElementToStage(hut, 700, 100, 100, 100);
        addElementToStage(pig2, 800, 100, 50, 50);
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

    public interface Level1ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
