package gui;

import javax.swing.*;
import java.awt.*;

public class CustomTextArea extends JTextArea {

    public CustomTextArea(String s) {
        setFont(getFont().deriveFont(Font.PLAIN,16f));
        setBackground(Colors.BG_BRIGHT);
        setLineWrap(true);
        setEditable(false);
        setText(s);
    }

}
