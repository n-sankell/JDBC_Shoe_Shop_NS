package gui;

import controller.User;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {

    private LoginPanel loginPanel;
    private ShopPanel shopPanel;
    private final GridBagLayout gb = new GridBagLayout();

    public BaseFrame() {
        super("Shoe Shop!");
        setUpLogin();
        setUpShopPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200,900));
        setLocation(300,100);
        setResizable(false);
        setVisible(true);
    }

    private void setUpShopPanel() {
        shopPanel = new ShopPanel();
        shopPanel.addShop();
        shopPanel.addScrollPane();
    }

    public void removeLogin() {
        remove(loginPanel);
    }

    public void removeShopPanel() {
        remove(shopPanel);
    }

    public void addShopPanel() {
        add(shopPanel);
        repaint();
        revalidate();
    }

    private void setUpLogin() {
        loginPanel = new LoginPanel();
        loginPanel.setVisible(true);
    }

    public void startLogin() {
        loginPanel.login();
        add(loginPanel);
        repaint();
        revalidate();
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public ShopPanel getShopPanel() {
        return shopPanel;
    }

}
