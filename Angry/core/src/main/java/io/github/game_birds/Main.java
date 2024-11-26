package io.github.game_birds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

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
    private String savedLevelScreen;
    private Music backgroundMusic;

    @Override
    public void create() {
        batch = new SpriteBatch();
        menuScreen = new MenuScreen(
            new Texture("background.jpg"),
            new Texture("playbutton.png"),
            this
        );
        // Load the background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds.mp3"));
        backgroundMusic.setLooping(true);  // The music will loop
        backgroundMusic.play();            // Play the music
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        if (isMenuScreen) {
            batch.begin();
            menuScreen.render(batch);
            menuScreen.update();
            batch.end();
        } else if (isPlayScreen) {
            batch.begin();
            playScreen.render(batch);
            playScreen.update();
            batch.end();
        } else if (isSettingsScreen) {
            batch.begin();
            settingsScreen.render(batch);
            settingsScreen.update();
            batch.end();
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
        System.out.println("Play button clicked");
        playScreen = new PlayScreen(
            new Texture("background2.jpg"),
            new Texture("play.png"),
            new Texture("exit.png"),
            new Texture("settings.png"),
            new Texture("load.png"),
            this
        );
        isMenuScreen = false;
        isPlayScreen = true;
        Gdx.input.setInputProcessor(playScreen.getStage());
    }

    @Override
    public void settingButton() {
        System.out.println("Settings button clicked");
        settingsScreen = new SettingsScreen(
            new Texture("settings_back.png"),
            new Texture("exit.png"),
            this
        );
        isPlayScreen = false;
        isSettingsScreen = true;
        Gdx.input.setInputProcessor(settingsScreen.getStage());
    }

    @Override
    public void exitButton() {
        System.out.println("Game exited");
        Gdx.app.exit();
    }

    @Override
    public void exit2Button() {
        System.out.println("Settings closed");
        isPlayScreen = true;
        isSettingsScreen = false;
        Gdx.input.setInputProcessor(playScreen.getStage());
    }

    @Override
    public void playButton2() {
        System.out.println("Play clicked");
        levelSelectionScreen = new LevelSelectionScreen(
            new Texture("background3.jpg"),
            new Texture("level1_texture.png"),
            new Texture("level2_texture.png"),
            new Texture("level3_texture.png"),
            new Texture("locked.png"),
            new Texture("backbutton.png"),
            new Texture("selectlevel.png"),
            this
        );
        isPlayScreen = false;
        isLevelSelectionScreen = true;
        Gdx.input.setInputProcessor(levelSelectionScreen.getStage());
    }

    @Override
    public void backButton() {
        System.out.println("Back button clicked");
        isLevelSelectionScreen = false;
        isPlayScreen = true;
        Gdx.input.setInputProcessor(playScreen.getStage());
    }

    @Override
    public void level1Button() {
        System.out.println("Level 1 clicked");
        level1Screen = new Level1Screen(
            new Texture("level1_back.jpg"),
            new Texture("rock.png"),
            new Texture("slingshot.png"),
            new Texture("redbird.png"),
            new Texture("block.png"),
            new Texture("stick.png"),
            new Texture("hori_stick.png"),
            new Texture("pig.png"),
            new Texture("hut.png"),
            new Texture("pig2.png"),
            new Texture("pause.png"),
            new Texture("download.png"),
            this
        );
        isLevelSelectionScreen = false;
        isLevel1Screen = true;
        wasLevel1Screen = true;
        wasLevel2Screen = false;
        wasLevel3Screen = false;
        Gdx.input.setInputProcessor(level1Screen.getStage());
    }

    @Override
    public void level2Button() {
        System.out.println("Level 2 clicked");
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
            new Texture("download.png"),
            this
        );
        isLevelSelectionScreen = false;
        isLevel2Screen = true;
        wasLevel1Screen = false;
        wasLevel2Screen = true;
        wasLevel3Screen = false;
        Gdx.input.setInputProcessor(level2Screen.getStage());
    }

    @Override
    public void level3Button() {
        System.out.println("Level 3 clicked");
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
            new Texture("download.png"),
            this
        );
        isLevelSelectionScreen = false;
        isLevel3Screen = true;
        wasLevel1Screen = false;
        wasLevel2Screen = false;
        wasLevel3Screen = true;
        Gdx.input.setInputProcessor(level3Screen.getStage());
    }

    @Override
    public void pauseButton() {
        System.out.println("Pause button clicked");
        pauseScreen = new PauseScreen(
            new Texture("pause1.png"),
            new Texture("backbutton.png"),
            new Texture("restart.png"),
            new Texture("resume.png"),
            this
        );
        isLevel1Screen = false;
        isLevel2Screen = false;
        isLevel3Screen = false;
        isPauseScreen = true;
        Gdx.input.setInputProcessor(pauseScreen.getStage());
    }

    @Override
    public void back2Button() {
        System.out.println("Back button clicked");
        isPauseScreen = false;
        isLevelSelectionScreen = true;
        Gdx.input.setInputProcessor(levelSelectionScreen.getStage());
    }

    @Override
    public void restartButton() {
        System.out.println("Restart button clicked");
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
        System.out.println("Resume button clicked");
        isPauseScreen = false;
        if (wasLevel1Screen) {
            isLevel1Screen = true;
            Gdx.input.setInputProcessor(level1Screen.getStage());
        } else if (wasLevel2Screen) {
            isLevel2Screen = true;
            Gdx.input.setInputProcessor(level2Screen.getStage());
        } else if (wasLevel3Screen) {
            isLevel3Screen = true;
            Gdx.input.setInputProcessor(level3Screen.getStage());
        }
    }

    @Override
    public void downloadButton() {
        System.out.println("Save button clicked");
        if (isLevel1Screen) {
            savedLevelScreen = "Level1Screen";
            System.out.println("Level 1 saved");
        } else if (isLevel2Screen) {
            savedLevelScreen = "Level2Screen";
            System.out.println("Level 2 saved");
        } else if (isLevel3Screen) {
            savedLevelScreen = "Level3Screen";
            System.out.println("Level 3 saved");
        }
        isLevel1Screen = false;
        isLevel2Screen = false;
        isLevel3Screen = false;
        isPlayScreen = true;
        Gdx.input.setInputProcessor(playScreen.getStage());
    }

    @Override
    public void loadButton() {
        System.out.println("Load button clicked");
        if ("Level1Screen".equals(savedLevelScreen)) {
            System.out.println("Level 1 loaded");
            isPlayScreen = false;
            isLevel1Screen = true;
            Gdx.input.setInputProcessor(level1Screen.getStage());
        } else if ("Level2Screen".equals(savedLevelScreen)) {
            System.out.println("Level 2 loaded");
            isPlayScreen = false;
            isLevel2Screen = true;
            Gdx.input.setInputProcessor(level2Screen.getStage());
        } else if ("Level3Screen".equals(savedLevelScreen)) {
            System.out.println("Level 3 loaded");
            isPlayScreen = false;
            isLevel3Screen = true;
            Gdx.input.setInputProcessor(level3Screen.getStage());
        }
    }
}
