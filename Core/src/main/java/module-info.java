module Core {
    requires Common;
    requires javafx.graphics;
    requires java.desktop;
    opens dk.sdu.cbse to javafx.graphics;
    uses dk.sdu.cbse.services.IGamePluginService;
    uses dk.sdu.cbse.services.IEntityProcessingService;
    uses dk.sdu.cbse.services.IPostEntityProcessingService;
}