package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        //remove entities
        for(Entity bullet : world.getEntities()){
            if(bullet instanceof Bullet){
                world.removeEntity(bullet);
            }
        }
    }
}
