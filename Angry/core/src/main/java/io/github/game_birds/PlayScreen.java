package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class PlayScreen {
    private Texture background= new Texture("background2.jpg");
    private Texture play= new Texture("play.png");
    private Texture exit= new Texture("exit.png");
    private Texture settings= new Texture("settings.png");
    private Texture load= new Texture("load.png");
    private Rectangle playBounds= new Rectangle(165, 100, 300, 300);
    private Circle exitBounds= new Circle(570, 400, 60);
    private Circle settingsBounds= new Circle(10, 410, 55);
    private Rectangle loadBounds= new Rectangle(165, 200, 300, 300);
    private PlayScreenListener listener;

    public PlayScreen(Texture background, Texture play, Texture exit, Texture settings, Texture load, Rectangle playBounds, Circle exitBounds, Circle settingsBounds, Rectangle loadBounds, PlayScreenListener listener) {
        this.background = background;
        this.play = play;
        this.exit = exit;
        this.settings = settings;
        this.load = load;
        this.playBounds = playBounds;
        this.exitBounds = exitBounds;
        this.settingsBounds = settingsBounds;
        this.loadBounds = loadBounds;
        this.listener = listener;

    }
    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (settingsBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.settingButton();
            } else if (exitBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.exitButton();
            } else if (loadBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.loadButton();
            } else if (playBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.playButton2();
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(play, playBounds.x, playBounds.y, playBounds.width, playBounds.height);
        batch.draw(exit, exitBounds.x, exitBounds.y, exitBounds.radius, exitBounds.radius);
        batch.draw(settings, settingsBounds.x, settingsBounds.y, settingsBounds.radius, settingsBounds.radius);
        batch.draw(load, loadBounds.x, loadBounds.y, loadBounds.width, loadBounds.height);
    }
    public void dispose() {
        background.dispose();
        play.dispose();
        exit.dispose();
        settings.dispose();
    }
    public interface PlayScreenListener {
        void settingButton();
        void exitButton();
        void playButton2();
        void loadButton();
    }
}
