package game.OtherEntities;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import org.junit.jupiter.api.Test;
import game.Balls.RubberBall;

class PlayerTest {
	
    Player player = new Player(new Point(300,430),150,10,new Rectangle(0,0,600,450));
    RubberBall ball = new RubberBall(new Point(300,430));



	@Test
	void testImpact() {
		assertTrue(player.impact(ball));
	}

	@Test
	void testMove() {
	       player.move();
	        int a = (int) ball.getPosition().getX()+1;
	        Point p = new Point(a,430);
	        assertEquals(new Point(301,430),p);
	}

	@Test
	void testMoveLeft() {
        player.moveLeft();
        player.setMoveAmount(-5);
        assertEquals(-5,player.getMoveAmount());
	}

	@Test
	void testMovRight() {
        player.movRight();
        player.setMoveAmount(5);
        assertEquals(5,player.getMoveAmount());
	}

	@Test
	void testStop() {
		player.stop();
        assertEquals(0, player.getMoveAmount());
	}

	@Test
	void testGetPlayerFace() {
        Rectangle playerFace = new Rectangle(225,430,150,10);
        assertEquals(playerFace,player.getPlayerFace());
	}

	@Test
	void testMoveTo() {
        player.moveTo(new Point(300,430));
        Point playerPosition = new Point(300-(150/2),430);
        assertEquals(new Point(225,430), playerPosition);
	}

}
