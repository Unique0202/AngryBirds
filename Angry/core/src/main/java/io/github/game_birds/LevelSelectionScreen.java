package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelSelectionScreen {
    private Texture background;
    private Stage stage;
    private ImageButton level1Button;
    private ImageButton level2Button;
    private ImageButton level3Button;
    private ImageButton lockedButton;
    private ImageButton backButton;
    private ImageButton selectLevelButton;
    private LevelSelectionScreenListener listener;

    public LevelSelectionScreen(Texture background, Texture level1Texture, Texture level2Texture, Texture level3Texture, Texture lockedTexture, Texture backTexture, Texture selectLevelTexture, LevelSelectionScreenListener listener) {
        this.background = background;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        level1Button = createButton(level1Texture, 0, 180, 200, 100);
        level2Button = createButton(level2Texture, 150, 185, 200, 100);
        level3Button = createButton(level3Texture, 300, 180, 200, 100);
        lockedButton = createButton(lockedTexture, 450, 180, 200, 100);
        backButton = createButton(backTexture, -10, 385, 150, 75);
        selectLevelButton = createButton(selectLevelTexture, 175, 300, 300, 150);

        level1Button.addListener(event -> {
            if (level1Button.isPressed()) {
                listener.level1Button();
                return true;
            }
            return false;
        });

        level2Button.addListener(event -> {
            if (level2Button.isPressed()) {
                listener.level2Button();
                return true;
            }
            return false;
        });

        level3Button.addListener(event -> {
            if (level3Button.isPressed()) {
                listener.level3Button();
                return true;
            }
            return false;
        });

        backButton.addListener(event -> {
            if (backButton.isPressed()) {
                listener.backButton();
                return true;
            }
            return false;
        });

        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);
        stage.addActor(lockedButton);
        stage.addActor(backButton);
        stage.addActor(selectLevelButton);
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

    public interface LevelSelectionScreenListener {
        void level1Button();
        void level2Button();
        void level3Button();
        void backButton();
    }
}
