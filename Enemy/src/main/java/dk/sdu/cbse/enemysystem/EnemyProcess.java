package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyProcess implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            int random = (int) (Math.random() * 50) + 1;
            if (random == 1 || random == 4) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (random == 2 || random == 5) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (random == 3) {
                if (world.getEntities().contains(enemy)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(enemy, gameData));
                            }
                    );
                }
            }

            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
