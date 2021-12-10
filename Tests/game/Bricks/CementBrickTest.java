package game.Bricks;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class CementBrickTest {
	
	 CementBrick cementBrick = new CementBrick(new Point(0,0), new Dimension(60,45));
	 Rectangle brickFace = new Rectangle(new Point(0,0), new Dimension(60,45));

	@Test
	void testMakeBrickFace() {
		Point pos = new Point(0,0);
        Dimension size = new Dimension(60,45);
        assertEquals(brickFace, cementBrick.makeBrickFace(pos,size));
	}

	@Test
	void testSetImpact() {
		Point2D down = new Point2D.Double();
        down.setLocation(300.0, 430.0);
        int up = 30;
        assertFalse(cementBrick.setImpact(down, up));
	}



	@Test
	void testRepair() {
		cementBrick.repair();
        assertFalse(cementBrick.isBroken());
	}
	@Test
	void testGetBrick() {
		 assertEquals(brickFace, cementBrick.getBrick());
	}



}
