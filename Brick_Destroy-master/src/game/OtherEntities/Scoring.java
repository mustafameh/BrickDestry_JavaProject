/**
 * <h1>Scoring </h1>
 * This Class is used to manage Saving, writing and getting the highScore and the Score History
 *
 * @author Mustafa Mehmood
 * @version 0.1
 */
package game.OtherEntities;

import java.io.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Scoring {

    private String txt1; //txt to be written

    /**
     * The Write Score method is Used to write The score of the player on a txt file. It asks for The name to be stored along with their score
     * @param score Int parameter which is the score that is to be stored
     */
    public void writeScore(int score){

        JFrame frame = new JFrame("User Name Storing");
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Enter User Name");

        final JLabel label = new JLabel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = (String)JOptionPane.showInputDialog(
                        frame,
                        "Type Down Your Player Name",
                        "Store User Name",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Default Name"
                );
                if(userName != null && userName.length() > 0){
                    label.setText("You selected:" + userName);
                }else {
                    label.setText("None selected");
                }
                try{
                    txt1 = "\n" + score + " by " + userName;
                    FileWriter fw=new FileWriter("Brick_Destroy-master\\src\\resources\\scoreList.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(txt1);
                    bw.close();
                }catch(Exception xyz){System.out.println(xyz);}
                System.out.println("Success...");
//getClass().getResource("/resources/brickwall.jpg")
            }
        });

        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);


    }

    /**
     * Used to read the file storing the scores and the names
     * @param file File Parameter to specify the location of file to be read
     * @param lines int parameter The number of lines to be read from bottom to top of the file
     * @return
     */
    public String readScore(File file, int lines){
        int readLines = 0;
        StringBuilder builder = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length() - 1;
            // Set the pointer at the last of the file
            randomAccessFile.seek(fileLength);
            for(long pointer = fileLength; pointer >= 0; pointer--){
                randomAccessFile.seek(pointer);
                char c;
                // read from the last one char at the time
                c = (char)randomAccessFile.read();
                // break when end of the line
                if(c == '\n'){
                    builder.append(">/rb<"); //writing reverse of </br> because file is being read back words and then reverced
                    readLines++;
                    if(readLines == lines)
                        break;
                }
                builder.append(c);
            }
            // Since line is read from the last so it
            // is in reverse so use reverse method to make it right
            builder.reverse();
            return  builder.toString() ;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "Error loading file";
    }



}
