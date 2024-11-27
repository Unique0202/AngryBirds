package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Pig {
    private Texture texture;
    private Body body;

    public Pig(World world, Texture texture, float x, float y) {
        this.texture = texture;

        // Define the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        // Define the shape
        CircleShape shape = new CircleShape();
        shape.setRadius(15f); // Set the radius of the pig

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0f; // Make it bounce a little bit

        // Create the fixture
        body.createFixture(fixtureDef);

        // Dispose the shape after using it
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - 15, body.getPosition().y - 15, 40, 40);
    }

    public void dispose() {
        texture.dispose();
    }
}
