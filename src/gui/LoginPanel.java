package gui;

import listeners.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    private void changeFont(JTextField textField) {
        textField.setText("");
        textField.setForeground(Colors.TEXT);
        textField.setFont(new Font("Arial", Font.PLAIN,12));
        revalidate();
    }

    private void setInputFields() {
        username = new JTextField("Nils Karlsson");
        password = new JTextField("nisse");
        username.setForeground(Color.GRAY);
        password.setForeground(Color.GRAY);
        username.setFont(new Font("Arial", Font.ITALIC,12));
        password.setFont(new Font("Arial", Font.ITALIC,12));
        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                changeFont(username);
            }
        });
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                changeFont(password);
            }
        });
        username.setBounds(10,10,200,30);
        password.setBounds(10,10,200,30);
        username.setBackground(Colors.BG_BRIGHT);
        password.setBackground(Colors.BG_BRIGHT);
        username.setPreferredSize(new Dimension(200,20));
        password.setPreferredSize(new Dimension(200,20));
        login = new CustomButton("login");
        register = new CustomButton("register");
        login.addActionListener(this);
        register.addActionListener(this);
    }

    public void login() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(20,10 ,20 ,10 );
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.ipadx = 30;
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
