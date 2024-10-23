package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class SettingsScreen {
    private Texture background= new Texture("settings_back.png");
    private Texture exit2= new Texture("exit.png");
    private Circle exitBounds= new Circle(520, 378, 80);
    private SettingsScreenListener listener;

    public SettingsScreen(Texture background, Texture exit2,SettingsScreenListener listener) {
        this.background = background;
        this.exit2 = exit2;
        this.listener = listener;
    }
    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (exitBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.exit2Button();
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(exit2, exitBounds.x, exitBounds.y, exitBounds.radius, exitBounds.radius);
    }
    public void dispose() {
        background.dispose();
        exit2.dispose();
    }
    public interface SettingsScreenListener {
        void exit2Button();
    }
}
