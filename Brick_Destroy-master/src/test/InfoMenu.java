package test;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoMenu extends JFrame implements ActionListener {
    JLabel text = new JLabel();
    ImageIcon background = new ImageIcon(getClass().getResource("/resources/brickwall.jpg"));

    ImageIcon exitIcon = new ImageIcon(getClass().getResource("/resources/exit.png"));
    JButton exitButton;

    public InfoMenu() {
        exitButton= new JButton(exitIcon);
        exitButton.setBounds(700,470,120,60);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);


        text.setIcon(background);

        text.setFont(new Font("Arial", Font.BOLD, 19));
        text.setForeground(new Color(200,200,100));
        text.setText("<html>" +
                "<h1><u>About</u></h1><br/>" +
                "The object of the game is destroy the bricks by hitting them with the ball.<br/> You must keep the ball in play by moving the paddle with your mouse <br/> " +
                "<h1><u>How to play</u></h1><br/>" +
                "1. Break all the wall bricks to win the game. <br/>" +
                "2. A player has 3 Lives. <br/>" +
                "3. A life is lost when the ball hits the ground without touching paddle. <br/><br/>" +
                "<h1><u>Controls</u></h1><br/>" +
                "<b>Pause and Resume : SPACE <br/>" +
                "<b>Move Paddle Right : D <br/>" +
                "<b>Move Paddle Left : A <br/>" +
                "<b>Pause Menu : Esc <br/>" +
                "<b>Game Console: ALT+SHIFT+F1</b> </br>" +
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