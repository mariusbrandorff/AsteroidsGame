package dk.sdu.cbse.common.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public interface IAsteroidSplitter {
    void splitAsteroids(Entity asteroid, World world);
}
