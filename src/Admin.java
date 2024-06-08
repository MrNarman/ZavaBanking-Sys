import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    JButton backButton;
    JButton addAccountButton;
    JTextField accountNumberADMN;
    JTextField firstNameADMN;
    JTextField lastNameADMN;
    JTextField firstDeposit;
    JButton clearFields;
    JButton clearAccDatabase;

    public Admin(){
        JFrame adminFrame = new JFrame("Admin");

        adminFrame.setSize(400, 400);
        adminFrame.setVisible(true);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setResizable(false);
        adminFrame.setLayout(new GridLayout(15, 0));

        clearAccDatabase = new JButton("DELETE ALL ACCOUNTS");
        clearAccDatabase.setBackground(Color.RED);
        clearAccDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int yesOrNo = JOptionPane.showConfirmDialog(adminFrame, "You are about to delete all accounts. Do you wish to continue?");
                
                    if (yesOrNo == JOptionPane.YES_OPTION){
                        MainScreen.accountBalanceHMap.clear();
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
                String adminGetAccNo = accountNumberADMN.getText();
                int adminGetFirstAmount = Integer.parseInt(firstDeposit.getText());
                updateHashMapDetails(adminGetAccNo, adminGetFirstAmount);

                JOptionPane.showMessageDialog(null, "Account "+ adminGetAccNo +" added with deposit Ksh "+ adminGetFirstAmount);

            }
        });

        clearFields = new JButton("CLEAR");
        clearFields.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNameADMN.setText("");
                lastNameADMN.setText("");
                accountNumberADMN.setText("");

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

    public void updateHashMapDetails(String bankAccNumber, int firstDepositAmount){
        MainScreen.accountBalanceHMap.put(bankAccNumber, firstDepositAmount);

    }
}
