package io.github.game_birds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Main extends ApplicationAdapter implements MenuScreen.MenuScreenListener, PlayScreen.PlayScreenListener, SettingsScreen.SettingsScreenListener, LevelSelectionScreen.LevelSelectionScreenListener, Level1Screen.Level1ScreenListener, Level2Screen.Level2ScreenListener, Level3Screen.Level3ScreenListener, PauseScreen.PauseScreenListener {
    private SpriteBatch batch;
    private MenuScreen menuScreen;
    private PlayScreen playScreen;
    private SettingsScreen settingsScreen;
    private LevelSelectionScreen levelSelectionScreen;
    private Level1Screen level1Screen;
    private Level2Screen level2Screen;
    private Level3Screen level3Screen;
    private PauseScreen pauseScreen;
    private boolean isMenuScreen = true;
    private boolean isPlayScreen = false;
    private boolean isSettingsScreen = false;
    private boolean isLevelSelectionScreen = false;
    private boolean isLevel1Screen = false;
    private boolean isLevel2Screen = false;
    private boolean isLevel3Screen = false;
    private boolean isPauseScreen = false;
    private boolean wasLevel1Screen = false;
    private boolean wasLevel2Screen = false;
    private boolean wasLevel3Screen = false;
    @Override
    public void create() {
        batch = new SpriteBatch();
        menuScreen = new MenuScreen(
        new Texture("background.jpg"),
        new Texture("playbutton.png"),
        new Rectangle((Gdx.graphics.getWidth() - 100) / 2, 30, 100, 75),
            this
        );
    }

    @Override
    public void render() {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
            batch.begin();
        if (isMenuScreen) {
            menuScreen.render(batch);
            menuScreen.update();
        } else if (isPlayScreen) {
            playScreen.render(batch);
            playScreen.update();
        } else if (isSettingsScreen) {
            settingsScreen.render(batch);
            settingsScreen.update();
        } else if (isLevelSelectionScreen) {
            levelSelectionScreen.render(batch);
            levelSelectionScreen.update();
        } else if (isLevel1Screen) {
            level1Screen.render(batch);
            level1Screen.update();
        } else if (isLevel2Screen) {
            level2Screen.render(batch);
            level2Screen.update();
        } else if (isLevel3Screen) {
            level3Screen.render(batch);
            level3Screen.update();
        } else if (isPauseScreen) {
            pauseScreen.render(batch);
            pauseScreen.update();
        }
            batch.end();


    }

    @Override
    public void dispose() {
        if (menuScreen != null) menuScreen.dispose();
        if (playScreen != null) playScreen.dispose();
        if (settingsScreen != null) settingsScreen.dispose();
        if (levelSelectionScreen != null) levelSelectionScreen.dispose();
        if (level1Screen != null) level1Screen.dispose();
        if (level2Screen != null) level2Screen.dispose();
        if (level3Screen != null) level3Screen.dispose();
        if (pauseScreen != null) pauseScreen.dispose();
    }
    @Override
    public void playButton() {
        playScreen = new PlayScreen(
            new Texture("background2.jpg"),
            new Texture("play.png"),
            new Texture("exit.png"),
            new Texture("settings.png"),
            new Texture("load.png"),
            new Rectangle(165, 100, 300, 300),
            new Circle(570, 400, 60),
            new Circle(10, 410, 55),
            new Rectangle(180, 10, 270, 220),
            this
        );
        isMenuScreen = false;
        isPlayScreen = true;
    }
    @Override
    public void settingButton() {
        settingsScreen = new SettingsScreen(
            new Texture("settings_back.png"),
            new Texture("exit.png"),
            this
        );
        isPlayScreen = false;
        isSettingsScreen = true;
    }
    @Override
    public void exitButton() {
        Gdx.app.exit();
    }
    @Override
    public void exit2Button() {
        isSettingsScreen = false;
        isPlayScreen = true;

    }
    @Override
    public void playButton2() {
        levelSelectionScreen = new LevelSelectionScreen(
            new Texture("background3.jpg"),
            new Texture("level1_texture.png"),
            new Texture("level2_texture.png"),
            new Texture("level3_texture.png"),
            new Texture("locked.png"),
            new Texture("backbutton.png"),
            new Texture("selectlevel.png"),
            new Rectangle(50, 200, 100, 100),
            new Rectangle(200, 205, 85, 100),
            new Rectangle(350, 200, 85, 100),
            new Rectangle(500, 200, 85, 100),
            new Circle(-10, 385, 100),
            new Rectangle(175, 350, 300, 100),
            this
        );
        isPlayScreen = false;
        isLevelSelectionScreen = true;
    }
    @Override
    public void backButton() {
        isLevelSelectionScreen = false;
        isPlayScreen = true;
    }
    @Override
    public void level1Button() {
        level1Screen = new Level1Screen(
            new Texture("level1_back.jpg"),
            new Texture("rock.png"),
            new Texture("slingshot.png"),
            new Texture("redbird.png"),
            new Texture("block.png"),
            new Texture("stick.png"),
            new Texture("pig.png"),
            new Texture("hut.png"),
            new Texture("pig2.png"),
            new Texture("pause.png"),
            new Circle(0, 375, 100),
            this
        );
        isLevelSelectionScreen = false;
        isLevel1Screen = true;
        wasLevel1Screen = true;
        wasLevel2Screen = false;
        wasLevel3Screen = false;
    }
    @Override
    public void level2Button() {
        level2Screen = new Level2Screen(
            new Texture("level2_back.jpg"),
            new Texture("stand.png"),
            new Texture("stick.png"),
            new Texture("pig.png"),
            new Texture("pig4.png"),
            new Texture("glass.png"),
            new Texture("rockstone.png"),
            new Texture("stone.png"),
            new Texture("redbird.png"),
            new Texture("yellowbird.png"),
            new Texture("slingshot.png"),
            new Texture("pause.png"),
            new Circle(0, 375, 100),
                this
        );
        isLevelSelectionScreen = false;
        isLevel2Screen = true;
        wasLevel1Screen = false;
        wasLevel2Screen = true;
        wasLevel3Screen = false;
    }
    @Override
    public void level3Button() {
        level3Screen = new Level3Screen(
            new Texture("level3_back.png"),
            new Texture("stand2.png"),
            new Texture("slingshot.png"),
            new Texture("redbird.png"),
            new Texture("yellowbird.png"),
            new Texture("blackbird.png"),
            new Texture("icestone.png"),
            new Texture("pig.png"),
            new Texture("wooden.png"),
            new Texture("rockstone.png"),
            new Texture("pig3.png"),
            new Texture("stick.png"),
            new Texture("stone.png"),
            new Texture("pig2.png"),
            new Texture("glass.png"),
            new Texture("pause.png"),
            new Circle(0, 375, 100),
            this
        );
        isLevelSelectionScreen = false;
        isLevel3Screen = true;
        wasLevel1Screen = false;
        wasLevel2Screen = false;
        wasLevel3Screen = true;
    }
    @Override
    public void pauseButton() {
        pauseScreen = new PauseScreen(
            new Texture("pause.jpg"),
            new Texture("backbutton.png"),
            new Texture("restart.png"),
            new Texture("resume.png"),
            new Circle(30, 140, 200),
            new Circle(265, 190, 110),
            new Circle(450, 190, 110),
            this
        );
        isLevel1Screen = false;
        isLevel2Screen = false;
        isLevel3Screen = false;
        isPauseScreen = true;
    }
    @Override
    public void back2Button() {
        isPauseScreen = false;
        isLevelSelectionScreen = true;
    }
    @Override
    public void restartButton() {
        isPauseScreen = false;
        if (wasLevel1Screen) {
            level1Button(); // Reinitialize Level 1
        } else if (wasLevel2Screen) {
            level2Button(); // Reinitialize Level 2
        } else if (wasLevel3Screen) {
            level3Button(); // Reinitialize Level 3
        }
    }
    @Override
    public void resumeButton() {
        isPauseScreen = false;
        if (wasLevel1Screen) {
            isLevel1Screen = true;
        } else if (wasLevel2Screen) {
            isLevel2Screen = true;
        } else if (wasLevel3Screen) {
            isLevel3Screen = true;
        }
    }
}
