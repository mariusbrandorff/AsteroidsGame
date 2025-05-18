package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    private ScheduledExecutorService scheduler;

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemy.setX(gameData.getDisplayHeight()*Math.random());
        enemy.setY(gameData.getDisplayHeight()*Math.random());
        enemy.setRadius(8);
        enemy.setHealth(3);
        enemy.setIsAlive(true);
        enemy.setColor(Color.DARKRED);
        return enemy;
    }

    @Override
    public void start(GameData gameData, World world) {
        scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> {
           enemy = createEnemy(gameData);
           world.addEntity(enemy);
        },10,20, TimeUnit.SECONDS);
    }

    @Override
    public void stop(GameData gameData, World world) {
        scheduler.shutdownNow();
        for (Entity entity : world.getEntities(Enemy.class)) {
            world.removeEntity(entity);
        }
    }
}
