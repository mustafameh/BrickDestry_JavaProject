package game.Bricks;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

class ClayBrickTest {
	ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(60,45));
    Rectangle brickFace = new Rectangle(new Point(0,0), new Dimension(60,45));

	@Test
	void testMakeBrickFace() {
		Point pos = new Point(0,0);
        Dimension size = new Dimension(60,45);
        assertEquals(brickFace, clayBrick.makeBrickFace(pos,size));
	}

	@Test
	void testGetBrick() {
		 assertEquals(brickFace, clayBrick.getBrick());
	}



}
