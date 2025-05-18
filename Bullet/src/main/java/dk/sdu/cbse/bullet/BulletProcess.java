package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import javafx.scene.paint.Color;

public class BulletProcess implements IEntityProcessingService, BulletSPI {
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 2);
        bullet.setY(shooter.getY() + changeY * 2);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        bullet.setHealth(1);
        bullet.setIsAlive(true);
        bullet.setColor(Color.WHITESMOKE);
        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(entity.getRotation()));
            double changeY = Math.sin(Math.toRadians(entity.getRotation()));
            entity.setX(entity.getX() + changeX * 10);
            entity.setY(entity.getY() + changeY * 10);
            if (entity.getY() < 0 || entity.getY() > gameData.getDisplayHeight() || entity.getX() < 0 || entity.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(entity);
            }

        }
    }
}
