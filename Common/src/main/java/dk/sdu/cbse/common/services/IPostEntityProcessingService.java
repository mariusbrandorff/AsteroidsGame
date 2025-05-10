package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IPostEntityProcessingService {
    /**
     * Is ran after the entities have been processed
     * @param gameData
     * generalized data about game
     * @param world
     * the world the entities are in
     */
    void process(GameData gameData, World world);
}
