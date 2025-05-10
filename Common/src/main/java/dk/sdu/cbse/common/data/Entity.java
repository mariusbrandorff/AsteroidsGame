package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double roation;
    private float radius;
    private int health;
    private Boolean isAlive;

    public String getId() {
        return ID.toString();
    }

    public int getHealth() {
        return health;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setPolygonCoordinates(double... polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return roation;
    }

    public void setRotation(double rotation) {
        this.roation = rotation;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}


