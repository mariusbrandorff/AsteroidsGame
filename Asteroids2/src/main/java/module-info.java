import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroids2 {
    requires Common;
    provides IGamePluginService with dk.sdu.cbse.asteroids.AsteroidsPlugin;
}