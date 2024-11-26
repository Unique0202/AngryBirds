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
        addElementToStage(stand2, 90, 93, 110, 110);
        addElementToStage(slingshot, 124, 156, 60, 60);
        addElementToStage(redbird, 100, 156, 30, 30);
        addElementToStage(yellowbird, 90, 140, 30, 30);
        addElementToStage(blackbird, 70, 140, 30, 30);
        addElementToStage(rockstone, 400, 140, 10, 60);
        addElementToStage(icestone, 400, 200, 10, 30);
        addElementToStage(rockstone, 400, 230, 10, 30);
        addElementToStage(rockstone, 460, 140, 10, 60);
        addElementToStage(icestone, 460, 200, 10, 30);
        addElementToStage(rockstone, 460, 230, 10, 30);
        addElementToStage(rockstone, 520, 140, 10, 60);
        addElementToStage(icestone, 520, 200, 10, 30);
        addElementToStage(rockstone, 520, 230, 10, 30);
        addElementToStage(stick,400,260,140,10);
        addElementToStage(glass,400,270,50,50);
        addElementToStage(glass,400,300,50,50);
        addElementToStage(glass,475,270,50,50);
        addElementToStage(glass,475,300,50,50);
        addElementToStage(pig2,444,270,40,40);
        addElementToStage(pig,400,330,40,40);
        addElementToStage(pig,475,330,40,40);
        addElementToStage(pig2,410,140,40,40);
        addElementToStage(pig3,475,140,40,40);
        addElementToStage(pig2,530,140,30,30);


     //   addElementToStage(rockstone, 490, 30, 10, 60);

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
