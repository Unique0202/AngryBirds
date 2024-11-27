package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Stick extends Structure {
    public Stick(World world, Texture texture, float x, float y, float width, float height) {
        super(world, texture, x, y, width, height);
    }
}
