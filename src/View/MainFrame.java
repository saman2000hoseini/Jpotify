package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {
    private final String WINDOW_TITLE = "JPotify";
    private final int WIDTH = 1920;
    private final int HEIGHT = 1030;
    private final int X = 0;
    private final int Y = 0;
    private MainPanel mainPanel;
    private BackgroundComponentDragger backgroundComponentDragger;
    private Boolean fullScreenMode;

    public MainFrame() {
        super();
        mainPanel = new MainPanel(950, 600);
        this.setTitle(WINDOW_TITLE);
        FrameComponent frame = new FrameComponent(new Insets(5, 5, 5, 5));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/View/Icons/JPotify.png"));
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.fullScreenMode = false;
        backgroundComponentDragger = new BackgroundComponentDragger(this);
        ComponentBorderDragger controller = new ComponentBorderDragger(this,
                new Insets(5, 5, 5, 5), new Dimension(10, 10));
        frame.addMouseMotionListener(controller);
        mainPanel.addMouseMotionListener(backgroundComponentDragger);
        Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, 950, 600);
        this.setUndecorated(true);
        this.add(frame);
        this.add(mainPanel);
        this.setMinimumSize(new Dimension(950, 600));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension sizeIn = ((JFrame) e.getComponent()).getContentPane().getSize();
                if (sizeIn.getWidth() < 950)
                    sizeIn.width = 950;
                if (sizeIn.getHeight() < 600)
                    sizeIn.height = 600;
                frame.setSize(sizeIn);
                frame.getParent().remove(mainPanel);
                mainPanel.removeAll();
                mainPanel = new MainPanel(sizeIn.width, sizeIn.height);
                mainPanel.addMouseMotionListener(backgroundComponentDragger);
                (frame.getParent()).add(mainPanel);
                frame.revalidate();
            }
        });
        this.setVisible(true);
    }

    public void setFullScreenMode(Boolean fullScreenMode) {
        this.fullScreenMode = fullScreenMode;
    }

    public Boolean getFullScreenMode() {
        return fullScreenMode;
    }
}
