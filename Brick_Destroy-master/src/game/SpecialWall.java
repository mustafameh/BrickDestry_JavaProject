package game;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpecialWall {

    private static final int LEVELS_COUNT = 4;


    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Rectangle area;

    Brick[] bricks;
    Ball superBall;
    Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private int playerScore = 0;
    private String brickType;

    public SpecialWall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;


        makeBall(ballPos);

        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;


    }

    private void makeBall(Point2D ballPos) {
//
        superBall = new SuperBall(ballPos);
    }

    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CEMENT, STEEL); // cement and steel level
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, STEEL);    //


        return tmp;
    }

    public void move() {
        player.move();
        superBall.move();
    }

    public void findImpacts() {
        if (player.impact(superBall)) {
            superBall.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            if (brickType.equals("ClayBrick")) {
                playerScore += 100;

            } else if (brickType.equals("CementBrick")) {
                playerScore += 200;

            } else if (brickType.equals("SteelBrick")) {
                playerScore += 300;

            }
        } else if (impactBorder()) {
            superBall.reverseX();
        } else if (superBall.getPosition().getY() < area.getY()) {
            superBall.reverseY();
        } else if (superBall.getPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }


    }

    private boolean impactWall() {
        for (Brick b : bricks) {
            switch (b.findImpact(superBall)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    brickType = b.getClass().getSimpleName();
                    superBall.reverseY();
                    return b.setImpact(superBall.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    brickType = b.getClass().getSimpleName();
                    superBall.reverseY();
                    return b.setImpact(superBall.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    brickType = b.getClass().getSimpleName();
                    superBall.reverseX();
                    return b.setImpact(superBall.right, Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    brickType = b.getClass().getSimpleName();
                    superBall.reverseX();
                    return b.setImpact(superBall.left, Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder() {
        Point2D p = superBall.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {

//        int speedX,speedY;
//        do{
//            speedX = rnd.nextInt(5) - 2;
//        }while(speedX == 0);
//        do{
//            speedY = -rnd.nextInt(3);
//        }while(speedY == 0);
////rubberBall.setSpeed(5,-5);

        player.moveTo(startPoint);
        superBall.moveTo(startPoint);
        superBall.setYSpeed(-superBall.getSpeedY());

        ballLost = false;
    }

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd() {
        return ballCount == 0;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel() {
        return level < levels.length;
    }

    public void setBallXSpeed(int s) {
        superBall.setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        superBall.setYSpeed(s);
    }

    public void resetBallCount() {
        ballCount = 3;
    }

    private Brick makeBrick(Point point, Dimension size, int type) {
        Brick out;
        switch (type) {
            case CLAY:
                out = new ClayBrick(point, size);
                break;
            case STEEL:
                out = new SteelBrick(point, size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
        return out;
    }

    public int getScore() {
        return playerScore;
    }

    public void setScore(int x) {
        playerScore = x;
    }
}
