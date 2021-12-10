package game.Bricks;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import org.junit.jupiter.api.Test;

class SteelBrickTest {
	
	SteelBrick steelBrick = new SteelBrick(new Point(0,0), new Dimension(60,45));
    Rectangle brickFace = new Rectangle(new Point(0,0), new Dimension(60,45));

	@Test
	void testMakeBrickFace() {
		Point pos = new Point(0,0);
        Dimension size = new Dimension(60,45);
        assertEquals(brickFace, steelBrick.makeBrickFace(pos,size));
	}

	@Test
	void testSetImpact() {
		Point2D up = new Point2D.Double(495.0,40.0);
        steelBrick.setImpact(up, Crack.DOWN);
        if(steelBrick.isBroken())
        {
            assertFalse(steelBrick.setImpact(up, Crack.DOWN));
        }

    }
	

	@Test
	void testGetBrick() {
		assertEquals(brickFace, steelBrick.getBrick());
	}

	@Test
	void testImpact() {
		Random rnd = new Random();
        double STEEL_PROBABILITY = 0.5;
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            assertFalse(steelBrick.isBroken());
        }
    
	}


}
