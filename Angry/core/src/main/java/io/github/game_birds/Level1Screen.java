package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

public class Level1Screen {
    private static final float BIRD_MOVE_DURATION = 5.0f; // Duration each bird moves

    private Texture background;
    private Stage stage;
    private ImageButton pauseButton;
    private ImageButton downloadButton;
    private Level1ScreenListener listener;
    private List<Image> redbirds;
    private Vector2[] initialPositions;
    private Vector2 velocity;
    private boolean isDragging;
    private boolean isReleased;
    private float[] times;
    private int currentBirdIndex;

    public Level1Screen(Texture background, Texture rock, Texture slingshot, Texture redbirdTexture, Texture block, Texture stick, Texture hori_stick, Texture pig, Texture hut, Texture pig2, Texture pause, Texture download, Level1ScreenListener listener) {
        this.background = background;
        this.listener = listener;
        this.velocity = new Vector2();
        this.isDragging = false;
        this.isReleased = false;
        this.times = new float[4];
        this.currentBirdIndex = 0;

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

        // Initialize red birds and their positions
        redbirds = new ArrayList<>();
        initialPositions = new Vector2[]{
                new Vector2(110, 240),
                new Vector2(50, 105),
                new Vector2(30, 105),
                new Vector2(10, 105)
        };

        for (Vector2 position : initialPositions) {
            final Image redbird = new Image(redbirdTexture);
            redbird.setPosition(position.x, position.y);
            redbird.setSize(30, 30);
            redbird.addListener(new DragListener() {
                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    if (redbirds.indexOf(redbird) == currentBirdIndex) {
                        isDragging = true;
                        redbird.setPosition(redbird.getX() + x - redbird.getWidth() / 2, redbird.getY() + y - redbird.getHeight() / 2);
                    }
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    if (redbirds.indexOf(redbird) == currentBirdIndex) {
                        isDragging = false;
                        isReleased = true;
                        velocity.set((initialPositions[currentBirdIndex].x - redbird.getX()) * 5, (initialPositions[currentBirdIndex].y - redbird.getY()) * 5);
                        times[currentBirdIndex] = 0;
                    }
                }
            });
            redbirds.add(redbird);
            stage.addActor(redbird);
        }
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
        if (isReleased) {
            times[currentBirdIndex] += Gdx.graphics.getDeltaTime();
            float newX = initialPositions[currentBirdIndex].x + velocity.x * times[currentBirdIndex];
            float newY = initialPositions[currentBirdIndex].y + velocity.y * times[currentBirdIndex] - 0.5f * 9.8f * times[currentBirdIndex] * times[currentBirdIndex];
            redbirds.get(currentBirdIndex).setPosition(newX, newY);
            if (times[currentBirdIndex] >= BIRD_MOVE_DURATION || newY < 0) {
                isReleased = false;
                redbirds.get(currentBirdIndex).setPosition(initialPositions[currentBirdIndex].x, initialPositions[currentBirdIndex].y);
                currentBirdIndex++;
                if (currentBirdIndex < redbirds.size()) {
                    for (int i = currentBirdIndex; i < redbirds.size(); i++) {
                        redbirds.get(i).setPosition(initialPositions[i - 1].x, initialPositions[i - 1].y);
                    }
                }
            }
        }
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
