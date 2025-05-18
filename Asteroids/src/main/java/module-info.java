import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;

module Asteroids {
    exports dk.sdu.cbse.asteroids;
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidProcessor;
}