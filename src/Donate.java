import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Donate {
    JButton backButton;
    static JLabel donateBal;
    static JLabel donateAccNumber;
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

        JLabel amount_to_DonateLabel = new JLabel("Amount to Donate:");
        amount_to_DonateLabel.setBounds(20, 60, 150, 30);

        JLabel amount_to_donate = new JLabel("Acc Number: ");
        amount_to_donate.setBounds(20, 150, 110, 25);

        donateAccNumber = new JLabel("########");
        donateAccNumber.setBounds(120, 150, 150, 25);

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
                                     Code to remove: JOptionPane.
                                     
                                    */

                            try(BufferedReader donateReader = new BufferedReader(new FileReader("Accounts.txt"))){
                                String fileLine;

                                while((fileLine = donateReader.readLine()) != null){
                                    String[] parts = fileLine.split(" ");

                                    if (parts.length >=4){
                                        String donate_AccBalance = parts[3];

                                        Withdraw.addBalanceToDatabase((donateAccNumber.getText()), donate_AccBalance, (String.valueOf(remainingBalance))); //
                                        donateBal.setText(String.valueOf(remainingBalance));
                                        MainScreen.accBalance.setText(String.valueOf(remainingBalance));
                                        donateAmountTxtFld.setText("");

                                        break;
                                    }
                                }

                            } catch (IOException ex){
                                JOptionPane.showMessageDialog(null, "An Error occurred!", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }


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
                donateFrame.dispose();

            }
        });

        //Add components to frame
        donateFrame.add(balLabel);
        donateFrame.add(donateBal);
        donateFrame.add(amount_to_DonateLabel);
        donateFrame.add(donateAmountTxtFld);
        donateFrame.add(amount_to_donate);
        donateFrame.add(donateAccNumber);
        donateFrame.add(donateButton);
        donateFrame.add(backButton);
    }

    public static  void donateUpdateBalLabel(String donateAccLabel){
        donateBal.setText(donateAccLabel);

    }

    public static void donateUpdateAccNumber(String donationAccountNumber){
        donateAccNumber.setText(donationAccountNumber);
    }

}
