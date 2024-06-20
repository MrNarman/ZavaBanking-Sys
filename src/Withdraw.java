import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Withdraw {
    JButton backButton;
    static JLabel withdrawBal;
    static JLabel withdrawAccNumber;
    static JTextField withdrawAmountTxtFld;
    JButton withdrawButton;

    public Withdraw(){
        JFrame withdrawFrame = new JFrame("WITHDRAW FUNDS");

        withdrawFrame.setVisible(true);
        withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        withdrawFrame.setLocationRelativeTo(null);
        withdrawFrame.setSize(400,350);
        withdrawFrame.setLayout(null);
        withdrawFrame.setResizable(false);

        JLabel balLabel = new JLabel("Balance Ksh: ");
        balLabel.setBounds(20, 20, 100, 30);

        withdrawBal = new JLabel("000000");
        withdrawBal.setBounds(120, 20, 200, 30);

        JLabel amount_to_withdrawLabel = new JLabel("Amount to withdraw:");
        amount_to_withdrawLabel.setBounds(20, 60, 150, 30);

        JLabel account_Number = new JLabel("Acc Number: ");
        account_Number.setBounds(20, 150, 110, 25);

        withdrawAccNumber = new JLabel("########");
        withdrawAccNumber.setBounds(120, 150, 150, 25);

        withdrawAmountTxtFld = new JTextField();
        withdrawAmountTxtFld.setBounds(20, 100, 100, 25);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(50, 270, 150, 30);
            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String getWithdrawAmount = withdrawAmountTxtFld.getText();
                    int withdrawBal_calculation = Integer.parseInt(String.valueOf(withdrawBal.getText()));

                    if (getWithdrawAmount.isEmpty()){
                        JOptionPane.showMessageDialog(null, "ENTER AMOUNT TO WITHDRAW.", "ERROR", JOptionPane.INFORMATION_MESSAGE);

                    }else if (Integer.parseInt(getWithdrawAmount) > Integer.parseInt(String.valueOf(withdrawBal.getText()))){
                        JOptionPane.showMessageDialog(null, "You have insufficient funds to withdraw Ksh "+getWithdrawAmount);

                    }else {
                        int amountUserToWithdraw = Integer.parseInt(getWithdrawAmount);
                        int remainingBalance = (withdrawBal_calculation) - (amountUserToWithdraw);

                        int confirmTransaction = JOptionPane.showConfirmDialog(null, "You are about to withdraw Ksh "+ getWithdrawAmount +"\n Do you wish to continue?","CONFIRM WITHDRAWAL", JOptionPane.YES_NO_OPTION);
                            if (confirmTransaction == JOptionPane.YES_OPTION){

                                Donate.add_Balance_To_Database((withdrawAccNumber.getText()), withdrawAmountTxtFld.getText());

                                try(BufferedReader wDrawReader = new BufferedReader(new FileReader("Accounts.txt"))){
                                    String fileLine;

                                    while((fileLine = wDrawReader.readLine()) != null){
                                        String[] parts = fileLine.split(" ");

                                        if (parts.length >=4){
                                            String wDraw_AccNumber = parts[2];
                                            String wDraw_AccBalance = parts[3];

                                            addBalanceToDatabase((withdrawAccNumber.getText()), wDraw_AccNumber, wDraw_AccBalance, (String.valueOf(remainingBalance)));
                                            withdrawBal.setText(String.valueOf(remainingBalance));
                                            MainScreen.accBalance.setText(String.valueOf(remainingBalance));
                                            withdrawAmountTxtFld.setText("");

                                            break;
                                        }
                                    }

                                } catch (IOException ex){
                                    JOptionPane.showMessageDialog(null, "An Error occurred!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }

                            } else if(confirmTransaction == JOptionPane.NO_OPTION){
                                JOptionPane.showMessageDialog(null, "You have canceled withdrawal of Ksh "+getWithdrawAmount);
                            }

                    }

                }
            });

        backButton = new JButton("BACK");
        backButton.setBackground(Color.RED);
        backButton.setBounds(310, 270, 70, 30);
        backButton.setForeground(Color.white);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    withdrawFrame.dispose();

                }
            });

        //Add components to frame
        withdrawFrame.add(balLabel);
        withdrawFrame.add(withdrawBal);
        withdrawFrame.add(amount_to_withdrawLabel);
        withdrawFrame.add(account_Number);
        withdrawFrame.add(withdrawAccNumber);
        withdrawFrame.add(withdrawAmountTxtFld);
        withdrawFrame.add(withdrawButton);
        withdrawFrame.add(backButton);
    }

    public static  void withdrawUpdateBalLabel(String withdrawAccBalance){ //This method sets the value of the withdrawal frame balance
        withdrawBal.setText(withdrawAccBalance);
    }

    public static void withdrawUpdateAccNumber(String withdrawalAccountNumber){ //This method sets the account number of the user to the withdrawal frame
        withdrawAccNumber.setText(withdrawalAccountNumber);

    }

    public static void addBalanceToDatabase(String account_Number,String partsAccNumber ,String oldAccBalance, String newAccBalance){
        try {
            //Read the entire file into a string builder
            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            reader.close();

            String content = builder.toString();
            if (account_Number.equals(partsAccNumber)){
                content = content.replaceAll("\\b"+ oldAccBalance+ "\\b", newAccBalance);

            }

            //Write the updated content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"));
            writer.write(content);
            writer.close();

            JOptionPane.showMessageDialog(null, "Withdrawal Successful");

        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "An Error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
