package View;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {
    TransparentButton(String text, ImageIcon imageIcon){
        super(text, imageIcon);
        setForeground(new Color(175, 175, 175));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
}
