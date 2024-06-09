import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

                   /* updateLabelText updates the full name and account number on the mainScreen
                   * update balance label updates balance from corresponding account number
                   * balance is stored in a hashMap as an integer with the account number as its key*/

                   try(BufferedReader loginReader = new BufferedReader(new FileReader("Accounts.txt"))){
                      String fileLine;
                      boolean accountFound = false;
                      while((fileLine = loginReader.readLine()) != null){
                          String[] parts = fileLine.split(" ");

                          if (parts.length >=4 ){
                              String loginFName = parts[0];
                              String loginLName = parts[1];
                              String loginAccNumber = parts[2];
                              String loginAccBal = parts[3];
                              String loginPin = parts[4];

                              if (loginAccNumber.equals(userAccNumber) && loginPin.equals(userPinNumber)){
                                  new MainScreen();
                                  MainScreen.updateLabelText((loginFName+ " "+ loginLName).toUpperCase(), loginAccNumber);
                                  MainScreen.loginUpdateBalanceLabel(loginAccBal);
                                  loginFrame.dispose();

                                  accountFound = true;
                                  break;
                              }
                          }
                      }
                      if (!accountFound){
                          JOptionPane.showMessageDialog(loginFrame, "Account Does Not Exist.\nCheck that you have entered your Acc Number and your PIN correctly ", "ERROR", JOptionPane.ERROR_MESSAGE);
                      }
                   }catch (IOException ex){
                       JOptionPane.showMessageDialog(loginFrame, "User Does Not Exist", "ERROR", JOptionPane.ERROR_MESSAGE);

                   }

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
               final String adminPassword = "admin@ZavaBank";

               String adminInput = JOptionPane.showInputDialog(null, "Enter Admin password: " );

               if(adminPassword.equals(adminInput)){
                   new Admin();
                   loginFrame.dispose();
               } else {
                   JOptionPane.showMessageDialog(null, "Wrong Password you are not an Admin", "ERROR", JOptionPane.ERROR_MESSAGE);
               }
           }
       });

    //Adding components to main Panel
       mainPanel.add(new JLabel()); //for spacing
       mainPanel.add(new JLabel()); //for spacing
       //mainPanel.add(new JLabel()); //for spacing

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
