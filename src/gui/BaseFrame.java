package gui;

import controller.User;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {

    private User user;
    private LoginPanel loginPanel;
    private final GridBagLayout gb = new GridBagLayout();

    public BaseFrame() {
        super("Shoe Shop!");
        setUpLogin();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200,900));
        setLocation(300,100);
        setVisible(true);
    }

    private void setUpLogin() {
        loginPanel = new LoginPanel();
        loginPanel.setVisible(true);
    }

    public void startLogin(User user) {
        loginPanel.login(user);
        add(loginPanel);
    }

}
