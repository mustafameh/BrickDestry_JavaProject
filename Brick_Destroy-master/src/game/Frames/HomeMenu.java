


package game.Frames;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <h1>Home Menu</h1>
 * This Class handles all the things that happen in the Home Menu
 * User Can Select Start to Launch the game.
 * User Can Select Exit to Close the Application
 * User Can Select History To check Score History
 * User Can
 *
 * @author Mustafa Mehmood
 * @version 0.1
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    BufferedImage BG_IMAGE; //stores background image of menu
    BufferedImage LOGO;     //Stores the logo


    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";
    private static final String SCORE_TEXT = "History";

    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = new Color(16, 52, 166);
    private static final Color CLICKED_TEXT = new Color(16, 52, 166);
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;
    private Rectangle infoButton;
    private Rectangle scoreButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean exitClicked;
    private boolean infoClicked;
    private boolean scoreClicked;


    /**
     * This is the constructor of HomeMenu which sets values of some fields and initializes some objects
     * @param owner  First parameter to constructor takes a Gameframe Object
     * @param area  Second parameter to constructor takes A Dimension Object used to set size of Frame
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12); //sets the size of Start Button and Menu Button relative to size of the frame
        startButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        scoreButton= new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);


        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);

    }

    /**
     * This method is used to cast Graphics g Obj to g2d obj
     * @param g The only parameter a graphics g obj to paint method
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g; //casting graphics g obj as g2d
        drawMenu(g2d);
    }


    /**
     * This Method is used to display menu makeing use of drawText, drawButton, drawBackground Methods
     * @param g2d First parameter takes in a graphics2D object
     */
    public void drawMenu(Graphics2D g2d){

        drawBackground(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * This Method is used to display the background image of the HomMenu
     * @param g2d First parameter takes in a graphics2D object
     */

    private void drawBackground(Graphics2D g2d){


        try{
            BG_IMAGE= ImageIO.read(getClass().getResource("/resources/brickwall.jpg"));
        }catch(IOException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}

        g2d.drawImage(BG_IMAGE, 0, 0, null);


    }

    /**
     * This method is used to display the logo of the game on to the Home Menu
     * @param g2d First parameter takes in a graphics2D object
     */
    private void drawText(Graphics2D g2d){
        try{
            LOGO= ImageIO.read(getClass().getResource("/resources/logo.png"));
        }catch(IOException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}



        g2d.setColor(TEXT_COLOR);

        g2d.drawImage(LOGO, 220, 0, null);


    }

    /**
     * This method is used to display the Start Exit Info And History button on the HomeMenu
     * @param g2d First parameter takes in a graphics2D object
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D txtRect2 = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D txtRect3 = buttonFont.getStringBounds(SCORE_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.55);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton); //draw rectangle
            g2d.drawString(START_TEXT,x,y);//draw text
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        exitButton.setLocation(x,y);




        x = (int)(exitButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (startButton.height * 0.9);

        if(exitClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }
        x = exitButton.x;
        y = exitButton.y;

        y *= 1.15;

        infoButton.setLocation(x,y);




        x = (int)(infoButton.getWidth() - txtRect2.getWidth()) / 2;
        y = (int)(infoButton.getHeight() - txtRect2.getHeight()) / 2;

        x += infoButton.x;
        y += infoButton.y + (startButton.height * 0.9);

        if(infoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }
        x = infoButton.x;
        y = infoButton.y;

        y *= 1.15;

        scoreButton.setLocation(x,y);




        x = (int)(scoreButton.getWidth() - txtRect3.getWidth()) / 2;
        y = (int)(scoreButton.getHeight() - txtRect3.getHeight()) / 2;

        x += scoreButton.x;
        y += scoreButton.y + (scoreButton.height * 0.9);

        if(scoreClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(SCORE_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(scoreButton);
            g2d.drawString(SCORE_TEXT,x,y);
        }



    }





    /**
     * Starts the Game Board when startButton is clicked exists when exitButton is clicked launchs infoMenu when info button is clicked and Score Menu when Score button is clicked
     * @param mouseEvent First parameter as MouseEvent Obj  represents events that occur due to the user interacting with a mouse click
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if(exitButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(infoButton.contains(p)){
            new InfoMenu();
        }
        else if(scoreButton.contains(p)){
            new ScoreMenu();
        }
    }

    /**
     * Changes the color of buttons when the buttons are pressed to indicate the event
     * @param mouseEvent First parameter as MouseEvent Obj  represents events that occur due to the user interacting with a mouse click
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(exitButton.contains(p)){
            exitClicked = true;
            repaint(exitButton.x, exitButton.y, exitButton.width+1, exitButton.height+1);
        }
        else if(infoButton.contains(p)){
            infoClicked = true;
            repaint(infoButton.x, infoButton.y, infoButton.width+1, infoButton.height+1);
        }
        else if(scoreButton.contains(p)){
            scoreClicked = true;
            repaint(scoreButton.x, scoreButton.y, scoreButton.width+1, scoreButton.height+1);
        }
    }
    /**
     * Changes the color of buttons when the buttons are released to indicate the event.
     * @param mouseEvent First parameter as MouseEvent Obj  represents events that occur due to the user interacting with a mouse click.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(exitClicked){
            exitClicked = false;
            repaint(exitButton.x, exitButton.y, exitButton.width+1, exitButton.height+1);
        }
        else if(infoClicked){
            infoClicked = false;
            repaint(infoButton.x, infoButton.y, infoButton.width+1, infoButton.height+1);
        }
        else if(scoreClicked){
            scoreClicked = false;
            repaint(scoreButton.x, scoreButton.y, scoreButton.width+1, scoreButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * This metod checks mouse movement and the postion to invoke setCursor methods.
     * @param mouseEvent First parameter as MouseEvent Obj  represents events that occur due to the user interacting with a mouse click.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || exitButton.contains(p) || infoButton.contains(p) || scoreButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
