package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bird {
    private Texture texture;
    private Body body;
    private float width;
    private float height;

    public Bird(World world, Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.width = width;
        this.height = height;

        // Define the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        // Define the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

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

    public void setBodyType(BodyDef.BodyType type) {
        body.setType(type);
    }

    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
    }

    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public PolygonShape getBounds() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2, body.getPosition(), 0);
        return shape;
    }
}
