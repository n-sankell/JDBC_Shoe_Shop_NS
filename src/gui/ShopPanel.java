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
        int i = 1;
        for (DisplayLabel entry: allProducts.values()) {
            scrollablePanel.add(entry);
            i++;
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
        scrollablePanel.setVisible(true);
        GridLayout grid = new GridLayout(allProducts.size()/3,3);
        grid.setHgap(40);
        grid.setVgap(40);
        scrollablePanel.setLayout(grid);
        scrollablePanel.setBackground(Colors.BG_BRIGHT);
        scrollPane = new JScrollPane(scrollablePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVisible(true);
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
        gc.ipadx = 100;
        gc.ipady = 1200;
        gc.gridx = 2;
        gc.gridy = 2;
        addProducts();
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
