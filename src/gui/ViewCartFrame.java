package gui;

import dbobjectmodel.CompleteShoe;
import listeners.CheckoutListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewCartFrame extends JFrame implements ActionListener {

    private JLabel title;
    private JLabel total;
    private JPanel bg;
    private JPanel listBg;
    private int counter = 0;
    private CustomButton checkout;
    private CheckoutListener checkoutListener;
    private final List<CompleteShoe> products;

    public ViewCartFrame(List<CompleteShoe> products) {
        this.products = products;
        setVisible(true);
        setUp();
        addElements();
    }

    private void setUp() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(400,500);
        setLocation(500,150);
        setResizable(false);
        title = new JLabel("Your Cart");
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        total = new JLabel();
        total.setForeground(Colors.TEXT);
        total.setFont(getFont().deriveFont(Font.BOLD,26f));
        checkout = new CustomButton("Checkout");
        checkout.addActionListener(this);
        GridLayout vertical = new GridLayout(products.size(), 1);
        listBg = new JPanel();
        listBg.setBackground(Colors.BG_BRIGHT);
        listBg.setLayout(vertical);
        vertical.setVgap(10);
        bg = new JPanel();
        bg.setBackground(Colors.BG_BRIGHT);
        GridBagLayout gb = new GridBagLayout();
        bg.setLayout(gb);
        bg.setPreferredSize(new Dimension(400,500));
        setTotalText();
    }

    private void addElements() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 1;
        bg.add(title,gc);
        gc.gridy = 1;
        addShoeLabels();
        gc.gridy = 2;
        bg.add(listBg,gc);
        gc.gridy = 3;
        bg.add(total,gc);
        gc.gridy = 4;
        bg.add(checkout,gc);
        add(bg);
    }

    private void setTotalText() {
        double totalPrice = products.stream().mapToDouble(e -> e.getPrice().getPrice()).sum();
        total.setText("Total: "+totalPrice+"sek");
    }

    private void addShoeLabels() {
        for (CompleteShoe shoe : products) {
            listBg.add(new CustomJLabel(shoe.getProduct().getLabel().getName()+" "+shoe.getProduct().getName()+" "+shoe.getPrice().getPrice()+"sek"));
        }
    }

    public void setCheckoutListener(CheckoutListener checkoutListener) {
        this.checkoutListener = checkoutListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkout && counter == 0) {
            counter++;
            checkoutListener.checkoutEvent();
        }
    }
}
