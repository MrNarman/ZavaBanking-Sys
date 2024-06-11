import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw {
    JButton backButton;
    static JLabel withdrawBal;
    JTextField withdrawAmountTxtFld;
    JButton withdrawButton;

    public Withdraw(){
        JFrame withdrawFrame = new JFrame("WITHDRAW FUNDS");

        withdrawFrame.setVisible(true);
        withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                                /*
                                 INSERT CODE FOR WITHDRAWAL FUNCTION HERE
                                 Code to insert: Updating database with the remaining balance after withdrawal
                                 Code to remove: JOptionPane.showMessageDialog
                                */
                                JOptionPane.showMessageDialog(null, "Balance: "+withdrawBal_calculation+" Withdraw: "+getWithdrawAmount +" Balance: "+remainingBalance);
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
                    new MainScreen();
                    withdrawFrame.dispose();

                }
            });


        //Add components to frame
        withdrawFrame.add(balLabel);
        withdrawFrame.add(withdrawBal);
        withdrawFrame.add(amount_to_withdrawLabel);
        withdrawFrame.add(withdrawAmountTxtFld);
        withdrawFrame.add(withdrawButton);
        withdrawFrame.add(backButton);
    }

    public static  void withdrawUpdateBalLabel(String withdrawAccBalance){
        withdrawBal.setText(withdrawAccBalance);
    }

}
