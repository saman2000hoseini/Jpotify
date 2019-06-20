package View;

import javax.swing.*;
import java.awt.*;

public class Icons {
    static ImageIcon JPOTIFY_ICON = new ImageIcon("src/View/Icons/JPotify.png");
    static ImageIcon VOLUME_ICON = new ImageIcon("src/View/Icons/Volume.png");

    public static ImageIcon rescaleIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
