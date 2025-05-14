module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    exports dk.sdu.cbse;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    opens dk.sdu.cbse to javafx.graphics, spring.core;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}