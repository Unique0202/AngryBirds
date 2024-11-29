package io.github.game_birds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class Level3ScreenTest {
    private Level3Screen level3Screen;
    private Level3Screen.Level3ScreenListener listener;

    @Before
    public void setUp() {
        Texture mockTexture = Mockito.mock(Texture.class);
        listener = Mockito.mock(Level3Screen.Level3ScreenListener.class);
        level3Screen = new Level3Screen(mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, mockTexture, listener);
    }

    @Test
    public void testInitialPigCount() {
        assertEquals(3, level3Screen.getPigCount());
    }

    @Test
    public void testPauseButton() {
        level3Screen.getStage().getActors().get(0).fire(new ChangeListener.ChangeEvent());
        verify(listener).pauseButton();
    }

    @Test
    public void testDownloadButton() {
        level3Screen.getStage().getActors().get(1).fire(new ChangeListener.ChangeEvent());
        verify(listener).downloadButton();
    }
}
