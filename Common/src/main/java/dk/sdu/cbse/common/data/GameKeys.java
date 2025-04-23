package dk.sdu.cbse.common.data;

public class GameKeys {

    private static boolean[] keys;
    private static boolean[] keysPressed;

    private static final int NUM_KEYS = 4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        keysPressed = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysPressed[i] = keys[i];
        }
    }

    public void setKey(int key, boolean pressed) {
        keys[key] = pressed;
    }

    public boolean isDown(int key) {
        return keysPressed[key];
    }

    public boolean isPressed(int k) {
        return keys[k] && !keysPressed[k];
    }
}
