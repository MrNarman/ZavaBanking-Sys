import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SendMoney {
    static JLabel sendMoneyBal;
    static JLabel sendMoneyAccNo;
    static JTextField recipientAccTxtField;
    static JTextField amountTxtField;
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

        JLabel acc_balanceTxt = new JLabel("Balance: ");
        acc_balanceTxt.setBounds(10, 15, 120, 25);

        sendMoneyBal = new JLabel("#######");
        sendMoneyBal.setBounds(125, 15, 120, 25);

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

        sendButton = new JButton("SEND MONEY");
        sendButton.setBounds(10, 210, 150, 30);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if ((recipientAccTxtField.getText()).isEmpty() || (amountTxtField.getText()).isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter Required Fields", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(amountTxtField.getText()) > Integer.parseInt(sendMoneyBal.getText())) {
                    JOptionPane.showMessageDialog(null, "You have insufficient funds to transfer Ksh "+(amountTxtField.getText()));
                }else{
                    int transfer_amount = Integer.parseInt(amountTxtField.getText());
                    int remaining_amount = (Integer.parseInt(sendMoneyBal.getText())) - (transfer_amount);

                    int confirmTransfer = JOptionPane.showConfirmDialog(null, "You are about to transfer Ksh "+ (amountTxtField.getText())+ " to account "+ (recipientAccTxtField.getText())+ "\n Do you wish to continue?", "Transfer Confirmation", JOptionPane.YES_NO_OPTION );
                        if (confirmTransfer == JOptionPane.YES_OPTION){

                            try(BufferedReader sendReader = new BufferedReader(new FileReader("Accounts.txt"))){
                                String fileLine;

                                while((fileLine = sendReader.readLine()) != null){
                                    String[] parts = fileLine.split(" ");

                                    if (parts.length >= 4){
                                        String oldBalance = parts[3];

                                        updateSenderBal_DB((sendMoneyAccNo.getText()), oldBalance, String.valueOf(remaining_amount));
                                        updateRecipientBal_DB((recipientAccTxtField.getText()), (amountTxtField.getText()));
                                        sendMoneyBal.setText(String.valueOf(remaining_amount));
                                        MainScreen.accBalance.setText(String.valueOf(remaining_amount));
                                        recipientAccTxtField.setText("");
                                        amountTxtField.setText("");

                                        break;
                                    }
                                }

                            } catch (IOException ex){
                                JOptionPane.showMessageDialog(null, "An Error occurred!", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }




                        } else if (confirmTransfer == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "You have canceled transfer of Ksh "+ (amountTxtField.getText()));
                        }
                }


                //Validate if recipient exists
                //update database with balances

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

        sendMoneyFrame.add(acc_balanceTxt);
        sendMoneyFrame.add(sendMoneyBal);
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

    public static void updateBalLabel(String getBalance){
        sendMoneyBal.setText(getBalance);
    }

    public static void updateSenderBal_DB(String account_number, String old_balance, String new_balance){
        try{
            //Read the entire file into a string builder
            BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
            reader.close();

            //Replace old value with new value
            old_balance = sendMoneyBal.getText(); //Balance  showing on the frame

            String content = builder.toString();
            if (sendMoneyAccNo.getText() == account_number){
                content = content.replaceAll("\\b"+ old_balance+ "\\b", new_balance);

            }

            //Write the updated content back to file
            BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"));
            writer.write(content);
            writer.close();

            //Probable place for the update recipient

            JOptionPane.showMessageDialog(null, "Transfer Successful");


        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "An Error occurred", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void updateRecipientBal_DB(String recipient_accNumber, String amount_forTransfer){
        //Open the inputFile Accounts.txt and a temporary file
        File inputFile = new File("Accounts.txt");

        try{
            File tempFile = new File("tempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(" ");

                if (data.length>=4 && data[2].equals(recipient_accNumber)){
                    int current_balance = Integer.parseInt(data[3]);
                    int new_balance = current_balance + (Integer.parseInt(amount_forTransfer));
                    data[3] = String.valueOf(new_balance);

                    //update the line with the new balance
                    line = String.join(" ", data);
                }
                writer.write(line + System.getProperty("line.separator"));
            }

            reader.close();
            writer.close();

            //Replace original file with the updated file
            if (inputFile.delete()){
                tempFile.renameTo(inputFile);
            }else {
                JOptionPane.showMessageDialog(null, "Error updating account balance");
            }

        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "An Error Occurred");
        }



    }

}



