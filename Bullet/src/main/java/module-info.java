import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    requires CommonBullet;
    requires Common;
    requires javafx.graphics;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletProcess;
    provides BulletSPI with dk.sdu.cbse.bullet.BulletProcess;

}