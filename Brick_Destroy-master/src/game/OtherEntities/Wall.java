/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package game.OtherEntities;

import java.awt.*;
import java.awt.geom.Point2D;
import game.Balls.Ball;
import game.Bricks.Brick;
import game.Bricks.Crack;
import game.Balls.RubberBall;


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


    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    public void move(){
        player.move();
        ball.move();
    }

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

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

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

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }
    
    public int getScore(){return playerScore;}
    public void setScore(int x){playerScore=x;}

}
