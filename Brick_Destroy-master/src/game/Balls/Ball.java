package game.Balls;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * <h1>Ball Abstract Class </h1>
 * This Class is used to manage the ball properties
 * @author Mustafa Mehmood
 * @version 0.1
 */
abstract public class Ball {

    public Point2D right;
    public Point2D left;
    public Point2D up;
    public Point2D down;
    private Shape ballFace;

    private Point2D center;


    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Constructor method of Ball which sets values of some fields , initializes some objects and calls methods required for ball.
     * @param center Point2D Parameter to specify ball center
     * @param radiusA Int to specify radius of Ball
     * @param radiusB Int to specify radius of Ball
     * @param inner Color obj to specify the color of ball
     * @param border Color obj to specify the color of border
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Protected method To create  the ball
     * @param center Point2D Parameter to specify ball center
     * @param radiusA Int to specify radius of Ball
     * @param radiusB Int to specify radius of Ball
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * used to move the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Used to set the speed of ball
     * @param x Int to set x speed of ball
     * @param y Int to set the Y speed of ball
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Used to set the speed of ball
     * @param s Int to set x speed
     */
    public void setXSpeed(int s){
        speedX = s;
    }
    /**
     * Used to set the speed of ball
     * @param s set y speed of ball Int
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * used to reverse the direction of ball in x direction
     */
    public void reverseX(){
        speedX *= -1;
    }
    /**
     * used to reverse the direction of ball in y direction
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * getter for border color
     * @return Color Object
     */
    public Color getBorderColor(){
        return border;
    }
    /**
     * getter for inner color
     * @return Color Object
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * getter to return position of Ball
     * @return point2d object for ball position
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Used to return the shape of ball
     * @return Shape object of ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Used to move the ball to a point
     * @param p Point object to specify the point for ball movement
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * Used to set the points for Ball
     * @param width double to specify width
     * @param height double to specify height
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * getter for x speed of ball
     * @return Int with x speed of ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * getter for y speed of ball
     * @return Int with y speed of ball
     */
    public int getSpeedY(){
        return speedY;
    }


}
