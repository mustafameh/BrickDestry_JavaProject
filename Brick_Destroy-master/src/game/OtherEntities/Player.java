/**
 * <h1>Player</h1>
 * This Class handles all the displaying of Score
 * User Can Select Exit to Close the Application
 *
 * @author Mustafa Mehmood
 * @version 0.1
 */
package game.OtherEntities;

import game.Balls.Ball;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * Constructor method of InfoMenu which sets and initializes some features of player
     * @param ballPoint Specify the point for player
     * @param width Int Parameter used to specify width of player
     * @param height Int Parameter used to specify height of player
     * @param container takes in Rectangle object as parameter
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * Used to create the rectangle for player
     * @param width Int parameter to specify the width of player
     * @param height Int parameter to specify the height of player
     * @return returns a Rectangle Object
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * Method detects if ball has made impact
     * @param b takes in Ball object
     * @return returns a boolean specifying whether impact has been made
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * Method used to move player
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * Method used to move the player left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Method  used to move the player Right
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * method used to stop Player
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * getter method for playerface
     * @return Returns Shape Object
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * Used to move the player
     * @param p Point Object as Parameter to specify the location to move
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}