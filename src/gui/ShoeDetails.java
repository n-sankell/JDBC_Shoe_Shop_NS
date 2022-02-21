package gui;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Category;
import dbobjectmodel.CompleteShoe;
import dbobjectmodel.ShoeColor;
import listeners.AddToCartListener;
import listeners.GoBackListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoeDetails extends JPanel implements ActionListener {

    private JLabel title;
    private JLabel price;
    private JLabel size;
    private JLabel categories;
    private JPanel alternativePanel;
    private JPanel detailPanel;
    private JPanel optionPanel;
    private JLabel inStock;
    private JLabel colors;
    private int optionCounter = 0;
    private int addedProductCounter = 0;
    private GoBackListener goBackListener;
    private AddToCartListener addToCartListener;
    private DisplayLabel selected;
    private CompleteShoe selectedShoe;
    private StringBuilder categoryString;
    private StringBuilder colorString;
    private CustomButton backButton;
    private CustomButton viewCartButton;
    private CustomButton checkOutButton;
    private CustomButton addToCartButton;
    private List<CompleteShoe> alternatives;
    private HashMap<CompleteShoe,DisplayLabel> alternativesMap;

    public ShoeDetails() {
        setUp();
    }

    public void setUp() {
        setVisible(true);
        setBackground(Colors.BG_BRIGHT);
        backButton = new CustomButton("Go back");
        backButton.addActionListener(this);
        addToCartButton = new CustomButton("Add to cart");
        addToCartButton.addActionListener(this);
        viewCartButton = new CustomButton("View cart");
        viewCartButton.addActionListener(this);
        checkOutButton = new CustomButton("Checkout");
        checkOutButton.addActionListener(this);
        GridLayout optionGrid = new GridLayout(1,4);
        optionPanel = new JPanel();
        optionPanel.setLayout(optionGrid);
        optionPanel.setBackground(Color.ORANGE);
        optionPanel.add(backButton);
        optionPanel.add(viewCartButton);
        optionPanel.add(checkOutButton);
        optionPanel.add(addToCartButton);
        selected = new DisplayLabel("");
        title = new JLabel();
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        categories = new JLabel();
        categories.setFont(getFont().deriveFont(Font.BOLD,25f));
        categories.setForeground(Colors.TEXT);
        price = new JLabel();
        price.setFont(getFont().deriveFont(Font.BOLD,20f));
        price.setForeground(Colors.TEXT);
        size = new JLabel();
        size.setFont(getFont().deriveFont(Font.BOLD,20f));
        size.setForeground(Colors.TEXT);
        inStock = new JLabel();
        inStock.setFont(getFont().deriveFont(Font.BOLD,20f));
        inStock.setForeground(Colors.TEXT);
        colors = new JLabel();
        colors.setFont(getFont().deriveFont(Font.BOLD,20f));
        colors.setForeground(Colors.TEXT);
        alternativePanel = new JPanel();
        alternativePanel.setBackground(Colors.BG_BRIGHT);
        alternativePanel.setPreferredSize(new Dimension(800,50));
        detailPanel = new JPanel();
        GridLayout grid = new GridLayout(2,3);
        detailPanel.setLayout(grid);
        detailPanel.setBackground(Colors.BG_BRIGHT);
        detailPanel.setPreferredSize(new Dimension(700,50));
        detailPanel.setBackground(Colors.BG_BRIGHT);
        grid.setVgap(2);
        grid.setHgap(2);
    }

    public void removeDetails() {
        selected = new DisplayLabel("");
        title.setText("");
        remove(title);
        categories.setText("");
        remove(categories);
        price.setText("");
        detailPanel.remove(price);
        size.setText("");
        detailPanel.remove(size);
        colors.setText("");
        detailPanel.remove(colors);
        inStock.setText("");
        detailPanel.remove(inStock);
        remove(detailPanel);
        repaint();
        revalidate();
    }

    private void addAlternativesToMap() {
        int count = 1;
        alternativesMap = new HashMap<>();
        for (CompleteShoe alternative : alternatives) {
            alternativesMap.put(alternative, new DisplayLabel("Option "+count));
            count++;
            System.out.println(count);
        }
    }

    private void addAlternatives() {
        for (DisplayLabel entry: alternativesMap.values()) {
            entry.addActionListener(this);
            alternativePanel.add(entry);
        }
        repaint();
        revalidate();
    }

    public void updateInStock() {
        inStock.setText("Number in stock: "+selectedShoe.getAmountInStock());
        repaint();
        revalidate();
    }

    private void setCategories(BaseProduct product) {
        categoryString = new StringBuilder();
        for (Category category: product.getCategories()) {
            categoryString.append(category.getName()).append(", ");
        }
        categoryString.setLength(categoryString.length()-2);
    }

    public void setProduct(BaseProduct product) {
        alternatives = new ArrayList<>();
        title.setText(product.getLabel().getName()+" "+product.getName());
        alternatives = product.getShoes();
        setCategories(product);
        categories.setText(categoryString.toString());
        addAlternativesToMap();
        addAlternatives();
    }

    public void addDetails() {
        GridBagLayout gb = new GridBagLayout();
        setLayout(gb);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10,10,10,10);
        gc.gridy = 0;
        gc.gridx = 1;
        gc.ipady = 10;
        gc.ipadx = 10;
        gc.gridwidth = GridBagConstraints.RELATIVE;
        add(optionPanel,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.ipady = 10;
        gc.ipadx = 10;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        add(title,gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(categories,gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(detailPanel,gc);
        gc.gridx = 1;
        gc.gridy = 4;
        detailPanel.add(price);
        detailPanel.add(size);
        detailPanel.add(colors);
        detailPanel.add(new JLabel(" "));
        detailPanel.add(inStock);
        detailPanel.add(new JLabel(" "));
        add(alternativePanel,gc);
        repaint();
        revalidate();
    }

    private void setTextFromSelected() {
        colorString = new StringBuilder("Colors: ");
        price.setText("Price: "+selectedShoe.getPrice().getPrice());
        size.setText("Size: "+selectedShoe.getSize().getSize());
        inStock.setText("Number in stock: "+selectedShoe.getAmountInStock());
        for (ShoeColor color : selectedShoe.getColors()) {
            colorString.append(color.getName()).append(", ");
        }
        colorString.setLength(colorString.length()-2);
        colors.setText(colorString.toString());
        repaint();
        revalidate();
    }

    private void findSelected() {
        for (Map.Entry<CompleteShoe, DisplayLabel> entry: alternativesMap.entrySet()) {
            if (entry.getValue() == selected) {
                selectedShoe = entry.getKey();
            }
        }
    }

    public void setGoBackListener(GoBackListener goBackListener) {
        this.goBackListener = goBackListener;
    }

    public void setAddToCartListener(AddToCartListener addToCartListener) {
        this.addToCartListener = addToCartListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        colorString = new StringBuilder();
        categoryString = new StringBuilder();
        if (e.getSource() == backButton) {
            removeDetails();
            goBackListener.goBackEvent();
        } else if (e.getSource() == addToCartButton) {
            if (optionCounter > 0) {
                addToCartListener.productAdded(selectedShoe);
                addedProductCounter++;
            }
        } else if (e.getSource() == viewCartButton) {
            System.out.println("view cart");
        } else if (e.getSource() == checkOutButton) {
            if (addedProductCounter > 0) {
                System.out.println("Checkout available!");
                addedProductCounter = 0;
                optionCounter = 0;
            }
            System.out.println("Checkout not available");
        } else {
            selected = (DisplayLabel) e.getSource();
            optionCounter++;
            findSelected();
            setTextFromSelected();
        }
    }
}
