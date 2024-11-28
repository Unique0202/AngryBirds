package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RetryScreen {
    private Texture retryTexture;
    private Texture backgroundTexture;
    private Stage stage;
    private ImageButton retryButton;
    private RetryScreenListener listener;

    public RetryScreen(Texture retryTexture, Texture retryButtonTexture, RetryScreenListener listener) {
        this.retryTexture = retryTexture;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        retryButton = createButton(retryButtonTexture, 220, 80, 200, 100);

        retryButton.addListener(event -> {
            if (retryButton.isPressed()) {
                listener.retryButton();
                return true;
            }
            return false;
        });

        stage.addActor(retryButton);

        // Add retry image
        Image retryImage = new Image(retryTexture);
        retryImage.setPosition(100, 200); // Set the position as needed
        retryImage.setSize(450, 150); // Set the size as needed
        stage.addActor(retryImage);

        // Load background texture
        backgroundTexture = new Texture("pause1.png");
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
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        retryTexture.dispose();
        backgroundTexture.dispose();
        stage.dispose();
    }

    public interface RetryScreenListener {
        void retryButton();
    }
}
