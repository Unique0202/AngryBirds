package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public abstract class Slingshot {
    protected Texture texture;
    protected Circle circle;

    public Slingshot(Texture texture, Circle circle) {
        this.texture = texture;
        this.circle = circle;
    }

    public Texture getTexture() {
        return texture;
    }

    public Circle getCircle() {
        return circle;
    }
}
