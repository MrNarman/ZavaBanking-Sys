import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainScreen {
    static JLabel fullName;
    static JLabel accNumberLabel;
    static JLabel accBalance;

    public MainScreen(){
        JFrame mainScreen = new JFrame("Main Screen");

        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setSize(600,500);
        mainScreen.setResizable(false);
        mainScreen.setLayout(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setBounds(200, 80, 350, 350);
        buttonPanel.setLayout(new GridLayout(2, 2, 2, 2));

        JButton withdrawButton = new JButton("WITHDRAW");
                withdrawButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        new Withdraw();
                        mainScreen.dispose();

                    }
                });
        JButton sendmoneyButton = new JButton("SEND MONEY");
                sendmoneyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){

                    }
                });
        JButton donateButton = new JButton("DONATE");
                donateButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        new Donate();
                        mainScreen.dispose();

                    }
                });
        JButton settingsButton = new JButton("SETTINGS");
                settingsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){

                    }
                });

        buttonPanel.add(withdrawButton);
        buttonPanel.add(sendmoneyButton);
        buttonPanel.add(donateButton);
        buttonPanel.add(settingsButton);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.lightGray);
        leftPanel.setBounds(0,0,180, 500);
        leftPanel.setLayout(null);

        fullName = new JLabel("XXXXXX XXXXXX");
        fullName.setBounds(7, 25, 175, 30);

        accNumberLabel = new JLabel("************");
        accNumberLabel.setBounds(7,50, 100, 30);  
        
        JButton exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBounds(100, 400, 70, 30);
                exitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){

                    }
                });

        leftPanel.add(fullName);
        leftPanel.add(accNumberLabel);
        leftPanel.add(exitButton);

        accBalance = new JLabel("000000000 ");
        accBalance.setBounds(300, 20, 200, 40);
        JLabel currencyLabel = new JLabel("Balance: Ksh ");
        currencyLabel.setBounds(200, 20, 200, 40);
        
        //Add components to Frame
        mainScreen.add(buttonPanel);
        mainScreen.add(currencyLabel);
        mainScreen.add(accBalance);
        mainScreen.add(leftPanel);
    }

    //Method to update the label text
    public static void updateLabelText(String fullNameText, String accNumberTxt){
        fullName.setText(fullNameText);
        accNumberLabel.setText(accNumberTxt);
    }

    public static void loginUpdateBalanceLabel(String accBalOfAccNoEntered){
        accBalance.setText(accBalOfAccNoEntered);
    }

}
