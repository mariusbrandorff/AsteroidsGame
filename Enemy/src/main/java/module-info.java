import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    provides IGamePluginService with dk.sdu.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemysystem.EnemyProcess;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
}