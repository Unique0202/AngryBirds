package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class VictoryScreen {
    private Texture background;
    private Texture levelUpTexture;
    private Texture victoryTexture;
    private Stage stage;
    private ImageButton backButton;
    private VictoryScreenListener listener;

    public VictoryScreen(Texture background, Texture backTexture, Texture levelUpTexture, Texture victoryTexture, VictoryScreenListener listener) {
        this.background = background;
        this.levelUpTexture = levelUpTexture;
        this.victoryTexture = victoryTexture;
        this.listener = listener;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backButton = createButton(backTexture, 240, 80, 150, 75);

        backButton.addListener(event -> {
            if (backButton.isPressed()) {
                listener.backButton();
                return true;
            }
            return false;
        });

        stage.addActor(backButton);

        // Add level up image
        Image levelUpImage = new Image(levelUpTexture);
        levelUpImage.setPosition(170, 170);// Set the position as needed
        levelUpImage.setSize(300, 80); // Set the size as needed
        stage.addActor(levelUpImage);

        // Add victory image
        Image victoryImage = new Image(victoryTexture);
        victoryImage.setPosition(75, 270); // Set the position as needed
        victoryImage.setSize(500, 120); // Set the size as needed
        stage.addActor(victoryImage);
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
        levelUpTexture.dispose();
        victoryTexture.dispose();
        stage.dispose();
    }

    public interface VictoryScreenListener {
        void backButton();
    }
}
