package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        //spawning a random number of asteroids between 2 and 10
        for(int i = 0; i < this.getRanNumBetween(2,10); i++){
            asteroid = createAstroid(gameData, this.getRanNumBetween(1,3));
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAstroid(GameData gameData, int life){
        Entity asteroid = new Asteroid();
        float radians = (float) Math.random() * 2 * 3.1415f;
        float speed = (float) Math.random() * 10f + 20f;
        float spawnX = (float) this.getRanNumBetween(0, gameData.getDisplayWidth());
        float spawnY = (float) this.getRanNumBetween(0, gameData.getDisplayHeight());

        LifePart lifePart = new LifePart(life);
        ColorPart colorPart = new ColorPart(0,128,128,1);
        MovingPart movingPart = new MovingPart(0, speed, speed, 2);
        PositionPart positionPart = new PositionPart(spawnX, spawnY, radians);

        asteroid.add(lifePart);
        asteroid.add(colorPart);
        asteroid.add(movingPart);
        asteroid.add(positionPart);

        this.setAsteroidRadius(asteroid);
        asteroid.add(new LifePart(life));
        return asteroid;
    }

    private void setAsteroidRadius(Entity asteroid){
        LifePart lifePart = asteroid.getPart(LifePart.class);
        int life = lifePart.getLife();
        if(life == 3){
            asteroid.setRadius(20);
        }else if(life == 2){
            asteroid.setRadius(10);
        }else if(life == 1){
            asteroid.setRadius(5);
        }
    }

    private int getRanNumBetween(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
