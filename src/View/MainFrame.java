package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final String WINDOW_TITLE = "JPotify";
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private final int X = 0;
    private final int Y = 0;
    static MainPanel mainPanel = new MainPanel();
    MainFrame() {
        super();
        this.setTitle(WINDOW_TITLE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/View/Icons/JPotify.png"));
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
