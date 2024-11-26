package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PlayScreen {
    private Texture background;
    private Stage stage;
    private ImageButton playButton;
    private ImageButton exitButton;
    private ImageButton settingsButton;
    private ImageButton loadButton;
    private PlayScreenListener listener;

    public PlayScreen(Texture background, Texture play, Texture exit, Texture settings, Texture load, PlayScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playButton = createButton(play, 210, 150, 200, 135);
        exitButton = createButton(exit, 515, 390, 150, 75);
        settingsButton = createButton(settings, -20, 390, 150, 75);
        loadButton = createButton(load, 210, 30, 200, 135);

        playButton.addListener(event -> {
            if (playButton.isPressed()) {
                listener.playButton2();
                return true;
            }
            return false;
        });

        exitButton.addListener(event -> {
            if (exitButton.isPressed()) {
                listener.exitButton();
                return true;
            }
            return false;
        });

        settingsButton.addListener(event -> {
            if (settingsButton.isPressed()) {
                listener.settingButton();
                return true;
            }
            return false;
        });

        loadButton.addListener(event -> {
            if (loadButton.isPressed()) {
                listener.loadButton();
                return true;
            }
            return false;
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
        stage.addActor(settingsButton);
        stage.addActor(loadButton);
    }

    private ImageButton createButton(Texture texture, float x, float y, float width, float height) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        ImageButton button = new ImageButton(drawable);
        button.setPosition(x, y);
        button.setSize(width, height);
        return button;
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
        batch.begin();
    }
    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    public interface PlayScreenListener {
        void settingButton();
        void exitButton();
        void playButton2();
        void loadButton();
    }
}
