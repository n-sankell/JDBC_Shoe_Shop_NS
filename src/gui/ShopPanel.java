package gui;

import listeners.LogOutListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel implements ActionListener {

    private JScrollPane scrollPane;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu();
    private final JMenuItem logout = new JMenuItem();
    private LogOutListener logOutListener;
    private final ScrollablePanel scrollablePanel = new ScrollablePanel();

    public ShopPanel() {
        setBackground(Colors.BG_DARK);
        setLayout(new GridLayout());
        setUpMenu();
        setUp();
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
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.insets = new Insets(110,20 ,60 ,20 );
        gc.ipadx = 800;
        gc.ipady = 700;
        gc.gridx = 2;
        gc.gridy = 2;
        add(scrollPane,gc);
    }

    public ScrollablePanel getScrollablePanel() {
        return scrollablePanel;
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
