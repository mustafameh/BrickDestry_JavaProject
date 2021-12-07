/**
 * <h1>Score Menu</h1>
 * This Class handles all the displaying of Score
 * User Can Select Exit to Close the Application
 *
 * @author Mustafa Mehmood
 * @version 0.1
 */
package game.Frames;

import game.OtherEntities.Scoring;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ScoreMenu extends JFrame implements ActionListener {

    JLabel text = new JLabel();
    ImageIcon background = new ImageIcon(getClass().getResource("/resources/brickwall.jpg"));

    ImageIcon exitIcon = new ImageIcon(getClass().getResource("/resources/exit.png"));
    JButton exitButton;
    Scoring score;
    /**
     * Constructor method of ScoreMenu which sets values of some fields and initializes some objects creates the frame and displays info
     */
    public ScoreMenu() {

        exitButton= new JButton(exitIcon);
        exitButton.setBounds(700,470,120,60);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);


        text.setIcon(background);

        text.setFont(new Font("Arial", Font.BOLD, 15));
        text.setForeground(Color.WHITE);

        score = new Scoring();

        text.setText("<html>"+  "<h1><u>Scores of Last 20 Matches </u></h1><br/>"
                +score.readScore(new File("Brick_Destroy-master\\src\\resources\\scoreList.txt"),20) +

                "</html>");


        text.setHorizontalTextPosition(JLabel.HORIZONTAL);


        this.add(exitButton);
        this.add(text);
        this.setSize(900,600);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }










    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();

    }
}
