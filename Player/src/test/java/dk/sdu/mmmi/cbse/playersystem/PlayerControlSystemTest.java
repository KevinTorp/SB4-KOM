package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PlayerControlSystemTest {
    @Test()
    void process() {
        // Mock a player with position and moving parts
        Player player = new Player();
        PositionPart positionPart = new PositionPart(0, 0, 0);
        player.add(positionPart);
        Player mockPlayer = mock(Player.class);
        MovingPart movingPart = mock(MovingPart.class);

        // Configure the mockPlayer to return the parts from the player
        when(mockPlayer.getPart(MovingPart.class)).thenReturn(movingPart);
        when(mockPlayer.getPart(PositionPart.class)).thenReturn(player.getPart(PositionPart.class));
        when(mockPlayer.getShapeX()).thenReturn(player.getShapeX());
        when(mockPlayer.getShapeY()).thenReturn(player.getShapeY());
        when(mockPlayer.getRadius()).thenReturn(player.getRadius());

        // Create a gameData with the keys we want to test
        GameData data = mock(GameData.class);
        GameKeys keys = mock(GameKeys.class);
        when(data.getKeys()).thenReturn(keys);
        when(keys.isDown(GameKeys.UP)).thenReturn(true);
        when(keys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(keys.isDown(GameKeys.RIGHT)).thenReturn(false);

        // Create a world with the mockPlayer
        World world = mock(World.class);
        List<Entity> players = new ArrayList<>();
        players.add(mockPlayer);
        when(world.getEntities(Player.class)).thenReturn(players);

        // Run the test with the mockPlayer
        PlayerControlSystem test = new PlayerControlSystem();
        test.process(data, world);

        // Verify that the mockPlayer was processed correctly
        verify(mockPlayer).getPart(MovingPart.class);
        verify(movingPart).setUp(true);
        verify(movingPart).setRight(false);
        verify(movingPart).setLeft(false);
    }
}