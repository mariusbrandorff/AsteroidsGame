package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class Collision implements IPostEntityProcessingService {

    public Collision() {}

    @Override
    public void process(GameData gameData, World world) {
        //putting all entities in a list in order to not do any double checks
        List<Entity> entities = new ArrayList<>(world.getEntities());
        //Iterate over all entities
        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);
            //If entity is dead it should continue, made in case the removal is too slow of dead objects
            if (!entity1.getIsAlive()) { continue; }
            //Iterate over remaining entities in order to prevent checking the same thing twice
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);
                //If entity is dead it should continue, made in case the removal is too slow of dead objects
                if (!entity2.getIsAlive()) { continue; }
                //Removes health from each entity
                if (this.collides(entity1,entity2)) {
                    entity1.setHealth(entity1.getHealth() - 1);
                    entity2.setHealth(entity2.getHealth() - 1);

                    if (entity1.getHealth() <= 0) {
                        entity1.setIsAlive(false);
                    }
                    if (entity2.getHealth() <= 0) {
                        entity2.setIsAlive(false);
                    }
                }
            }
        }
    }

    public boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
