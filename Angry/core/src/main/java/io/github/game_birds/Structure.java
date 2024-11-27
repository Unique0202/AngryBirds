package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Structure {
    protected Texture texture;
    protected Body body;
    protected float width;
    protected float height;

    public Structure(World world, Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.width = width;
        this.height = height;

        // Define the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Change to DynamicBody
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        // Define the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;

        // Create the fixture
        body.createFixture(fixtureDef);

        // Dispose the shape after using it
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
