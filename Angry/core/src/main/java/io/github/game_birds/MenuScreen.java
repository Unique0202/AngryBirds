package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen {
    private Texture background= new Texture("background.jpg");
    private Texture playButton= new Texture("playbutton.png");
    private Rectangle playButtonBounds= new Rectangle((Gdx.graphics.getWidth() - 100) / 2, 30, 100, 75);
    private MenuScreenListener listener;
    public MenuScreen(Texture background, Texture playButton, Rectangle playButtonBounds, MenuScreenListener listener) {
        this.background = background;
        this.playButton = playButton;
        this.playButtonBounds = playButtonBounds;
        this.listener = listener;
    }
    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (playButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.playButton();
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playButton, playButtonBounds.x, playButtonBounds.y, playButtonBounds.width, playButtonBounds.height);
    }
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
    public interface MenuScreenListener {
        void playButton();
    }
}
