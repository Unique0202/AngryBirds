package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class PauseScreen {
    private Texture background= new Texture("pause.jpg");
    private Texture back= new Texture("backbutton.png");
    private Texture restart= new Texture("restart.png");
    private Texture resume= new Texture("resume.png");
    private Circle backBounds=  new Circle(30, 140, 200);
    private Circle restartBounds= new Circle(265, 190, 110);
    private Circle resumeBounds= new Circle(450, 190, 110);
    private PauseScreenListener listener;
    public PauseScreen(Texture background, Texture back, Texture restart, Texture resume, Circle backBounds, Circle restartBounds, Circle resumeBounds, PauseScreenListener listener) {
        this.background = background;
        this.back = back;
        this.restart = restart;
        this.resume = resume;
        this.backBounds = backBounds;
        this.restartBounds = restartBounds;
        this.resumeBounds = resumeBounds;
        this.listener = listener;
    }
    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (backBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.back2Button();
            } else if (restartBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.restartButton();
            } else if (resumeBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.resumeButton();
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(back, backBounds.x, backBounds.y, backBounds.radius, backBounds.radius);
        batch.draw(restart, restartBounds.x, restartBounds.y, restartBounds.radius, restartBounds.radius);
        batch.draw(resume, resumeBounds.x, resumeBounds.y, resumeBounds.radius, resumeBounds.radius);
    }
    public void dispose() {
        background.dispose();
        back.dispose();
        restart.dispose();
        resume.dispose();
    }
    public interface PauseScreenListener {
        void back2Button();
        void restartButton();
        void resumeButton();
    }
}
