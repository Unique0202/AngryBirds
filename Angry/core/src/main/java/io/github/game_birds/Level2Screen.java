package io.github.game_birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Level2Screen {
    private Texture background= new Texture("level2_back.jpg");
    private Texture stand= new Texture("stand.png");
    private Texture stick= new Texture("stick.png");
    private Texture pig= new Texture("pig.png");
    private Texture pig4= new Texture("pig4.png");
    private Texture glass= new Texture("glass.png");
    private Texture rockstone= new Texture("rockstone.png");
    private Texture stone= new Texture("stone.png");
    private Texture redbirdTexture= new Texture("redbird.png");
    private Texture yellowbirdTexture= new Texture("yellowbird.png");
    private Texture slingshot= new Texture("slingshot.png");
    private Texture pause= new Texture("pause.png");
    private Texture download= new Texture("download.png");
    private Circle pauseBounds= new Circle(0, 375, 100);
    private Circle downloadBounds= new Circle(580, 410, 60);
    private Level2ScreenListener listener;

    public Level2Screen(Texture background, Texture stand, Texture stick, Texture pig, Texture pig4, Texture glass, Texture rockstone, Texture stone, Texture redbirdTexture, Texture yellowbirdTexture, Texture slingshot, Texture pause, Texture download, Circle pauseBounds, Circle downloadBounds, Level2ScreenListener listener) {
        this.background = background;
        this.stand = stand;
        this.stick = stick;
        this.pig = pig;
        this.pig4 = pig4;
        this.glass = glass;
        this.rockstone = rockstone;
        this.stone = stone;
        this.redbirdTexture = redbirdTexture;
        this.yellowbirdTexture = yellowbirdTexture;
        this.slingshot = slingshot;
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
        batch.draw(stand, 80, 150, 80, 60);
        batch.draw(stand, 350, 150, 300, 60);
        batch.draw(stick, 420, 170, 10, 100);
        batch.draw(stick, 435, 170, 10, 100);
        batch.draw(stick, 450, 170, 10, 100);
        batch.draw(stick, 560, 170, 10, 100);
        batch.draw(stick, 590, 170, 10, 100);
        batch.draw(pig, 460, 170, 70, 70);
        batch.draw(pig4, 500, 170, 70, 50);
        batch.draw(stick, 410, 270, 65, 10);
        batch.draw(stick, 550, 270, 65, 10);
        batch.draw(stick, 460, 280, 10, 80);
        batch.draw(stick, 560, 280, 10, 80);
        batch.draw(stick, 450, 350, 130, 10);
        batch.draw(stick, 460, 360, 10, 80);
        batch.draw(stick, 560, 360, 10, 80);
        batch.draw(glass, 470, 360, 80, 20);
        batch.draw(glass, 470, 480, 80, 20);
        batch.draw(glass, 470, 400, 80, 20);
        batch.draw(stick, 450, 420, 130, 10);
        batch.draw(rockstone, 475, 425, 40, 40);
        batch.draw(pig, 510, 425, 40, 40);
        batch.draw(stone, 420, 275, 40, 40);
        batch.draw(stone, 570, 275, 40, 40);
        batch.draw(redbirdTexture, 45, 150, 30, 30);
        batch.draw(redbirdTexture, 15, 150, 30, 30);
        batch.draw(yellowbirdTexture, 80, 170, 30, 30);
        batch.draw(slingshot, 100, 170, 50, 50);
        batch.draw(pause, pauseBounds.x, pauseBounds.y, pauseBounds.radius, pauseBounds.radius);
        batch.draw(download, downloadBounds.x, downloadBounds.y, downloadBounds.radius, downloadBounds.radius);
    }
    public void dispose() {
        background.dispose();
        stand.dispose();
        stick.dispose();
        pig.dispose();
        pig4.dispose();
        glass.dispose();
        rockstone.dispose();
        stone.dispose();
        redbirdTexture.dispose();
        yellowbirdTexture.dispose();
        slingshot.dispose();
        pause.dispose();
        download.dispose();
    }
    public interface Level2ScreenListener {
        void pauseButton();
        void downloadButton();
    }
}
