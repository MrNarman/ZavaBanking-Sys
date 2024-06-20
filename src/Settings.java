import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Settings {
    static JLabel settingsAccNumber;
    static JTextField newPinTxtField;
    static JLabel oldPin;
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
                updatePinToDB((settingsAccNumber.getText()), (oldPin.getText()), (newPinTxtField.getText()));

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

    public static void updatePinLabel(String getAccNo_for_Pin){

        try(BufferedReader settingsReader = new BufferedReader(new FileReader("Accounts.txt"))){
            String fileLine;
            while((fileLine = settingsReader.readLine()) != null){
                String[] parts = fileLine.split(" ");

                if (parts.length >= 4){
                    String settingsAccNumber = parts[2];
                    String settingsPin = parts[4];

                    if (settingsAccNumber.equals(getAccNo_for_Pin)){
                        oldPin.setText(String.valueOf(parts[4]));

                        break;
                    }

                }
            }

        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "A Error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    public  static void updatePinToDB(String account_number, String old_Pin, String newPin){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            reader.close();

            //Replace old pin with new pin
            old_Pin = oldPin.getText(); //Pin displayed on the screen

            String content = builder.toString();
            if (settingsAccNumber.getText().equals(account_number)){
                content = content.replaceAll("\\b"+ old_Pin+ "\\b", newPin);
            }

            //write the updated content back to file
            BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"));
            writer.write(content);
            writer.close();

            JOptionPane.showMessageDialog(null, "PIN successfully changed for Acc: "+ settingsAccNumber.getText());
            newPinTxtField.setText("");

        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "An Error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
}
