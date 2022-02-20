package gui;

import dbobjectmodel.BaseProduct;

import javax.swing.*;
import java.awt.*;

public class ShoeDetails extends JPanel {

    private JLabel title;

    public ShoeDetails() {
        setVisible(true);
        setBackground(Colors.BG_BRIGHT);
        setUp();
    }

    private void setUp() {
        GridBagLayout gb = new GridBagLayout();
        setLayout(gb);
        title = new JLabel();
    }

    public void addDetails(BaseProduct product) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0,10,0,10);
        gc.gridx = 1;
        gc.gridy = 0;
        title.setText(product.getLabel().getName()+" "+product.getName());
        add(title);
        gc.gridwidth = GridBagConstraints.REMAINDER;



        gc.gridx = 2;
        gc.gridy = 2;

    }


}
