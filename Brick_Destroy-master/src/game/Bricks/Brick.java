package game.Bricks;

import game.Balls.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;


abstract public class Brick  {
	/**
	 * <h1>Brick Abstract Class </h1>
	 * This Class is used to manage the Brick properties
	 * @author Mustafa Mehmood
	 * @version 0.1
	 */

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;



    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Constructor method of Brick which sets values of some fields , initializes some objects and calls methods required for brick.
     * @param name String Set name of brick
     * @param pos Point obj to set point of brick
     * @param size Dimension obj to set size of brick
     * @param border Color obj to set color of border
     * @param inner color obj to set color of brick
     * @param strength Int to set the strength ie the number of hits it can take before breaking
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * Abstract method to made the brick
     * @param pos Point obj to set position of brick
     * @param size Dimension obj to set size of brick
     * @return
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * Used to set Impact on the brick
     * @param point Point2D obj for the brick position
     * @param dir Int obj
     * @return returns a boolean to specify
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Abstract method to return the Shape of brick
     * @return
     */
    public abstract Shape getBrick();


    /**
     * getter method to return border color of brick
     * @return Color obj of brick
     */
    public Color getBorderColor(){
        return  border;
    }
    /**
     * getter method to return  color of brick
     * @return Color obj of brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Used to find the impact on brick
     * @param b ball object that hit brick
     * @return Int specifying impact direction
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * method used to check if brick is broken
     * @return Boolean specifying the same
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Used to repair bricks
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Used to impact brick
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * Used to generate random ness
     * @return
     */
    public static Random getRnd() {
        return rnd;
    }



}