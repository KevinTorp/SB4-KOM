package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        //spawning a random number of enemies between 3 and 8
        for(int i = 0; i < this.getRanNumBetween(3,8); i++){
            enemy = createEnemy(gameData);
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove all enemy entities
        for(Entity e : world.getEntities(Enemy.class)){
            world.removeEntity(e);
        }
    }

    private Entity createEnemy(GameData gameData) {

        float deacceleration = 100;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        // spawn point; random left side of screen from bottom to max screen height
        float x = 0;
        float y = this.getRanNumBetween(0, gameData.getDisplayHeight());
        float radians = 3.1415f / 2;

        Entity enemy = new Enemy();

        enemy.setRadius(10);

        enemy.add(new LifePart(2));
        enemy.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new PositionPart(x, y, radians));
        enemy.add(new ColorPart(255,0,0,1));

        return enemy;
    }

    private int getRanNumBetween(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }

}
