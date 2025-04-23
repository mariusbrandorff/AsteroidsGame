package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Start is called when the game is running its first tick
     * @param gameData
     * relevant generalized data
     * @param world
     * access to all entities in current world
     */
    void start(GameData gameData, World world);

    /**
     * stop should be called when the game reaches an end
     * @param gameData
     * relevant generalized data
     * @param world
     * access to all entities in current world
     */
    void stop(GameData gameData, World world);
}
