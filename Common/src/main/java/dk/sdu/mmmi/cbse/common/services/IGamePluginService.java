package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * It's called to start the plugin.
     * And adds entities to the World.
     * Pre: It must not have been called before.
     * Post: The world contains the entity created.
     * @param gameData Contains every information about the game.
     * @param world Contains the entities in the world.
     *              And is used to add entities to the world.
     */
    void start(GameData gameData, World world);

    /**
     * It's called to stop the plugin.
     * And it's used to remove plugin/entities from the world.
     * @param gameData Contains every information about the game.
     * @param world Contains the entities in the world.
     *              And is used to remove plugin/entities from the world.
     */
    void stop(GameData gameData, World world);
}
