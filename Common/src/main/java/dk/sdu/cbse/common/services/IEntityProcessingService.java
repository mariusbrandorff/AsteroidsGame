package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * used to implement an entities logic during processing
     * @param gameData
     * gameData is used to be able to use generalized data for the game
     * @param world
     * world is used to be able to add, delete or create new entities
     */
    void process(GameData gameData, World world);

}
