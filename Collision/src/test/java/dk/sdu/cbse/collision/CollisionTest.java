package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    private GameData gameData;
    private World world;
    private Collision collision;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
        world = new World();
        collision = new Collision();
    }

    @org.junit.jupiter.api.Test
    void collides() {
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        entity1.setX(10);
        entity1.setY(10);
        entity1.setRadius(8);
        entity1.setHealth(1);
        entity2.setX(10);
        entity2.setY(10);
        entity2.setRadius(8);
        entity2.setHealth(1);
        entity1.setIsAlive(true);
        entity2.setIsAlive(true);

        world.addEntity(entity1);
        world.addEntity(entity2);

        assertTrue(world.getEntities().contains(entity1));
        assertTrue(world.getEntities().contains(entity2));

        collision.process(gameData, world);

        assertTrue(collision.collides(entity1, entity2));

        assertFalse(entity1.getIsAlive());
        assertFalse(entity2.getIsAlive());
    }


    @AfterEach
    void tearDown() {
        for (Entity e : world.getEntities()) {
            world.removeEntity(e);
        }
    }
}