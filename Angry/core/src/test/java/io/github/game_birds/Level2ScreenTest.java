package io.github.game_birds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class Level2ScreenTest {
    private Level2Screen level2Screen;
    private Level2Screen.Level2ScreenListener listener;

    @Before
    public void setUp() {
        Texture mockTexture = Mockito.mock(Texture.class);
        listener = Mockito.mock(Level2Screen.Level2ScreenListener.class);
        level2Screen = new Level2Screen(mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, listener);
    }

    @Test
    public void testInitialPigCount() {
        assertEquals(3, level2Screen.getPigCount());
    }

    @Test
    public void testPauseButton() {
        level2Screen.getStage().getActors().get(0).fire(new ChangeListener.ChangeEvent());
        verify(listener).pauseButton();
    }

    @Test
    public void testDownloadButton() {
        level2Screen.getStage().getActors().get(1).fire(new ChangeListener.ChangeEvent());
        verify(listener).downloadButton();
    }
}
