package io.github.game_birds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Texture playButton;
    private Texture background2;
    private Texture background3;
    private Texture settings;
    private Texture settings_back;
    private int currentLevelNumber;
    private int fix;
    private Texture level1Texture;
    private Texture level2Texture;
    private Texture level3Texture;
    private Texture lockedTexture;
    private Texture selectlevel;
    private Texture redbirdTexture;
    private Texture yellowbirdTexture;
    private Texture blackbirdTexture;
    private Texture choosebird;
    private Texture slingshot ;
    private Texture rock ;
    private Texture stick;
    private Texture pig ;
    private Texture hut ;
    private Texture pig2;
    private Texture stand ;
    private Texture pig3;
    private Texture pig4;
    private Texture glass ;
    private Texture triangle;
    private Texture rockstone ;
    private Texture stone;
    private Texture stand2;
    private Texture icestone ;
    private Texture wooden;
    private Texture block ;
    private Texture pause;
    private Texture restart;
    private Texture resume;
    private Texture exit2;
    // New background textures for each level
    private Texture t1;
    private Texture t2;
    private Texture t3;
    private Texture currentLevelTexture; // Holds the current background texture for the selected level
    private Texture play;
    private Texture exit;
    private Texture back;
    private Texture back2;
    private Texture pauseScreen;
    private Rectangle level1;
    private Rectangle level2;
    private Rectangle level3;
    private Rectangle lockedLevel;
    private Rectangle Play;
    private Rectangle selectLevel;
    private Circle circleRedBird;
    private Circle circleYellowBird;
    private Circle circleBlackBird;
    private Circle circleslingshot ;
    private Circle circleRock ;
    private Circle Exit;
    private Circle Back;
    private Circle Back2;
    private Circle Pause;
    private Circle Restart;
    private Circle Resume;
    private Circle Settings;
    private Circle Exit2;
    private Rectangle playButtonBounds;
    private boolean isGameStarted = false;
    private boolean isLevelSelected = false;
    private boolean isBirdSelected = false;
    private boolean isPlaySelected = false;
    private String selectedBird = null;
    private boolean isPauseSelected = false;
    private boolean isBack2Selected = false;
    private boolean isRestartSelected = false;
    private boolean isResumeSelected = false;
    private boolean isSettingSelected = false;
    private boolean isExit2Selected = false;
    private Music backgroundMusic;


    @Override
    public void create() {
        batch = new SpriteBatch();

        // Loading textures
        background = new Texture("background.jpg");
        playButton = new Texture("playbutton.png");
        background2 = new Texture("background2.jpg");
        background3 = new Texture("background3.jpg");
        redbirdTexture = new Texture("redbird.png");
        yellowbirdTexture = new Texture("yellowbird.png");
        blackbirdTexture = new Texture("blackbird.png");
        choosebird = new Texture("choosebird.jpg");
        play = new Texture("play.png");
        exit = new Texture("exit.png");
        back = new Texture("backbutton.png");
        back2 = new Texture("backbutton.png");
        selectlevel = new Texture("selectlevel.png");
        slingshot = new Texture("slingshot.png");
        rock = new Texture("rock.png");
        block = new Texture("block.png");
        stick = new Texture("stick.png");
        pig = new Texture("pig.png");
        hut = new Texture("hut.png");
        pig2 = new Texture("pig2.png");
        stand = new Texture("stand.png");
        pig3= new Texture("pig3.png");
        pig4= new Texture("pig4.png");
        glass = new Texture("glass.png");
        triangle = new Texture("triangle.png");
        rockstone = new Texture("rockstone.png");
        stone = new Texture("stone.png");
        stand2 = new Texture("stand2.png");
        icestone = new Texture("icestone.png");
        wooden = new Texture("wooden.png");
        level1Texture = new Texture("level1_texture.png");
        level2Texture = new Texture("level2_texture.png");
        level3Texture = new Texture("level3_texture.png");
        lockedTexture = new Texture("locked.png");
        pause = new Texture("pause.png");
        pauseScreen= new Texture("pause.jpg");
        restart = new Texture("restart.png");
        resume = new Texture("resume.png");
        settings = new Texture("settings.png");
        exit2 = new Texture("exit.png");
        // Loading textures for the new stage backgrounds
        t1 = new Texture("level1_back.jpg"); // Replace with your actual image
        t2 = new Texture("level2_back.jpg");
        t3 = new Texture("level3_back.png");
        settings_back = new Texture("settings_back.png");


        // Play button positioning
        float buttonWidth = 100;
        float buttonHeight = 75;
        float buttonX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float buttonY = 30;
        playButtonBounds = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);

        // Level positioning
        float levelWidth = 100;
        float levelHeight = 100;
        level1 = new Rectangle(50, 200, levelWidth, levelHeight);
        level2 = new Rectangle(200, 205, levelWidth-15, levelHeight);
        level3 = new Rectangle(350, 200, levelWidth-15, levelHeight);
        lockedLevel = new Rectangle(500, 200, levelWidth-15, levelHeight);
        Back = new Circle(-10, 385, 100);
        Back2 = new Circle(30, 140, 200);
        selectLevel = new Rectangle(175, 350, levelWidth+200, levelHeight);
        Pause = new Circle(0, 375, 100);
        Restart = new Circle(265, 190, 110);
        Resume = new Circle(450, 190, 110);
        Settings = new Circle(10, 410, 55);
        // Play and Exit positioning
        float playWidth =300;
        float playHeight = 300;
        Play = new Rectangle(165, 100, playWidth, playHeight);
        Exit = new Circle(570,400,60);
        Exit2 = new Circle(520,378,80);

        // Bird positioning
        circleRedBird = new Circle(130, Gdx.graphics.getHeight() - 200, 46);
        circleYellowBird = new Circle(335, Gdx.graphics.getHeight() - 180, 60);
        circleBlackBird = new Circle(250, Gdx.graphics.getHeight() - 355, 40);
        circleslingshot = new Circle(170 , Gdx.graphics.getHeight() - 355, 30);
        circleRock = new Circle(180 , Gdx.graphics.getHeight() - 400, 30);


        // Load the background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds.mp3"));
        backgroundMusic.setLooping(true);  // The music will loop
        backgroundMusic.play();            // Play the music

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 touchPos = new Vector2(screenX, screenY);

                // Handle play button click
                if (playButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                    System.out.println("Play button clicked!");
                    isGameStarted = true;
                    fix = 1;
                    return true;
                }

                // Handle play selection
                if (isGameStarted && !isPlaySelected) {
                    if (Play.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Play clicked!");
                        isPlaySelected = true;
                        fix=2;
                        return true;
                    }
                }
                if (isGameStarted && fix == 1 ) {
                    if (Settings.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Setting clicked!");
                        isSettingSelected = true;
                        return true;
                    }
                }
                if (!isExit2Selected && isSettingSelected){
                    if (Exit2.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Setting closed!");
                        isSettingSelected = false;
                        return true;
                    }
                }
                // Handle level selection
                if (isPlaySelected && !isLevelSelected) {
                    if (level1.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Level 1 selected!");
                        startLevel(1);
                        return true;
                    }
                    if (level2.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Level 2 selected!");
                        startLevel(2);
                        return true;
                    }
                    if (level3.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Level 3 selected!");
                        startLevel(3);
                        return true;
                    }
                }
                if (isLevelSelected && !isPauseSelected) {
                    if (Pause.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Pause Button Clicked!");
                        isPauseSelected = true;
                        return true;
                    }
                }
                if (isPauseSelected && !isBack2Selected) {
                    if (Back2.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Back Button Clicked! Returning to levels screen.");
                        isLevelSelected = false;
                        isPauseSelected = false;
                        return true;
                    }
                }
                if (isPauseSelected && !isRestartSelected){
                    if (Restart.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Restart Button Clicked!");
                        isPauseSelected = false;
                    }
                }
                if (isPauseSelected && !isResumeSelected){
                    if (Resume.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Resume Button Clicked!");
                        isPauseSelected = false;
                    }
                }
                // Handle back button click on the level selection screen
                if (isPlaySelected && isLevelSelected == false && Back.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                    System.out.println("Back button clicked! Returning to play screen.");
                    isPlaySelected = false;
                    fix=1;
                    return true;
                }
                // Handle exit button click on the play screen
                if (isGameStarted && !isPlaySelected) {
                    if (Exit.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                        System.out.println("Exit button clicked!");
                        Gdx.app.exit();  // Exit the game
                        return true;
                    }
                }

                return false;
            }
        });
    }

    @Override
    public void render() {
        batch.begin();

        if (isGameStarted) {
            if (isPauseSelected) {
                batch.draw(pauseScreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(back2, Back2.x, Back2.y, Back2.radius, Back2.radius);
                batch.draw(restart, Restart.x, Restart.y, Restart.radius, Restart.radius);
                batch.draw(resume, Resume.x, Resume.y, Resume.radius, Resume.radius);
            }else if(isSettingSelected && !isLevelSelected){
                batch.draw(settings_back,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(exit2, Exit2.x, Exit2.y, Exit2.radius, Exit2.radius);
            }
            else{
            if (!isPlaySelected) {
                batch.draw(background2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(play, Play.x, Play.y, Play.width, Play.height);
                batch.draw(exit, Exit.x, Exit.y, Exit.radius, Exit.radius);
                batch.draw(settings, Settings.x, Settings.y, Settings.radius, Settings.radius);
            } else if (!isLevelSelected) {
                // Show level selection screen
                batch.draw(background3, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(level1Texture, level1.x, level1.y, level1.width, level1.height);
                batch.draw(level2Texture, level2.x, level2.y, level2.width, level2.height);
                batch.draw(level3Texture, level3.x, level3.y, level3.width, level3.height);
                batch.draw(lockedTexture, lockedLevel.x, lockedLevel.y, lockedLevel.width, lockedLevel.height);
                batch.draw(back, Back.x, Back.y, Back.radius, Back.radius);
                batch.draw(selectlevel, selectLevel.x, selectLevel.y, selectLevel.width, selectLevel.height);
            } else if(isLevelSelected) {
                // Show selected level's background
                batch.draw(currentLevelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                if (currentLevelTexture == t1) {
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
                    batch.draw(pause, Pause.x, Pause.y, Pause.radius, Pause.radius);
                } else if (currentLevelTexture == t2) {
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
                    batch.draw(pause, Pause.x, Pause.y, Pause.radius, Pause.radius);
                } else if (currentLevelTexture == t3) {
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
                    batch.draw(pause, Pause.x, Pause.y, Pause.radius, Pause.radius);
                }
            }
        }
        }else {
            // Show main menu with play button
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(playButton, playButtonBounds.x, playButtonBounds.y, playButtonBounds.width, playButtonBounds.height);
        }

        batch.end();
    }

    private void startLevel(int level) {
        isLevelSelected = true;
        currentLevelNumber = level;
        switch (level) {
            case 1:
                currentLevelTexture = t1;
                break;
            case 2:
                currentLevelTexture = t2;
                break;
            case 3:
                currentLevelTexture = t3;
                break;
        }
        System.out.println("Starting Level " + level);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        playButton.dispose();
        background2.dispose();
        level1Texture.dispose();
        level2Texture.dispose();
        level3Texture.dispose();
        lockedTexture.dispose();
        play.dispose();
        t1.dispose();
        t2.dispose();
        t3.dispose();
        pause.dispose();
        pauseScreen.dispose();
        backgroundMusic.dispose();
    }
}
