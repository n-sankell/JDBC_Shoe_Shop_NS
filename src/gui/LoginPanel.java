package gui;

import controller.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private User user;
    private String enteredName;
    private String enteredPass;
    private JTextField username;
    private JTextField password;
    private CustomButton login;
    private CustomButton register;
    private GridBagLayout gb = new GridBagLayout();

    public LoginPanel() {
        setBackground(Colors.BG_DARK);
        setInputFields();
        setLayout(gb);
    }

    private void setInputFields() {
        username = new JTextField("username");
        password = new JTextField("password");
        login = new CustomButton("login");
        register = new CustomButton("register");
        login.addActionListener(this);
        register.addActionListener(this);
    }

    public void login(User user) {
        this.user = user;
        add(username);
        add(password);
        add(login);
        add(register);
    }

    private void validateUser() {
        System.out.println("validate user");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            System.out.println("login");
            enteredName = username.getText();
            enteredPass = password.getText();
            System.out.println(enteredName);
            System.out.println(enteredPass);
            validateUser();
        }
        if (e.getSource() == register) {
            System.out.println("register");
        }
    }

}
