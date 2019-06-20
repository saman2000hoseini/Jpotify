package View;

import javax.swing.*;

public class TransparentButton extends JButton {
    TransparentButton(String text, ImageIcon imageIcon){
        super(text, imageIcon);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
}
