import javax.swing.JFrame;

public class MainScreen {
    public void main_Screen(){
        JFrame mainScreen = new JFrame("Main Screen");

        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setSize(600,500);
        
    }
    
}
