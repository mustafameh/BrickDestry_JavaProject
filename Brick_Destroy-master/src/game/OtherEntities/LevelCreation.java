/**
 * <h1>Level Creation</h1>
 * This Class handles all the things related to creation of levels in the game.
 * @author Mustafa Mehmood
 * @version 0.1
 */

package game.OtherEntities;

import game.Bricks.Brick;
import game.Bricks.CementBrick;
import game.Bricks.ClayBrick;
import game.Bricks.CrystalBrick;
import game.Bricks.SteelBrick;

import java.awt.*;

public class LevelCreation {

    private static final int LEVELS_COUNT = 7;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int CRYSTAL =4;

    /**
     * this method is used for creation of Bricks in the game
     * @param point First parameter to makeBrick takes a Point Object used for creation of brick
     * @param size  Second parameter to makeBrick takes a Dimention Object used for sizing of brick
     * @param type  Third parameter to makeBrick takes an int to specify the type of brick to be made
     * @return Returns a Brick Object
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case CRYSTAL:
                out = new CrystalBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }


    /**
     * Used to make walls with single type of brick
     * @param drawArea First parameter to makeSingleTypeLevel takes a Rectangle Object used for creation of level
     * @param brickCnt Second parameter to makeSingleTypeLevel takes an integer to specify number of bricks in level
     * @param lineCnt Third parameter to makeSingleTypeLevel takes an integer to specify number of bricks rows in level
     * @param brickSizeRatio Fourth parameter to makeSingleTypeLevel takes adouble to specify Size ratio of bricks in level
     * @param type Fifth parameter to makeSingleTypeLevel takes an integer to specify the type of bricks to be used
     * @return returns a Brick[]
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }
    /**
     * Used to make walls with Multiple type of brick
     * @param drawArea First parameter to makeChessBoardLevel takes a Rectangle Object used for creation of level
     * @param brickCnt Second parameter to makeChessBoardLevel takes an integer to specify number of bricks in level
     * @param lineCnt Third parameter to makeChessBoardLevel takes an integer to specify number of bricks rows in level
     * @param brickSizeRatio Fourth parameter to makeChessBoardLevel takes adouble to specify Size ratio of bricks in level
     * @param typeA Fifth parameter to makeChessBoardLevel  takes an integer to specify the type of bricks to be used
     * @param typeB Sixth parameter to makeChessBoardLevel  takes an integer to specify the type of bricks to be used
     * @return returns a Brick[]
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
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

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * Makes levels using single and multi level creation
     * @param drawArea  First parameter takes a Rectangle Object used for creation of level
     * @param brickCount Second parameter takes an integer to specify number of bricks in level
     * @param lineCount  Third parameter  takes an integer to specify number of bricks rows in level
     * @param brickDimensionRatio Fourth parameter takes adouble to specify Size ratio of bricks in level
     * @return returns a Brick[][]
     */
    public Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CRYSTAL);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CRYSTAL,CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CRYSTAL,STEEL);
        return tmp;
    }

}
