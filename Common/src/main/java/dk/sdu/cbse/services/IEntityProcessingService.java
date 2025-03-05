package dk.sdu.cbse.services;

import dk.sdu.cbse.data.GameData;
import dk.sdu.cbse.data.World;

public interface IEntityProcessingService {
    void process(GameData gameData, World world);
}
