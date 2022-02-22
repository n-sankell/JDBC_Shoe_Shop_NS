package gui;

import listeners.AllAveragesListener;
import listeners.LogOutListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel implements ActionListener {

    private JScrollPane scrollPane;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu userMenu = new JMenu();
    private final JMenu viewMenu = new JMenu();
    private final JMenuItem logout = new JMenuItem();
    private final JMenuItem averages = new JMenuItem();
    private LogOutListener logOutListener;
    private AllAveragesListener allAveragesListener;
    private ShoeDetails shoeDetails = new ShoeDetails();
    private final ScrollablePanel scrollablePanel = new ScrollablePanel();

    public ShopPanel() {
        setUpMenu();
        setUp();
    }

    private void setUpMenu() {
        menuBar.setMargin(new Insets(2,20,2,20));
        userMenu.setText("User");
        menuBar.add(userMenu);
        viewMenu.setText("View");
        menuBar.add(viewMenu);
        logout.setText("Logout");
        userMenu.add(logout);
        averages.setText("View Averages");
        viewMenu.add(averages);
        logout.addActionListener(this);
        averages.addActionListener(this);
    }

    private void setUp() {
        setBackground(Colors.BG_DARK);
        setLayout(new GridBagLayout());
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
        gc.insets = new Insets(0,10 ,823 ,10 );
        add(menuBar,gc);
    }

    public void addScrollPane() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.insets = new Insets(110,20 ,60 ,20 );
        gc.ipadx = 900;
        gc.ipady = 700;
        gc.gridx = 2;
        gc.gridy = 2;
        add(scrollPane,gc);
        repaint();
        revalidate();
    }

    public void removeScrollPane() {
        remove(scrollPane);
    }

    public void removeDetails() {
        remove(shoeDetails);
    }

    public void createDetailPanel() {
        shoeDetails = new ShoeDetails();
    }

    public void addDetails() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.insets = new Insets(110,20 ,60 ,20 );
        gc.ipadx = 400;
        gc.ipady = 300;
        gc.gridx = 2;
        gc.gridy = 2;
        add(shoeDetails,gc);
        repaint();
        revalidate();
    }

    public ShoeDetails getShoeDetails() {
        return shoeDetails;
    }

    public ScrollablePanel getScrollablePanel() {
        return scrollablePanel;
    }

    public void setLogOutListener(LogOutListener logOutListener) {
        this.logOutListener = logOutListener;
    }

    public void setAllAveragesListener(AllAveragesListener allAveragesListener) {
        this.allAveragesListener = allAveragesListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            logOutListener.logOutOccurred();
        }
        if (e.getSource() == averages) {
            allAveragesListener.showAllAverages();
        }
    }

}
