package gui;

import listeners.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel implements ActionListener {

    private JLabel aShoe;
    private GridBagLayout gb = new GridBagLayout();

    public ShopPanel() {
        setBackground(Colors.BG_DARK);
        setLayout(gb);
        setUp();
    }

    private void setUp() {
        aShoe = new JLabel("SKOKOKOKOKOKSSSKO");
    }

    public void addShop() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(10,10 ,10 ,10 );
        add(aShoe,gc);
        gc.gridx = 2;
        gc.gridy = 2;
        add(aShoe,gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(aShoe,gc);
        gc.gridx = 3;
        gc.gridy = 3;
        add(aShoe,gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
