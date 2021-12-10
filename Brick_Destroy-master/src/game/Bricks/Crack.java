package game.Bricks;

import game.Bricks.Brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
/**
 * <h1>Crack </h1>
 * This Class is used to manage the Crack properties
 * @author Mustafa Mehmood
 * @version 0.1
 */
public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;


    private final Brick brick;
    private GeneralPath crack;

    private int crackDepth;
    private int steps;

    /**
     * Constructor method for Crack class used to initialize some properties for functioning of Crack
     * @param brick Brick Obj to specify the brick needed to be cracked
     * @param crackDepth Int specifuing the crack depth
     * @param steps Int Specifying the steps
     */
    public Crack(Brick brick, int crackDepth, int steps) {
        this.brick = brick;

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }

    /**
     * Used for drawing
     * @return generalpath obj
     */
    public GeneralPath draw() {

        return crack;
    }

    /**
     * Used to reset the carck
     */
    public void reset() {
        crack.reset();
    }

    /**
     * Used to make the crack in brick
     * @param point Point2D obj to specify the location of crack
     * @param direction int to specify direction for crack
     */
    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /**
     * Used to make the crack in brick
     * @param start Point obj for crack
     * @param end point obj for crack
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /**
     * Used for random in bounds
     * @param bound Int
     * @return int
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }

    /**
     * Used to check if in Middle
     * @param i Int
     * @param steps Int
     * @param divisions Int
     * @return boolean
     */
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /**
     * used for jumps in crack
     * @param bound Int
     * @param probability Double
     * @return Int
     */
    private int jumps(int bound, double probability) {

        if (Brick.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * Used to make random points in crack
     * @param from Point obj
     * @param to Point obj
     * @param direction Int
     * @return point
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = Brick.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = Brick.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }



}
