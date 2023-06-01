package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.bullet.Bullet;
import dk.sdu.mmmi.cbse.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            movingPart.setUp(true);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            setShape(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        PositionPart shooterPosition = shooter.getPart(PositionPart.class);
        float x = shooterPosition.getX();
        float y = shooterPosition.getY();
        float radians = shooterPosition.getRadians();
        float speed = 200;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        bullet.add(new ColorPart(255,255,0,1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }

    private void setShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = x;
        shapey[0] = y;

        shapex[1] = (float) (x + cos(radians - 4 * 3.1415f / 5));
        shapey[1] = (float) (y + sin(radians - 4 * 3.1145f / 5));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
