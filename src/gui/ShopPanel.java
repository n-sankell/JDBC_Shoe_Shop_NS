package gui;

import dbobjectmodel.BaseProduct;
import listeners.LogOutListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ShopPanel extends JPanel implements ActionListener {

    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu();
    private final JMenuItem logout = new JMenuItem();
    private LogOutListener logOutListener;
    private final GridBagLayout gb = new GridBagLayout();
    private final Map<BaseProduct,DisplayLabel> allProducts = new HashMap<>();

    public ShopPanel() {
        setBackground(Colors.BG_DARK);
        fillMap();
        setLayout(gb);
        setUpMenu();
        setUp();
    }

    private void fillMap() {
        for (int i = 0; i <= 40; i++) {
            allProducts.put(new BaseProduct(i,i,"shoe "+i),new DisplayLabel("shoe "+i));
        }
    }

    private void addProducts() {
        for (DisplayLabel entry: allProducts.values()) {
            System.out.println(entry.getText());
            scrollablePanel.add(entry);
        }
    }

    private void setUpMenu() {
        menuBar.setMargin(new Insets(2,20,2,20));
        menu.setText("User");
        menuBar.add(menu);
        logout.setText("Logout");
        menu.add(logout);
        logout.addActionListener(this);
    }

    private void setUp() {
        scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
        scrollablePanel.setBackground(Colors.BG_BRIGHT);
        scrollPane = new JScrollPane(scrollablePanel);
    }

    public void addShop() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.ipadx = 1200;
        gc.ipady = 15;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.gridheight = GridBagConstraints.NORTH;
        gc.insets = new Insets(0,10 ,0 ,10 );
        add(menuBar,gc);
        gc.insets = new Insets(110,20 ,60 ,20 );
        gc.ipadx = 800;
        gc.ipady = 600;
        gc.gridx = 2;
        gc.gridy = 2;
        add(scrollablePanel,gc);
    }

    public void setLogOutListener(LogOutListener logOutListener) {
        this.logOutListener = logOutListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            logOutListener.logOutOccurred();
        }
    }

}
