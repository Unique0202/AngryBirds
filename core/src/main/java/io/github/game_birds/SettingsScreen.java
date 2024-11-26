package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen {
    private Texture background;
    private Stage stage;
    private ImageButton exitButton;
    private SettingsScreenListener listener;

    public SettingsScreen(Texture background, Texture exit, SettingsScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        exitButton = createButton(exit, 480, 385, 150, 75); // Adjust width and height as needed

        exitButton.addListener(event -> {
            if (exitButton.isPressed()) {
                listener.exit2Button();
                return true;
            }
            return false;
        });

        stage.addActor(exitButton);
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

    public interface SettingsScreenListener {
        void exit2Button();
    }
}
