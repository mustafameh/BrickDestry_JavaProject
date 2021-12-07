///**
// * <h1>Home Menu</h1>
// * This Class is used to manage Saving, writing and getting the highScore
// *
// * @author Mustafa Mehmood
// * @version 0.1
// */
//
//
//
//
//package game.OtherEntities;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//
//public class HighScore {
//
//    FileReader readFile;
//
//
//    /**
//     * This method is used
//     * @return returns a string with high score
//     */
//    public String getHighScore() {
//        BufferedReader reader = null;
//        try {
//            readFile = new FileReader("highscore\\src\\resources\\scoreList.txt");
//            reader = new BufferedReader(readFile);
//            return reader.readLine();
//        }
//        catch(Exception e) {
//            return "Nobody:0";
//        }
//        finally {
//            try {
//                if(reader != null)
//                    reader.close();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//
//
//}
//
//
//    public void CheckScore(int gamescore) {
//        System.out.print(highScore);
//        System.out.print(Integer.parseInt((highScore.split(":")[1])));
//        System.out.print(gamescore);
//        //String name =  JOptionPane.showInputDialog("You have set a new highscore. What is your name?");
//        if (gamescore > Integer.parseInt((highScore.split(":")[1]))) {
//            System.out.println("hi");
//            //user sets a new record
//            String name =  JOptionPane.showInputDialog("You have set a new highscore. What is your name?");
//            //String name =  JOptionPane.showInputDialog("You have set a new highscore. What is your name?");
//            this.highScore = name + ":" + gamescore;
//
//            File scoreFile = new File("highscore.dat");
//            //System.out.println(scoreFile.getAbsolutePath());
//            if(!scoreFile.exists()) {
//                try {
//                    scoreFile.createNewFile();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//            FileWriter writeFile = null;
//            BufferedWriter writer = null;
//            try
//            {
//                writeFile = new FileWriter(scoreFile);
//                writer = new BufferedWriter(writeFile); // Channges file into writable file
//                writer.write(highScore); // writes highesty\ score into  the file
//            }
//            catch(Exception e) {
//                //errors
//            }
//            finally {
//                try {
//                    if(writer != null)
//                        writer.close();
//                }
//                catch (Exception e) {}
//
//            }
//
//        }
//    }public String GetHighScore() {
//    FileReader readFile = null;
//    BufferedReader reader = null;
//    try {
//        readFile = new FileReader("highscore.dat");
//
//
//        reader = new BufferedReader(readFile);
//        return reader.readLine();
//    }
//    catch(Exception e) {
//        return "
//        ";
//    }
//    finally {
//        try {
//            if(reader != null)
//                reader.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//}
