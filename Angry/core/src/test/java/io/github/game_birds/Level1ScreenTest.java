package io.github.game_birds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class Level1ScreenTest {
    private Level1Screen level1Screen;
    private Level1Screen.Level1ScreenListener listener;

    @Before
    public void setUp() {
        Texture mockTexture = Mockito.mock(Texture.class);
        listener = Mockito.mock(Level1Screen.Level1ScreenListener.class);
        level1Screen = new Level1Screen(mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, listener);
    }

    @Test
    public void testInitialPigCount() {
        assertEquals(3, level1Screen.getPigCount());
    }

    @Test
    public void testPauseButton() {
        level1Screen.getStage().getActors().get(0).fire(new ChangeListener.ChangeEvent());
        verify(listener).pauseButton();
    }

    @Test
    public void testDownloadButton() {
        level1Screen.getStage().getActors().get(1).fire(new ChangeListener.ChangeEvent());
        verify(listener).downloadButton();
    }
}
