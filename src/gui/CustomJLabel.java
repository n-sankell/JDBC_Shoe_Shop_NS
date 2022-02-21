package gui;

import javax.swing.*;
import java.awt.*;

public class CustomJLabel extends JLabel {

    public CustomJLabel(String s) {
        this.setText(s);
        this.setFont(getFont().deriveFont(Font.BOLD,20f));
    }

}
