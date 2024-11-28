// core/src/main/java/io/github/game_birds/Structure.java
package io.github.game_birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Structure {
    protected Texture texture;
    protected Body body;
    protected float width;
    protected float height;
    protected int health;

    public Structure(World world, Texture texture, float x, float y, float width, float height, int health) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.health = health;

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

        // Set user data for collision detection
        body.setUserData(this);
    }

    public void handleCollision() {
        health -= 10; // Decrease health by 10 on collision
        if (health <= 0) {
            // Handle structure destruction
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - width / 2, body.getPosition().y - height / 2, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void setBodyType(BodyDef.BodyType type) {
        body.setType(type);
    }
}
