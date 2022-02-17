package gui;

import listeners.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private String enteredName;
    private String enteredPass;
    private JTextField username;
    private JTextField password;
    private CustomButton login;
    private CustomButton register;
    private LoginListener loginListener;

    public LoginPanel() {
        GridBagLayout gb = new GridBagLayout();
        setBackground(Colors.BG_DARK);
        setInputFields();
        setLayout(gb);
    }

    private void setInputFields() {
        username = new JTextField("username");
        password = new JTextField("password");
        username.setBounds(10,10,300,30);
        password.setBounds(10,10,300,30);
        username.setForeground(Colors.TEXT);
        password.setForeground(Colors.TEXT);
        username.setBackground(Colors.BG_BRIGHT);
        password.setBackground(Colors.BG_BRIGHT);
        username.setSize(new Dimension(200,50));
        password.setSize(new Dimension(200,50));
        login = new CustomButton("login");
        register = new CustomButton("register");
        login.addActionListener(this);
        register.addActionListener(this);
    }

    public void login() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(20,10 ,20 ,10 );
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.ipadx = 250;
        gc.ipady = 15;
        gc.gridx = 2;
        gc.gridy = 1;
        add(username,gc);
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.gridx = 2;
        gc.gridy = 2;
        add(password,gc);
        gc.gridwidth = GridBagConstraints.RELATIVE;
        gc.ipadx = 5;
        gc.ipady = 5;
        gc.gridx = 1;
        gc.gridy = 3;
        add(login,gc);
        gc.gridwidth = GridBagConstraints.RELATIVE;
        gc.gridx = 3;
        gc.gridy = 3;
        add(register,gc);
    }

    private void validateUser() {
        loginListener.loginEvent(enteredName, enteredPass);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            System.out.println("login");
            enteredName = username.getText();
            enteredPass = password.getText();
            validateUser();
        }
        if (e.getSource() == register) {
            System.out.println("Not featured yet");
        }
    }

}
