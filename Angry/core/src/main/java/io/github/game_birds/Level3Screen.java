package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Level3Screen {
    private Texture background= new Texture("level3_back.png");
    private Texture stand2= new Texture("stand2.png");
    private Texture slingshot=  new Texture("slingshot.png");
    private Texture redbirdTexture= new Texture("redbird.png");
    private Texture yellowbirdTexture= new Texture("yellowbird.png");
    private Texture blackbirdTexture= new Texture("blackbird.png");
    private Texture icestone= new Texture("icestone.png");
    private Texture pig=    new Texture("pig.png");
    private Texture wooden= new Texture("wooden.png");
    private Texture rockstone= new Texture("rockstone.png");
    private Texture pig3= new Texture("pig3.png");
    private Texture stick= new Texture("stick.png");
    private Texture stone= new Texture("stone.png");
    private Texture pig2= new Texture("pig2.png");
    private Texture glass= new Texture("glass.png");
    private Texture pause= new Texture("pause.png");
    private Texture download= new Texture("download.png");
    private Circle pauseBounds= new Circle(0, 375, 100);
    private Circle downloadBounds= new Circle(580, 410, 60);
    private Level3ScreenListener listener;

    public Level3Screen(Texture background, Texture stand2, Texture slingshot, Texture redbirdTexture, Texture yellowbirdTexture, Texture blackbirdTexture, Texture icestone, Texture pig, Texture wooden, Texture rockstone, Texture pig3, Texture stick, Texture stone, Texture pig2, Texture glass, Texture pause, Texture download, Circle pauseBounds, Circle downloadBounds, Level3ScreenListener listener) {
        this.background = background;
        this.stand2 = stand2;
        this.slingshot = slingshot;
        this.redbirdTexture = redbirdTexture;
        this.yellowbirdTexture = yellowbirdTexture;
        this.blackbirdTexture = blackbirdTexture;
        this.icestone = icestone;
        this.pig = pig;
        this.wooden = wooden;
        this.rockstone = rockstone;
        this.pig3 = pig3;
        this.stick = stick;
        this.stone = stone;
        this.pig2 = pig2;
        this.glass = glass;
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
        batch.draw(stand2, 125, 80, 100, 140);
        batch.draw(slingshot, 110, 165, 50, 50);
        batch.draw(redbirdTexture, 85, 132, 30, 30);
        batch.draw(yellowbirdTexture, 60, 132, 30, 30);
        batch.draw(blackbirdTexture, 30, 132, 30, 30);
        batch.draw(yellowbirdTexture, 0, 132, 30, 30);
        batch.draw(icestone, 420, 130, 10, 40);
        batch.draw(icestone, 440, 130, 10, 40);
        batch.draw(icestone, 420, 170, 30, 10);
        batch.draw(icestone, 455, 130, 5, 40);
        batch.draw(pig, 460, 130, 30, 50);
        batch.draw(wooden, 420, 180, 70, 10);
        batch.draw(wooden, 490, 130, 10, 60);
        batch.draw(rockstone, 500, 130, 60, 60);
        batch.draw(pig3, 560, 130, 60, 60);
        batch.draw(stick, 600, 130, 10, 60);
        batch.draw(stone, 420, 190, 40, 40);
        batch.draw(stone, 455, 190, 40, 40);
        batch.draw(stone, 440, 220, 40, 40);
        batch.draw(pig2, 535, 150, 40, 40);
        batch.draw(glass, 480, 190, 130, 20);
        batch.draw(wooden, 530, 200, 40, 40);
        batch.draw(wooden, 530, 240, 40, 40);
        batch.draw(wooden, 530, 280, 40, 40);
        batch.draw(pig2, 530, 320, 40, 40);
        batch.draw(pig2, 390, 130, 30, 30);
        batch.draw(pause, pauseBounds.x, pauseBounds.y, pauseBounds.radius, pauseBounds.radius);
        batch.draw(download, downloadBounds.x, downloadBounds.y, downloadBounds.radius, downloadBounds.radius);
    }

    public void dispose() {
        background.dispose();
        stand2.dispose();
        slingshot.dispose();
        redbirdTexture.dispose();
        yellowbirdTexture.dispose();
        blackbirdTexture.dispose();
        icestone.dispose();
        pig.dispose();
        wooden.dispose();
        rockstone.dispose();
        pig3.dispose();
        stick.dispose();
        stone.dispose();
        pig2.dispose();
        glass.dispose();
        pause.dispose();
        download.dispose();
    }
    public interface Level3ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
