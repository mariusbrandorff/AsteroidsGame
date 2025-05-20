import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonEnemy;
    requires CommonAsteroids;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.Collision;
    uses dk.sdu.cbse.common.services.IScoreSender;

}