package View;

import javax.swing.*;
import java.awt.*;

public class Icons {
    static ImageIcon JPOTIFY_ICON = new ImageIcon("src/View/Icons/JPotify.png");
    static ImageIcon VOLUME_ICON = new ImageIcon("src/View/Icons/Volume.png");
    static ImageIcon BROWSE_ICON = new ImageIcon("src/View/Icons/browse.png");
    static ImageIcon BROWSE_FOCUSED_ICON = new ImageIcon("src/View/Icons/browse_focused.png");
    static ImageIcon DOWN_ARROW_ICON = new ImageIcon("src/View/Icons/down_arrow.png");
    static ImageIcon HOME_ICON = new ImageIcon("src/View/Icons/home.png");
    static ImageIcon HOME_FOCUSED_ICON = new ImageIcon("src/View/Icons/home_focused.png");
    static ImageIcon LEFT_ARROW_ICON = new ImageIcon("src/View/Icons/left_arrow.png");
    static ImageIcon MENU_ICON = new ImageIcon("src/View/Icons/menu.png");
    static ImageIcon RADIO_ICON = new ImageIcon("src/View/Icons/radio.png");
    static ImageIcon RIGHT_ARROW_ICON = new ImageIcon("src/View/Icons/right_arrow.png");
    static ImageIcon UP_ARROW_ICON = new ImageIcon("src/View/Icons/up_arrow.png");
    static ImageIcon USER_ICON = new ImageIcon("src/View/Icons/user.png");

    public static ImageIcon rescaleIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
