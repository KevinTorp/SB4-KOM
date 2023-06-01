package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService  {
        /**
         * Process all entities in the world.
         * And it's used to after updates from {@link IEntityProcessingService#process(GameData, World)}.
         * Commonly used for collision handling.
         * Pre: The entities have been started,
         *      and {@link IEntityProcessingService#process(GameData, World)} has been called.
         * Post: The entities in the world has been post-processed and updated.
         * @param gameData Contains every information about the game.
         * @param world Contains the entities in the world.
         */
        void process(GameData gameData, World world);
}
