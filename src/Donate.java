import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Donate {
    JButton backButton;
    static JLabel donateBal;
    JTextField donateAmountTxtFld;
    JButton donateButton;

    public Donate(){
        JFrame donateFrame = new JFrame();

        donateFrame.setVisible(true);
        donateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        donateFrame.setLocationRelativeTo(null);
        donateFrame.setSize(400,350);
        donateFrame.setLayout(null);

        JLabel balLabel = new JLabel("Balance Ksh: ");
        balLabel.setBounds(20, 20, 100, 30);

        donateBal = new JLabel("000000");
        donateBal.setBounds(120, 20, 100, 30);

        JLabel amount_to_withdrawLabel = new JLabel("Amount to Donate:");
        amount_to_withdrawLabel.setBounds(20, 60, 150, 30);

        donateAmountTxtFld = new JTextField();
        donateAmountTxtFld.setBounds(20, 100, 100, 30);

        donateButton = new JButton("DONATE");
        donateButton.setBounds(50, 270, 150, 30);
        donateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String getDonateAmount = donateAmountTxtFld.getText();
                int donateBal_calculation = Integer.parseInt(String.valueOf(donateBal.getText()));

                if (getDonateAmount.isEmpty()){
                    JOptionPane.showMessageDialog(null, "ENTER AMOUNT TO DONATE.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else if(Integer.parseInt(getDonateAmount) > Integer.parseInt(String.valueOf(donateBal.getText()))){
                    JOptionPane.showMessageDialog(null, "You have insufficient funds to donate Ksh "+getDonateAmount);
                }else{
                    int amountUserToDonate = Integer.parseInt(getDonateAmount);
                    int remainingBalance = (donateBal_calculation) - (amountUserToDonate);

                    int confirmDonation = JOptionPane.showConfirmDialog(null, "You are about to donate Ksh "+ getDonateAmount +"\n Do you wish to continue?","CONFIRM WITHDRAWAL", JOptionPane.YES_NO_OPTION);
                        if (confirmDonation == JOptionPane.YES_OPTION){
                                    /*
                                     INSERT CODE FOR DONATION FUNCTION HERE
                                     Code to insert: Updating database with the remaining balance after donation
                                     Code to remove: JOptionPane.showMessageDialog
                                    */
                            JOptionPane.showMessageDialog(null, "Balance: "+donateBal_calculation+" Withdraw: "+getDonateAmount +" Balance: "+remainingBalance);
                        } else if(confirmDonation == JOptionPane.NO_OPTION){
                            JOptionPane.showMessageDialog(null, "You have canceled withdrawal of Ksh "+getDonateAmount);
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
                donateFrame.dispose();

            }
        });

        //Add components to frame
        donateFrame.add(balLabel);
        donateFrame.add(donateBal);
        donateFrame.add(amount_to_withdrawLabel);
        donateFrame.add(donateAmountTxtFld);
        donateFrame.add(donateButton);
        donateFrame.add(backButton);
    }

    public static  void donateUpdateBalLabel(String donateAccLabel){
        donateBal.setText(donateAccLabel);

    }

}
