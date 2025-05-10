import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidProcessor;
}