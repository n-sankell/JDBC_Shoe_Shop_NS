package gui;

import dbobjectmodel.BaseProduct;
import listeners.ShoeDetailsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollablePanel extends JPanel implements ActionListener {

    private ShoeDetailsListener shoeDetailsListener;
    private DisplayLabel alternative = new DisplayLabel("");
    private BaseProduct baseProduct;
    private final Map<BaseProduct,DisplayLabel> allProducts = new HashMap<>();

    public ScrollablePanel() {
        setUp();
    }

    private void setUp() {
        setVisible(true);
        setBackground(Colors.BG_BRIGHT);
        GridLayout grid = new GridLayout(allProducts.size()/3,3);
        grid.setHgap(40);
        grid.setVgap(40);
        setLayout(grid);
    }

    public void fillMap(List<BaseProduct> products) {
        for (BaseProduct product : products) {
            allProducts.put(product, new DisplayLabel(product.getLabel().getName() + " " + product.getName()));
        }
        addProducts();
    }

    public void addProducts() {
        for (DisplayLabel entry: allProducts.values()) {
            entry.addActionListener(this);
            add(entry);
        }
    }

    private BaseProduct findAlternative() {
        for (Map.Entry<BaseProduct, DisplayLabel> entry: allProducts.entrySet()) {
            if (entry.getValue() == alternative) {
                baseProduct = entry.getKey();
            }
        }
        return baseProduct;
    }

    public void setShoeDetailsListener(ShoeDetailsListener shoeDetailsListener) {
        this.shoeDetailsListener = shoeDetailsListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alternative = (DisplayLabel) e.getSource();
        shoeDetailsListener.detailEventOccurred(findAlternative());
    }
}
