package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;

public interface ICollision {
    public boolean collides(Entity entity1, Entity entity2);
}
