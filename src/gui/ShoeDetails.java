package gui;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Category;
import dbobjectmodel.CompleteShoe;
import dbobjectmodel.ShoeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ShoeDetails extends JPanel implements ActionListener {

    private JLabel title;
    private JLabel price;
    private JLabel size;
    private JLabel categories;
    private JPanel alternativePanel;
    private DisplayLabel selected = new DisplayLabel("");
    private final StringBuilder categoryString = new StringBuilder("");
    private List<ShoeColor> colors = new ArrayList<>();
    private List<CompleteShoe> alternatives = new ArrayList<>();

    public ShoeDetails() {
        setUp();
    }

    private void setUp() {
        setVisible(true);
        setBackground(Colors.BG_BRIGHT);
        title = new JLabel();
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        categories = new JLabel();
        categories.setFont(getFont().deriveFont(Font.BOLD,36f));
        price = new JLabel();
        price.setFont(getFont().deriveFont(Font.BOLD,36f));
        size = new JLabel();
        size.setFont(getFont().deriveFont(Font.BOLD,36f));
        alternativePanel = new JPanel();
        alternativePanel.setBackground(Colors.BG_BRIGHT);
    }

    private void addAlternatives() {
        int count = 1;
        for (CompleteShoe ignored : alternatives) {
            alternativePanel.add(new DisplayLabel("Option "+count));
            count++;
            System.out.println(count);
        }
    }

    private void setCategories(BaseProduct product) {
        for (Category category: product.getCategories()) {
            categoryString.append(category.getName()).append(", ");
        }
        categoryString.setLength(categoryString.length()-2);
    }

    public void setProduct(BaseProduct product) {
        title.setText(product.getLabel().getName()+" "+product.getName());
        alternatives = product.getShoes();
        setCategories(product);
        categories.setText(categoryString.toString());
        addAlternatives();
    }

    public void addDetails() {
        GridBagLayout gb = new GridBagLayout();
        setLayout(gb);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10,10,10,10);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.ipady = 10;
        gc.ipadx = 10;
        add(title,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(categories,gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(price,gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(size,gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(alternativePanel,gc);
    }

    private void setTextFromSelected() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selected = (DisplayLabel) e.getSource();
        setTextFromSelected();
    }
}
