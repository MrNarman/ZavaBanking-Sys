import javax.swing.*;
public class App {
    public static void main(String[] args){
        Login loginObject = new Login();
        
        SwingUtilities.invokeLater(()-> loginObject.login_Frame()); //Ensures all components are displayed
    }
}
