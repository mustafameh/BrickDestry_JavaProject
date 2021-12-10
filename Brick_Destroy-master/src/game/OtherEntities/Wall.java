
package game.OtherEntities;

import java.awt.*;
import java.awt.geom.Point2D;
import game.Balls.Ball;
import game.Bricks.Brick;
import game.Bricks.Crack;
import game.Balls.RubberBall;
import game.OtherEntities.Player;
import game.OtherEntities.Scoring;



/**
 * <h1>Wall </h1>
 * This Class is used to manage the wall and related entities of Wall
 *
 * @author Mustafa Mehmood
 * @version 0.1
 */
public class Wall {

    private static final int LEVELS_COUNT = 7;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int CRYSTAL =4;
    public Ball ball;
    public Player player;
    public Brick[] bricks;

    //private Random rnd;
    private Rectangle area;



    private Brick[][] levels;


    private int level;



    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private int playerScore = 0;
    private String brickType;

    private LevelCreation tempp;


    /**
     * onstructor method of InfoMenu which sets and initializes some features of wall and the level
     * @param drawArea First parameter takes a Rectangle Object used for creation of level
     * @param brickCount Second parameter  takes an integer to specify number of bricks in level
     * @param lineCount Third parameter  takes an integer to specify number of bricks in level
     * @param brickDimensionRatio Fourth parameter takes in double to specify the size
     * @param ballPos Fifth parameter takes a Point object
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        tempp = new LevelCreation();

        levels = tempp.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;



        makeBall(ballPos);
//
//        rnd = new Random();
//        int speedX,speedY;
//        do{
//            speedX = rnd.nextInt(5) - 2;
//        }while(speedX == 0);
//        do{
//            speedY = -rnd.nextInt(3);
//        }while(speedY == 0);
//
//        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }

    /**
     * Used to create the ball on the wall
     * @param ballPos Point2D Parameter Specify position
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * Used to move player and ball
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * used to chect whether impact has been made.
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            if(brickType.equals("ClayBrick")){
                playerScore+=100;

            }
            else if(brickType.equals("CementBrick")){
                playerScore+=200;

            }
            else if(brickType.equals("SteelBrick")){
                playerScore+=300;

            }
            else if(brickType.equals("CrystalBrick")){
                playerScore+=400;

            }
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * Used to check whether impact with wall has been made
     * @return returns a boolean to specify impact
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    brickType=b.getClass().getSimpleName();
                    ball.reverseY();
                    return b.setImpact(ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    brickType=b.getClass().getSimpleName();
                    ball.reverseY();
                    return b.setImpact(ball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    brickType=b.getClass().getSimpleName();
                    ball.reverseX();
                    return b.setImpact(ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    brickType=b.getClass().getSimpleName();
                    ball.reverseX();
                    return b.setImpact(ball.left,Crack.LEFT);
            }
        }
        return false;
    }
    /**
     * Used to check whether impact with border has been made
     * @return returns a boolean to specify impact
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * getter method for brickcount
     * @return Returns an integer with brick count
     */
    public int getBrickCount(){
        return brickCount;
    }
    /**
     * getter method for ballCount
     * @return Returns an integer with ball count
     */
    public int getBallCount(){
        return ballCount;
    }
    /**
     * Used to check whether ball is lost
     * @return returns a boolean to specify ball lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * Used to reset the ball
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
//        int speedX,speedY;
//        do{
//            speedX = rnd.nextInt(5) - 2;
//        }while(speedX == 0);
//        do{
//            speedY = -rnd.nextInt(3);
//        }while(speedY == 0);

        ball.setSpeed(ball.getSpeedX(),- ball.getSpeedY());
        ballLost = false;
    }

    /**
     * used to reset the wall
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }
    /**
     * getter method for level
     * @return Int specifying the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * used to set ball count to 0
     * @return
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }
    /**
     * used to set ball count to 0
     * @return
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * method used to go to the next level
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * Method used to check level
     * @return returns a boolean to check whether the level is less than levels length
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * Used to set the speed of ball
     * @param s int parameter to set the ball speed
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }
    /**
     * Used to set the speed of ball
     * @param s int parameter to set the ball speed
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * Used to reset the ball count
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Setter method for playerScore
      * @return Int specifying the score
     */
    public int getScore(){return playerScore;}

    /**
     * Used to set Score
     * @param x Int Parameter used to set score
     */
    public void setScore(int x){playerScore=x;}

}
