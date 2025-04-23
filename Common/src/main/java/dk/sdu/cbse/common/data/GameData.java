package dk.sdu.cbse.common.data;

public class GameData {

    private int displayWidth = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int destroyedAsteroids = 0;
    private int destroyedEnemies = 0;

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void incrementDestroyedAsteroids() {
        destroyedAsteroids++;
    }

    public int getDestroyedAsteroids() {
        return destroyedAsteroids;
    }

    public int getDestroyedEnemies() {
        return destroyedEnemies;
    }

    public void incrementDestroyedEnemies() {
        destroyedEnemies++;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }
}
