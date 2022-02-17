package gui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class CustomJop {

    private static Dialog dialog;
    private final GridBagLayout gb = new GridBagLayout();
    private final GridBagConstraints gc = new GridBagConstraints();

    public CustomJop(String message, String buttonText) {
        JFrame frame = new JFrame();
        dialog = new JDialog(frame,true);
        dialog.setBackground(Colors.BG_DARK);
        dialog.setAlwaysOnTop(true);
        dialog.setLocation(600,300);
        dialog.setLayout(gb);

        CustomButton button = new CustomButton (buttonText);
        button.addActionListener (e -> dialog.setVisible(false));
        button.setBounds(10,10,200,75);

        JTextPane loginMessage = new JTextPane();
        loginMessage.setOpaque(false);
        loginMessage.setText(message);
        loginMessage.setForeground(Colors.TEXT);
        loginMessage.setFont(new Font("Druk Wide",Font.BOLD,20));
        StyledDocument documentStyle = loginMessage.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        gc.insets = new Insets(20,20,20,20);
        gc.gridy = 1;
        dialog.add(loginMessage,gc);
        gc.gridy = 2;
        dialog.add(button,gc);

        dialog.setSize(600,350);
        dialog.setVisible(true);
        dialog.setBackground(Colors.BG_DARK);

    }
}