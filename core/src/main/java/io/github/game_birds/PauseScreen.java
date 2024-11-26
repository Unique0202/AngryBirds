package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen {
    private Texture background;
    private Stage stage;
    private ImageButton backButton;
    private ImageButton restartButton;
    private ImageButton resumeButton;
    private PauseScreenListener listener;

    public PauseScreen(Texture background, Texture back, Texture restart, Texture resume, PauseScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backButton = createButton(back, 30, 190, 200, 100);
        restartButton = createButton(restart, 215, 185, 200, 120);
        resumeButton = createButton(resume, 400, 185, 200, 120);

        backButton.addListener(event -> {
            if (backButton.isPressed()) {
                listener.back2Button();
                return true;
            }
            return false;
        });

        restartButton.addListener(event -> {
            if (restartButton.isPressed()) {
                listener.restartButton();
                return true;
            }
            return false;
        });

        resumeButton.addListener(event -> {
            if (resumeButton.isPressed()) {
                listener.resumeButton();
                return true;
            }
            return false;
        });

        stage.addActor(backButton);
        stage.addActor(restartButton);
        stage.addActor(resumeButton);
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
        batch.draw(background, 0, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200);
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

    public interface PauseScreenListener {
        void back2Button();
        void restartButton();
        void resumeButton();
    }
}
