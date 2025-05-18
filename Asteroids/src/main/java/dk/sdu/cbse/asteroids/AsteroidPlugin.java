package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;
    @Override
    public void start(GameData gameData, World world) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        executor.scheduleAtFixedRate(()-> {
            asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }, 1, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextDouble(gameData.getDisplayWidth()));
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setIsAlive(true);
        if (asteroid.getRadius() > 10) {
            asteroid.setHealth(5);
        } else if (asteroid.getRadius() < 5) {
            asteroid.setHealth(1);
        } else {
            asteroid.setHealth(3);
        }
        asteroid.setColor(Color.GREY);
        return asteroid;
    }
}
