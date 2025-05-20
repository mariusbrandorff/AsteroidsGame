package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void splitAsteroids(Entity asteroid, World world) {
        if (asteroid.getRadius() >= 8) {
            Entity asteroid1 = new Asteroid();
            Entity asteroid2 = new Asteroid();
            asteroid1.setX(asteroid.getX() + asteroid.getRadius() + 1);
            asteroid1.setY(asteroid.getY());
            asteroid1.setRadius(asteroid.getRadius()/2);
            asteroid1.setRotation(asteroid.getRotation()+45);
            asteroid1.setIsAlive(true);
            asteroid2.setIsAlive(true);
            asteroid1.setHealth(1);
            asteroid2.setHealth(1);
            asteroid2.setX(asteroid.getX() - (asteroid.getRadius() - 1));
            asteroid2.setY(asteroid.getY());
            double[] cords1 = new double[asteroid.getPolygonCoordinates().length];
            double[] cords2 = new double[asteroid.getPolygonCoordinates().length];
            asteroid2.setRadius(asteroid.getRadius()/2);
            asteroid2.setRotation(asteroid.getRotation()/2);
            for (int i = 0; i < asteroid.getPolygonCoordinates().length; i++) {
                cords1[i] = ((asteroid.getPolygonCoordinates()[i])/(double)2)+2;
                cords2[i] = ((asteroid.getPolygonCoordinates()[i])/(double)2)-2;
            }
            asteroid1.setPolygonCoordinates(cords1);
            asteroid2.setPolygonCoordinates(cords2);
            asteroid1.setColor(asteroid.getColor());
            asteroid2.setColor(asteroid.getColor());
            asteroid1.setScore(asteroid1.getHealth());
            asteroid2.setScore(asteroid2.getHealth());
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }
}
