package game.Debugger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import game.OtherEntities.Wall;

class DebugPanelTest {

	private Point point = new Point(300,430);
	private Point point2 = new Point(200,200);
	private Dimension size = new Dimension(20,20);
	Rectangle rect = new Rectangle(point, size);
	Wall wall = new Wall(rect,10,3,2.2,point2);
	DebugPanel debugpanel = new DebugPanel(wall);
	
	@Test
	public void testDebugPanel() {
		assertNotNull(debugpanel);
	}

	@Test
	public void testSetValues() {
		debugpanel.setValues(2, 4);
	}





}
