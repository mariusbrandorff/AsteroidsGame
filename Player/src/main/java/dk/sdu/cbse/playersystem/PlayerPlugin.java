package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.player.Player;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin(){}

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private Entity createPlayer(GameData gameData) {
        // creating player entity via, polymorphism
        Entity playerEntity = new Player();
        playerEntity.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerEntity.setX((double) gameData.getDisplayHeight() /2);
        playerEntity.setY((double) gameData.getDisplayWidth() /2);
        playerEntity.setHealth(3);
        playerEntity.setRadius(8);
        playerEntity.setIsAlive(true);
        return playerEntity;
    }
}
