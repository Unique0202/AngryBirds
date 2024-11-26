package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen {
    private Texture background;
    private Stage stage;
    private ImageButton playButton;
    private MenuScreenListener listener;

    public MenuScreen(Texture background, Texture playButtonTexture, MenuScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        if (playButtonTexture != null) {
            TextureRegionDrawable playDrawable = new TextureRegionDrawable(playButtonTexture);
            playButton = new ImageButton(playDrawable);

            // Set the size of the play button
            playButton.setSize(200, 100); // Adjust width and height as needed

            // Adjust the position of the play button
            playButton.setPosition((Gdx.graphics.getWidth() - playButton.getWidth()) / 2, 30);

            playButton.addListener(event -> {
                if (playButton.isPressed()) {
                    listener.playButton();
                    return true;
                }
                return false;
            });

            stage.addActor(playButton);
        } else {
            Gdx.app.error("MenuScreen", "Play button texture is null");
        }
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

    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    public interface MenuScreenListener {
        void playButton();
    }
}
