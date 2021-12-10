package game.OtherEntities;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.geom.Point2D;

import game.Balls.Ball;
import game.Bricks.Brick;
import game.Bricks.Crack;
import game.Balls.RubberBall;

import org.junit.jupiter.api.Test;

class WallTest {
	
	Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));
    Player player = new Player(new Point(300,430),150,10,new Rectangle(0,0,600,450));
    RubberBall ball = new RubberBall(new Point(300,430));

	@Test
	void testWall() {
		fail("Not yet implemented");
	}

	@Test
	void testMove() {
		fail("Not yet implemented");
	}

	@Test
	void testFindImpacts() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBrickCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBallCount() {
		fail("Not yet implemented");
	}

	@Test
	void testIsBallLost() {
		fail("Not yet implemented");
	}

	@Test
	void testBallReset() {
		fail("Not yet implemented");
	}

	@Test
	void testWallReset() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLevel() {
		fail("Not yet implemented");
	}

	@Test
	void testBallEnd() {
		fail("Not yet implemented");
	}

	@Test
	void testIsDone() {
		fail("Not yet implemented");
	}

	@Test
	void testNextLevel() {
		fail("Not yet implemented");
	}

	@Test
	void testHasLevel() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBallXSpeed() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBallYSpeed() {
		fail("Not yet implemented");
	}

	@Test
	void testResetBallCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetScore() {
		fail("Not yet implemented");
	}

	@Test
	void testSetScore() {
		fail("Not yet implemented");
	}

}
