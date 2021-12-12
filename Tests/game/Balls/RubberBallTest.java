package game.Balls;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;


class RubberBallTest {

	@Test
	public void testMakeBall() {
		Point2D center = new Point(300,430);
		double x = center.getX() - (2 / 2);
        double y = center.getY() - (2 / 2);
		RubberBall ball = new RubberBall(center);
		
		assertEquals(ball.makeBall(center, 2, 2), new Ellipse2D.Double(x,y,2,2));
	}

}
