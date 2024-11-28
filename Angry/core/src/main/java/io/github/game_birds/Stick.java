package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Stick extends Structure {
    private int health;
    private int collisionCount;

    public Stick(World world, Texture texture, float x, float y, float width, float height) {
        super(world, texture, x, y, width, height,100);
        this.health = 100; // Initial health
        this.collisionCount = 0;
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(texture, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
        // Display health
        font.draw(batch, "Health: " + health, body.getPosition().x, body.getPosition().y + height / 2 + 10);
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int getHealth() {
        return health;
    }

    public Rectangle getBounds() {
        return new Rectangle(body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
    }
    public void incrementCollisionCount() {
        collisionCount++;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

}
