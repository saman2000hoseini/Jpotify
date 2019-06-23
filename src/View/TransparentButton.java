package View;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {
    private Boolean focused;
    TransparentButton(String text, ImageIcon imageIcon){
        super(text, imageIcon);
        setForeground(new Color(175, 175, 175));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.focused = false;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
    TransparentButton(String text){
        super(text);
        setForeground(new Color(175, 175, 175));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.focused = false;
    }

    TransparentButton(ImageIcon imageIcon){
        super(imageIcon);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.focused = false;
    }

    public void setFocused(Boolean focused) {
        this.focused = focused;
    }

    public Boolean getFocused() {
        return focused;
    }
}
