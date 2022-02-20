package gui;

import javax.swing.*;
import java.awt.*;

public class DisplayLabel extends JButton {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public DisplayLabel(String text) {
        this.text = text;
        setForeground(Colors.TEXT);
        setFont(getFont().deriveFont(Font.BOLD,30f));
        setPreferredSize(new Dimension(150, 50));
        setMaximumSize(new Dimension(150, 50));
        setFocusPainted(false);
        setBackground(Colors.TEXT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        if (getModel().isPressed()) {
            g2d.setColor(Colors.BUTTON_PRESSED);
        } else if (getModel().isRollover()) {
            g2d.setColor(Colors.BUTTON_ROLL_OVER);
        } else {
            g2d.setColor(getBackground());
        }
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Colors.BG_BRIGHT);
        drawCenteredString(g2d, getWidth(), getHeight());
    }

    private void drawCenteredString(Graphics2D g2, int width, int height) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((int) (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) * 0.4));
        g2.drawString(text, x, y);
    }

    @Override
    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Colors.BUTTON_ROLL_OVER);
        g2.setStroke(new BasicStroke(8));

        Dimension size = getSize();
        g2.drawRect(0,0, size.width, size.height);
    }

}
