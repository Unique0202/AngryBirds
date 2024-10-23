package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Level1Screen {
    private Texture background= new Texture("level1_back.jpg");
    private Texture rock= new Texture("rock.png");
    private Texture slingshot=  new Texture("slingshot.png");
    private Texture redbirdTexture= new Texture("redbird.png");
    private Texture block= new Texture("block.png");
    private Texture stick= new Texture("stick.png");
    private Texture pig= new Texture("pig.png");
    private Texture hut= new Texture("hut.png");
    private Texture pig2= new Texture("pig2.png");
    private Texture pause= new Texture("pause.png");
    private Texture download= new Texture("download.png");
    private Circle pauseBounds= new Circle(0, 375, 100);
    private Circle downloadBounds= new Circle(580, 410, 60);
    private Level1ScreenListener listener;

    public Level1Screen(Texture background, Texture rock, Texture slingshot, Texture redbirdTexture, Texture block, Texture stick, Texture pig, Texture hut, Texture pig2, Texture pause, Texture download, Circle pauseBounds, Circle downloadBounds, Level1ScreenListener listener) {
        this.background = background;
        this.rock = rock;
        this.slingshot = slingshot;
        this.redbirdTexture = redbirdTexture;
        this.block = block;
        this.stick = stick;
        this.pig = pig;
        this.hut = hut;
        this.pig2 = pig2;
        this.pause = pause;
        this.download = download;
        this.pauseBounds = pauseBounds;
        this.downloadBounds = downloadBounds;
        this.listener = listener;
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (pauseBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.pauseButton();
            } else if (downloadBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                listener.downloadButton();
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(rock, 110, 100, 70, 70);
        batch.draw(slingshot, 110, 160, 50, 50);
        batch.draw(redbirdTexture, 60, 100, 30, 30);
        batch.draw(redbirdTexture, 90, 100, 30, 30);
        batch.draw(redbirdTexture, 30, 100, 30, 30);
        batch.draw(block, 450, 100, 50, 50);
        batch.draw(block, 500, 100, 50, 50);
        batch.draw(block, 550, 100, 50, 50);
        batch.draw(stick, 460, 150, 15, 60);
        batch.draw(stick, 520, 150, 15, 60);
        batch.draw(stick, 570, 150, 15, 60);
        batch.draw(stick, 445, 210, 155, 15);
        batch.draw(pig, 472, 148, 47, 47);
        batch.draw(pig, 535, 148, 35, 35);
        batch.draw(hut, 450, 188, 150, 150);
        batch.draw(pig2, 493, 218, 60, 60);
        batch.draw(pause, pauseBounds.x, pauseBounds.y, pauseBounds.radius, pauseBounds.radius);
        batch.draw(download, downloadBounds.x, downloadBounds.y, downloadBounds.radius, downloadBounds.radius);
    }
    public void dispose() {
        background.dispose();
        rock.dispose();
        slingshot.dispose();
        redbirdTexture.dispose();
        block.dispose();
        stick.dispose();
        pig.dispose();
        hut.dispose();
        pig2.dispose();
        pause.dispose();
        download.dispose();
    }
    public interface Level1ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
