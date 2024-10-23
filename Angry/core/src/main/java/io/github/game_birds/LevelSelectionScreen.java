package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LevelSelectionScreen {
    private Texture background= new Texture("background3.jpg");
    private Texture level1Texture= new Texture("level1_texture.png");
    private Texture level2Texture= new Texture("level2_texture.png");
    private Texture level3Texture= new Texture("level3_texture.png");
    private Texture lockedTexture= new Texture("locked.png");
    private Texture back= new Texture("backbutton.png");
    private Texture selectLevel= new Texture("selectlevel.png");
    private Rectangle level1Bounds= new Rectangle(50, 200, 100, 100);
    private Rectangle level2Bounds= new Rectangle(200, 205, 85, 100);
    private Rectangle level3Bounds= new Rectangle(350, 200, 85, 100);
    private Rectangle lockedLevelBounds= new Rectangle(500, 200, 85, 100);
    private Circle backBounds= new Circle(-10, 385, 100);
    private Rectangle selectLevelBounds= new Rectangle(175, 350, 300, 100);
    private LevelSelectionScreenListener listener;

    public LevelSelectionScreen(Texture background, Texture level1Texture, Texture level2Texture, Texture level3Texture, Texture lockedTexture, Texture back, Texture selectLevel, Rectangle level1Bounds, Rectangle level2Bounds, Rectangle level3Bounds, Rectangle lockedLevelBounds, Circle backBounds, Rectangle selectLevelBounds, LevelSelectionScreenListener listener) {
        this.background = background;
        this.level1Texture = level1Texture;
        this.level2Texture = level2Texture;
        this.level3Texture = level3Texture;
        this.lockedTexture = lockedTexture;
        this.back = back;
        this.selectLevel = selectLevel;
        this.level1Bounds = level1Bounds;
        this.level2Bounds = level2Bounds;
        this.level3Bounds = level3Bounds;
        this.lockedLevelBounds = lockedLevelBounds;
        this.backBounds = backBounds;
        this.selectLevelBounds = selectLevelBounds;
        this.listener = listener;
    }
    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (backBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.backButton();
            } else if (level1Bounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.level1Button();
            } else if (level2Bounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.level2Button();
            } else if (level3Bounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.level3Button();
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(level1Texture, level1Bounds.x, level1Bounds.y, level1Bounds.width, level1Bounds.height);
        batch.draw(level2Texture, level2Bounds.x, level2Bounds.y, level2Bounds.width, level2Bounds.height);
        batch.draw(level3Texture, level3Bounds.x, level3Bounds.y, level3Bounds.width, level3Bounds.height);
        batch.draw(lockedTexture, lockedLevelBounds.x, lockedLevelBounds.y, lockedLevelBounds.width, lockedLevelBounds.height);
        batch.draw(back, backBounds.x, backBounds.y, backBounds.radius, backBounds.radius);
        batch.draw(selectLevel, selectLevelBounds.x, selectLevelBounds.y, selectLevelBounds.width, selectLevelBounds.height);
    }
    public void dispose() {
        background.dispose();
        level1Texture.dispose();
        level2Texture.dispose();
        level3Texture.dispose();
        lockedTexture.dispose();
        back.dispose();
        selectLevel.dispose();
    }
    public interface LevelSelectionScreenListener {
        void level1Button();
        void level2Button();
        void level3Button();
        void backButton();
    }
}
