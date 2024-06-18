import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMoney {
    static JLabel sendMoneyAccNo;
    JTextField recipientAccTxtField;
    JTextField amountTxtField;
    JButton sendButton;
    JButton backButton;

    public SendMoney(){
        JFrame sendMoneyFrame = new JFrame();

        sendMoneyFrame.setVisible(true);
        sendMoneyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sendMoneyFrame.setLocationRelativeTo(null);
        sendMoneyFrame.setSize(300,300);
        sendMoneyFrame.setLayout(null);
        sendMoneyFrame.setResizable(false);

        JLabel account_numberTxt = new JLabel("Acc Number: ");
        account_numberTxt.setBounds(10, 30, 120, 25);

        sendMoneyAccNo = new JLabel("########");
        sendMoneyAccNo.setBounds(125, 30, 120, 25);

        JLabel recipient_AccTxt = new JLabel("Recipient Acc: ");
        recipient_AccTxt.setBounds(10, 60, 120, 25);

        recipientAccTxtField = new JTextField();
        recipientAccTxtField.setBounds(125, 60, 120, 25);

        JLabel amount_ToSendTxt = new JLabel("Amount: ");
        amount_ToSendTxt.setBounds(10, 100, 120, 25);

        amountTxtField = new JTextField();
        amountTxtField.setBounds(125, 100, 120, 25);

        sendButton = new JButton("CHANGE PIN");
        sendButton.setBounds(10, 210, 150, 30);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        backButton = new JButton("BACK");
        backButton.setBackground(Color.RED);
        backButton.setBounds(210, 210, 70, 30);
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMoneyFrame.dispose();

            }
        });

        sendMoneyFrame.add(account_numberTxt);
        sendMoneyFrame.add(sendMoneyAccNo);
        sendMoneyFrame.add(recipient_AccTxt);
        sendMoneyFrame.add(recipientAccTxtField);
        sendMoneyFrame.add(amount_ToSendTxt);
        sendMoneyFrame.add(amountTxtField);
        sendMoneyFrame.add(sendButton);
        sendMoneyFrame.add(backButton);


    }

    public static void updateAccLabel(String getAccNumber){
        sendMoneyAccNo.setText(getAccNumber);
    }
    
}
