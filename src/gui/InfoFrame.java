package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InfoFrame extends JFrame {

    private JLabel title;
    private JPanel bg;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private final List<String> listToPrint;

    public InfoFrame(List<String> listToPrint) {
        this.listToPrint = listToPrint;
        setVisible(true);
        setUp();
        addElements();
    }

    private void setUp() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(false);
        setSize(800,1000);
        setLocation(500,150);
        setResizable(false);
        title = new JLabel(listToPrint.get(0));
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        GridLayout grid = new GridLayout(listToPrint.size(),1);
        listPanel = new JPanel();
        listPanel.setLayout(grid);
        listPanel.setBackground(Colors.BG_BRIGHT);
        grid.setVgap(5);
        bg = new JPanel();
        bg.setBackground(Colors.BG_BRIGHT);
        GridBagLayout gb = new GridBagLayout();
        bg.setLayout(gb);
        scrollPane = new JScrollPane(bg, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVisible(true);
    }

    private void addElements() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 1;
        bg.add(title,gc);
        gc.insets = new Insets(20,5,20,5);
        gc.gridy = 1;
        addAllInfo();
        gc.gridy = 2;
        bg.add(listPanel,gc);
        add(scrollPane);
    }

    private void addAllInfo() {
        for (String infoRow : listToPrint) {
            if (!infoRow.equals(listToPrint.get(0))) {
                listPanel.add(new CustomJLabel(infoRow));
            }
        }
    }

}
