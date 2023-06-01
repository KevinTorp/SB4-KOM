package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * Process all entities in the world.
     * It's called every time the game loops.
     * And is used to update the entities in the world.
     * e.g. movement
     * pre: The previous process call has ended.
     * post: The entities in the world has been processed and updated.
     * @param gameData Contains every information about the game.
     *                 And it's used for processing the entities.
     * @param world Contains the entities in the world.
     */

    void process(GameData gameData, World world);
}
