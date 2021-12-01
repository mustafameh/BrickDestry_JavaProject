package game;

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

    public ScoreMenu() {

        exitButton= new JButton(exitIcon);
        exitButton.setBounds(700,470,120,60);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);


        text.setIcon(background);

        text.setFont(new Font("Arial", Font.BOLD, 19));
        text.setForeground(Color.WHITE);

        score = new Scoring();

        text.setText("<html>" +score.readScore(new File("Brick_Destroy-master\\src\\resources\\scoreList.txt"),10) +

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
