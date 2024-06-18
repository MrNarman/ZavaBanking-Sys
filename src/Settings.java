import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    static JLabel settingsAccNumber;
    JTextField newPinTxtField;
    JLabel oldPin;
    JButton backButton;
    JButton changePin;

    public  Settings(){
        JFrame settingsFrame = new JFrame();

        settingsFrame.setVisible(true);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setSize(300,300);
        settingsFrame.setLayout(null);
        settingsFrame.setResizable(false);

        JLabel account_numberTxt = new JLabel("Acc Number: ");
        account_numberTxt.setBounds(10, 30, 120, 25);

        settingsAccNumber = new JLabel("########");
        settingsAccNumber.setBounds(125, 30, 120, 25);

        JLabel old_pinTxt = new JLabel("Old Pin:");
        old_pinTxt.setBounds(10, 70, 120, 25);

        oldPin = new JLabel("XXX-XXX-XXX");
        oldPin.setBounds(130, 70, 120, 25);

        JLabel new_pinTxt = new JLabel("New Pin: ");
        new_pinTxt.setBounds(10, 100, 120, 25);

        newPinTxtField = new JTextField();
        newPinTxtField.setBounds(130, 100, 120, 25);

        changePin = new JButton("CHANGE PIN");
        changePin.setBounds(10, 210, 150, 30);
        changePin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        backButton = new JButton("BACK");
        backButton.setBackground(Color.RED);
        backButton.setBounds(210, 210, 70, 30);
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingsFrame.dispose();

            }
        });

        settingsFrame.add(account_numberTxt);
        settingsFrame.add(settingsAccNumber);
        settingsFrame.add(old_pinTxt);
        settingsFrame.add(oldPin);
        settingsFrame.add(new_pinTxt);
        settingsFrame.add(newPinTxtField);
        settingsFrame.add(changePin);
        settingsFrame.add(backButton);
    }

    public static void updateAccLabel(String getAccNumber){
        settingsAccNumber.setText(getAccNumber);
    }
    
}
