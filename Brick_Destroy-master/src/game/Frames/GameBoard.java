

package game.Frames;

import game.Balls.Ball;
import game.Bricks.Brick;
import game.Debugger.DebugConsole;
import game.OtherEntities.Wall;
import game.OtherEntities.Player;
import game.OtherEntities.Scoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.text.DecimalFormat;

/**
 * This Class Handles creation and management of GameBoard
 *  * @author Mustafa Mehmood
 *  * @version 0.1
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;

    private int currentLevel;



    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private Wall wall;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    Timer timer;
    String timertxt;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    private DebugConsole debugConsole;


    /**
     * Constructor method of gameBoard which sets values of some fields , initializes some objects and calls methods required to start game
     * @param owner First parameter to constructor takes a Jframe Object
     */

    public GameBoard(JFrame owner){
        super();


        strLen = 0;
        showPauseMenu = false;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,4,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wall,this);
        initiateTimer();

        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();

            message = String.format("Bricks: %d Balls %d Score: %d, Timer: %s",wall.getBrickCount(),wall.getBallCount(),wall.getScore(), timertxt);

            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = String.format("Player Scored  : %d",wall.getScore());

                //call function to write score on file
                    Scoring scoring= new Scoring();
                    scoring.writeScore(wall.getScore());


                wall.setScore(0);
                    timer.stop();
                    initiateTimer();



                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    if(wall.getLevel() == 7) {
                        timer.start();
                    }


                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    Scoring xyz = new Scoring();
                    xyz.writeScore(wall.getScore());
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }

    /**
     * Initilized GameBoard
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Initilizes the values for timer
     */
    public void initiateTimer() {

        second =0;
        minute = 5;
        countdownTimer();
    }

    /**
     * Handles all the logic for the timer of last level
     */
    public void countdownTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                timertxt = ddMinute + ":" + ddSecond;
                System.out.println(timertxt);

                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    timertxt = ddMinute + ":" + ddSecond;
                }
                if(minute==0 && second==0) {
                    message = "The time is over...";
                    Scoring xyz = new Scoring();
                    xyz.writeScore(wall.getScore());
                    timer.stop();
                    initiateTimer();
                    gameTimer.stop();
                    wall.setScore(0);
                    wall.ballReset();
                    wall.wallReset();
                    repaint();

                }
            }
        });
    }


    /**
     * Draws Ball, DrawsPlayer, Draws wall on to the gameBoard
     * @param g First parameter takes in A Graphics object
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        drawBall(wall.ball,g2d);

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * sets Color
     * @param g2d  First parameter takes in A Graphics2D object
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Responsible for drawing bricks on the gameboard
     * @param brick First parameter takes in A Brick object
     * @param g2d Second parameter takes in A Graphics2D object
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Responsible for drawing ball on the gameboard
     * @param ball First parameter takes in A Ball object
     * @param g2d Second parameter takes in A Graphics2D object
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }


    /**
     * Responsible for drawing bricks on the gameboard
     * @param p First parameter takes in A player object
     * @param g2d Second parameter takes in A Graphics2D object
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Responsible for drawing pause menu and obscuregameBoard on GameBoard
     * @param g2d parameter takes in A Graphics2D object
     */

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * obscure Game Board method
     * @param g2d parameter takes in A Graphics2D object
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Responsible for drawing pause menu on the GameBoard
     * @param g2d parameter takes in A Graphics2D object
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Handles all the keyPress events
     * @param keyEvent Takes KeyEvent Object as parameter
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                gameTimer.stop();
                timer.stop();
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning()) {
                        timer.stop();
                        gameTimer.stop();
                    }
                    else
                    if(wall.getLevel() <= 6) {
                        gameTimer.start();
                    }else {
                        gameTimer.start();
                        timer.start();
                    }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.player.stop();
        }
    }
    /**
     * Handles all the keyRelease events
     * @param keyEvent Takes KeyEvent Object as parameter
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }
    /**
     * Handles all the MouseClick events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            System.out.println(wall.getLevel());
            if(wall.getLevel()<=7) {
                currentLevel = wall.getLevel();
            }

            if(currentLevel == 7)
            {
                timertxt = "03:00";
                second =0;
                minute =3;
                countdownTimer();

            }
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.setScore(0);
            wall.wallReset();
            showPauseMenu = false;

            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    /**
     * Handles all the MousePress events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
    /**
     * Handles all the MouseRelease events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
    /**
     * Handles all the MouseEnter events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    /**
     * Handles all the MouseExit events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * Handles all the MouseDrag events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
    /**
     * Handles all the MouseMove events
     * @param mouseEvent Takes MouseEvent Object as parameter
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Handles the event where in window is out of focus
     */
    public void onLostFocus(){
        gameTimer.stop();
        timer.stop();
        message = "Focus Lost";
        repaint();
    }



}
