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
        withdrawBal.setBounds(120, 20, 100, 30);

        JLabel amount_to_withdrawLabel = new JLabel("Amount to withdraw:");
        amount_to_withdrawLabel.setBounds(20, 60, 150, 30);

        withdrawAmountTxtFld = new JTextField();
        withdrawAmountTxtFld.setBounds(20, 100, 100, 30);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(50, 270, 150, 30);
            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });

        backButton = new JButton("BACK");
        backButton.setBackground(Color.RED);
        backButton.setBounds(310, 270, 70, 30);
        backButton.setForeground(Color.white);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

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
