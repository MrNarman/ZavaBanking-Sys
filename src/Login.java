import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Login {
   public Login(){
    JFrame loginFrame = new JFrame("LOGIN");

    loginFrame.setVisible(true);
    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loginFrame.setLocationRelativeTo(null);
    loginFrame.setSize(600,500);
    loginFrame.setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(15,0));
    mainPanel.setBackground(Color.lightGray);

    //Main panel components
    JTextField fNameTxtField = new JTextField();
    JTextField lNameTxtField = new JTextField();
    JTextField accNoTxtField = new JTextField();
    JTextField pinTxtField = new JTextField();

       // login button
    JButton loginButton = new JButton("LOGIN");
            loginButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e){
                   String userFirstName = fNameTxtField.getText();
                   String userLastName = lNameTxtField.getText();
                   String userAccNumber = accNoTxtField.getText();
                   String userPinNumber = pinTxtField.getText();

                    new MainScreen();
                    MainScreen.updateLabelText((userFirstName+" "+ userLastName).toUpperCase(), userAccNumber);
                    MainScreen.updateBalanceLabel(userAccNumber);
                    loginFrame.dispose();

               } 
            });

        // clear fields button
    JButton clearFieldsBtn = new JButton("CLEAR FIELDS");
            clearFieldsBtn.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e){
                  fNameTxtField.setText("");
                  lNameTxtField.setText("");
                  accNoTxtField.setText("");
                  pinTxtField.setText("");   

               } 
            });

        //exit button
    JButton exitButton = new JButton("EXIT");
    exitButton.setBackground(Color.red);
    exitButton.setForeground(Color.black);
            exitButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e){
                loginFrame.dispose();

               }
            });

            //Admin Button
       JButton adminButton = new JButton("ADMIN");
       adminButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               new Admin();
               loginFrame.dispose();

           }
       });

    //Adding components to main Panel
    mainPanel.add(new JLabel("First Name:"));
    mainPanel.add(fNameTxtField);
    mainPanel.add(new JLabel("Last Name:"));
    mainPanel.add(lNameTxtField);
    mainPanel.add(new JLabel("Account Number:"));
    mainPanel.add(accNoTxtField);
    mainPanel.add(new JLabel("PIN: "));
    mainPanel.add(pinTxtField);

    mainPanel.add(new JLabel()); //for spacing

    mainPanel.add(loginButton);
    mainPanel.add(clearFieldsBtn);
    mainPanel.add(new JLabel()); // for spacing
    mainPanel.add(exitButton);
    mainPanel.add(new JLabel()); //for spacing
    mainPanel.add(adminButton);
   
    //Adding components to Frame
    loginFrame.add(mainPanel, BorderLayout.CENTER);

   }

}
