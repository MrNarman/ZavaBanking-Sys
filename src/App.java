import javax.swing.*;
public class App {
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> new Login()); //Ensures all components are displayed
    }
}
