package dk.sdu.cbse.asteroids;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteroidsPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("AsteroidsPlugin start, split packages are working");
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
