/** This Class handles all the things that happen in the Home Menu where user can select the Start or Exit
 * @author Mustafa Mehmood
 */




package test;


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


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    BufferedImage BG_IMAGE;
    BufferedImage LOGO;


    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = new Color(16, 52, 166);
    private static final Color CLICKED_TEXT = new Color(16, 52, 166);
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle exitButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont ;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean exitClicked;


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

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);


        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);
        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);;
        creditsFont= new Font("Monospaced",Font.PLAIN,10);





    }


    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g; //casting graphics g obj as g2d
        drawMenu(g2d);
    }

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

    private void drawBackground(Graphics2D g2d){


        try{
            BG_IMAGE= ImageIO.read(getClass().getResource("/resources/brickwall.jpg"));
        }catch(IOException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}

        g2d.drawImage(BG_IMAGE, 0, 0, null);



//        Color prev = g2d.getColor();
//
//        g2d.setColor(BG_COLOR);
//        g2d.fill(menuFace);
//
//        Stroke tmp = g2d.getStroke();
//
//        g2d.setStroke(borderStoke_noDashes);
//        g2d.setColor(DASH_BORDER_COLOR);
//        g2d.draw(menuFace);
//
//        g2d.setStroke(borderStoke);
//        g2d.setColor(BORDER_COLOR);
//        g2d.draw(menuFace);
//
//        g2d.setStroke(tmp);
//
//        g2d.setColor(prev);
    }

    private void drawText(Graphics2D g2d){
        try{
            LOGO= ImageIO.read(getClass().getResource("/resources/logo.png"));
        }catch(IOException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}



        g2d.setColor(TEXT_COLOR);

//        FontRenderContext frc = g2d.getFontRenderContext();
//
//        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
//        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
//        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);
//
//        int sX,sY;
//
//        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
//        sY = (int)(menuFace.getHeight() / 4);
//
//        g2d.setFont(greetingsFont);
//        g2d.drawString(GREETINGS,sX,sY);
//
//        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
//        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings
//
//        g2d.setFont(gameTitleFont);
//        g2d.drawString(GAME_TITLE,sX,sY);
//
//        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
//        sY += (int) creditsRect.getHeight() * 1.1;
//
//        g2d.setFont(creditsFont);
//        g2d.drawString(CREDITS,sX,sY);

        g2d.drawImage(LOGO, 200, 50, null);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(EXIT_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.65);

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

    }





    /**
     * Starts the Game Board when startButton is clicked exists when menuButton is clicked
     * @param mouseEvent
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
    }

    /**
     * Changes the color of buttons
     * @param mouseEvent
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
    }

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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || exitButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}