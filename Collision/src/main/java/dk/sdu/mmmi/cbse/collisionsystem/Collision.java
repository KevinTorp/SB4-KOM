package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.List;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for(Entity entity : world.getEntities()){
            for (Entity other : world.getEntities()){
                LifePart entityLife = entity.getPart(LifePart.class);

                //If the two entities are the same, skip
                if(entity.getID().equals(other.getID())){
                    continue;
                }

                //Collision detection
                if(this.collides(entity, other)){
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    private Boolean collides(Entity e1, Entity e2){
        PositionPart myPositionPart = e1.getPart(PositionPart.class);
        PositionPart otherPositionPart = e2.getPart(PositionPart.class);

        double dist = Math.sqrt(
                ((myPositionPart.getX() - otherPositionPart.getX()) * (myPositionPart.getX() - otherPositionPart.getX()))
                        +
                        ((myPositionPart.getY() - otherPositionPart.getY()) * (myPositionPart.getY() - otherPositionPart.getY()))
        );

        if(dist < (e1.getRadius() + e2.getRadius())){
            return true;
        }

        return false;
    }
}
