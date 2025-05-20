package dk.sdu.cbse;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons =new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    Text text;

    Game(Collection<? extends IGamePluginService> gamePluginServices, Collection<? extends IPostEntityProcessingService> gamePostEntityProcessingServices, Collection<? extends IEntityProcessingService> gameEntityProcessingServices) {
        this.gamePluginServices = (List<IGamePluginService>) gamePluginServices;
        this.postEntityProcessingServices = (List<IPostEntityProcessingService>) gamePostEntityProcessingServices;
        this.entityProcessingServiceList = (List<IEntityProcessingService>) gameEntityProcessingServices;
    }

    public void start(Stage stage) {
        text = new Text(10,20,"Score: " + gameData.getDestroyedEnemies());
        text.setFill(Color.WHITE);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (keyEvent.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        for (IGamePluginService iGamePlugin : getGamePluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("ASTEROIDS");
        stage.show();
    }
    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessingService : getEntityProcessingServices()) {
            entityProcessingService.process(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            if (!entity.getIsAlive()) {
                world.removeEntity(entity);
            }
        }
        for (IPostEntityProcessingService postEntityProcessingService : getPostEntityProcessingServices()) {
            postEntityProcessingService.process(gameData, world);
        }
    }

    private void draw() {
       text.setText("Score: " + gameData.getDestroyedEnemies());
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            polygon.setFill(entity.getColor());
        }
    }

    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServiceList;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }


}
