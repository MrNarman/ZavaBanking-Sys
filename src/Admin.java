import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Admin extends JFrame {
    JButton backButton;
    JButton addAccountButton;
    JTextField accountNumberADMN;
    JTextField firstNameADMN;
    JTextField lastNameADMN;
    JTextField firstDeposit;
    JButton clearFields;
    JButton clearAccDatabase;
    JButton seeDatabase;

    public Admin(){
        JFrame adminFrame = new JFrame("Admin");

        adminFrame.setSize(400, 400);
        adminFrame.setVisible(true);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setResizable(false);
        adminFrame.setLayout(new GridLayout(15, 0));

        seeDatabase = new JButton("VIEW DATABASE");
        seeDatabase.setBackground(Color.BLUE);
        seeDatabase.setForeground(Color.WHITE);
        seeDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeAccDatabase();
            }
        });

        clearAccDatabase = new JButton("DELETE ALL ACCOUNTS");
        clearAccDatabase.setBackground(Color.RED);
        clearAccDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int yesOrNo = JOptionPane.showConfirmDialog(adminFrame, "You are about to delete all accounts. Do you wish to continue?");

                    if (yesOrNo == JOptionPane.YES_OPTION){

                        File databaseFile = new File("Accounts.txt");
                        databaseFile.delete();

                        JOptionPane.showMessageDialog(null, "All Accounts Successfully Deleted From Database");

                    } else if (yesOrNo == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Accounts have been Retained in the Database");
                    }
            }
        });

        JLabel fNameLabelADMN = new JLabel("First Name: ");
        firstNameADMN = new JTextField();

        JLabel lNameADMN = new JLabel("Last Name:");
        lastNameADMN = new JTextField();

        JLabel accNumberADM = new JLabel("Account Number: ");
        accountNumberADMN = new JTextField();

        JLabel firstDepoADMN = new JLabel("First Deposit");
        firstDeposit = new JTextField();

        addAccountButton = new JButton("ADD");
        addAccountButton.setBackground(Color.cyan);
        addAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accPinWindow = JOptionPane.showInputDialog(null, "Set a pin for the account: ", "ACCOUNT PIN", JOptionPane.INFORMATION_MESSAGE);

                if (accPinWindow != null){
                     String dbFName = firstNameADMN.getText();
                     String dbLName = lastNameADMN.getText();

                     String adminGetAccNo = accountNumberADMN.getText();
                     int adminGetFirstAmount = Integer.parseInt(firstDeposit.getText());

                     addDetailsToFile(dbFName, dbLName, adminGetAccNo, adminGetFirstAmount, accPinWindow);
                     JOptionPane.showMessageDialog(null, "Account "+ adminGetAccNo +" added with deposit Ksh "+ adminGetFirstAmount);

                } else{
                    JOptionPane.showMessageDialog(null, "Account PIN not set. User might not access account.\nGo back and set PIN", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearFields = new JButton("CLEAR");
        clearFields.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNameADMN.setText("");
                lastNameADMN.setText("");
                accountNumberADMN.setText("");
                firstDeposit.setText("");

            }
        });

        backButton = new JButton("BACK");
        backButton.setBackground(Color.GREEN);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                adminFrame.dispose();

            }
        });

        adminFrame.add(seeDatabase);
        adminFrame.add(clearAccDatabase);
        adminFrame.add(fNameLabelADMN);
        adminFrame.add(firstNameADMN);
        adminFrame.add(lNameADMN);
        adminFrame.add(lastNameADMN);
        adminFrame.add(accNumberADM);
        adminFrame.add(accountNumberADMN);
        adminFrame.add(firstDepoADMN);
        adminFrame.add(firstDeposit);
        adminFrame.add(new JLabel()); //For spacing
        adminFrame.add(addAccountButton);
        adminFrame.add(clearFields);
        adminFrame.add(new JLabel()); //For spacing
        adminFrame.add(backButton);

    }

    public void addDetailsToFile(String dbFirstName, String dbLastName, String dbAccNumber, int dbFirstDeposit, String pinNumber){
       try{
           BufferedWriter databaseWriter = new BufferedWriter(new FileWriter("Accounts.txt", true));
           databaseWriter.newLine();
           databaseWriter.write(dbFirstName+" "+ dbLastName+" "+ dbAccNumber+ " "+ dbFirstDeposit+" "+pinNumber);

           databaseWriter.close();
       }catch (IOException e){
           JOptionPane.showMessageDialog(null, "An Error Occurred!", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }

    public void seeAccDatabase(){

        String fileName = "Accounts.txt";
        StringBuilder fileContent = new StringBuilder();

        try(BufferedReader myFileReadObj = new BufferedReader (new FileReader(fileName))){
            String data = myFileReadObj.readLine();
            while(data != null){
                fileContent.append(data);
                fileContent.append("\n");
                data = myFileReadObj.readLine();

            }
            JOptionPane.showMessageDialog(null, fileContent.toString(), "Accounts", JOptionPane.INFORMATION_MESSAGE);
            myFileReadObj.close();

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occurred", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
}
