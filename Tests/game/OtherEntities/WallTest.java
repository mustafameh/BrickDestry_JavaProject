package game.OtherEntities;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.geom.Point2D;

import game.Balls.RubberBall;
import game.OtherEntities.Player;
import game.OtherEntities.Scoring;
import game.Bricks.Brick;
import game.Bricks.ClayBrick;

import org.junit.jupiter.api.Test;

class WallTest {

	Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));
    Player player = new Player(new Point(300,430),150,10,new Rectangle(0,0,600,450));
    RubberBall ball = new RubberBall(new Point(300,430));
    


	@Test
	void testMove() {
		player.move();
        ball.move();
        assertNotNull(player);
        assertNotNull(ball);
	}



	@Test
	void testGetBallCount() {
		wall.resetBallCount();
		assertEquals(3,wall.getBallCount());
	}

	@Test
	void testIsBallLost() {
		assertFalse(wall.isBallLost());
	}

	@Test
	void testBallReset() {
		wall.ballReset();
        assertEquals(new Point(300,430),ball.getPosition());
	}

	@Test
	void testWallReset() {
		ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(60,45));
        if(clayBrick.isBroken()) {
            wall.wallReset();
        }

        assertEquals(0,wall.getBrickCount());
        assertEquals(3,wall.getBallCount());
	}

	@Test
	void testBallEnd() {
		if (wall.getBallCount() == 0)
            assertTrue(wall.ballEnd());
    }

	@Test
	void testIsDone() {
		if(wall.getBrickCount() == 0){
            assertTrue(wall.isDone());
        }
	}

	

	@Test
	void testHasLevel() {
		assertTrue(wall.hasLevel());
	}

	@Test
	void testSetBallXSpeed() {
        assertEquals(3,ball.getSpeedX());
	}

	@Test
	void testSetBallYSpeed() {

        assertEquals(-3,ball.getSpeedY());
	}

	@Test
	void testResetBallCount() {
		wall.resetBallCount();
        assertEquals(3,wall.getBallCount());
	}

	@Test
	void testGetScore() {
		 assertNotNull( wall.getScore());
	}

	@Test
	void testSetScore() {
		wall.setScore(100);
		assertEquals(100,wall.getScore());
	}

}
