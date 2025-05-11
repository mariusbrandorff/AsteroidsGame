import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    requires CommonPlayer;
    requires CommonBullet;
    requires Common;
    provides IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerProcess;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
}