import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;

    exports dk.sdu.mmmi.cbse.asteroidsystem;

    provides IEntityProcessingService with AsteroidControlSystem;
    provides IGamePluginService with AsteroidPlugin;
}