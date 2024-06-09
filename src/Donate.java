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
