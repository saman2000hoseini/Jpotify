package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.Constructor;

public class MainPanel extends JPanel {
    private WestPanel westPanel = new WestPanel();
    private CentrePanel centrePanel;
    private FriendsPanel friendsPanel;
    private PlayPanel playPanel;

    MainPanel(int width, int height) {
        super();
        GroupLayout layout = new GroupLayout(this);
        this.setSize(width, height);
        playPanel = new PlayPanel(getWidth(),MainFrame.musics);
        centrePanel = new CentrePanel(width, height);
        this.setBackground(new Color(24, 24, 24));
        this.setLayout(layout);
        if (width >= 1070) {
            friendsPanel = new FriendsPanel();
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(westPanel, 215, 215, Short.MAX_VALUE)
                            .addComponent(centrePanel, 600, 1450, Short.MAX_VALUE)
                            .addComponent(friendsPanel, 255, 255, Short.MAX_VALUE))
                    .addComponent(playPanel, 940, 1920, Short.MAX_VALUE));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(westPanel, 500, 952, Short.MAX_VALUE)
                            .addComponent(centrePanel, 500, 952, Short.MAX_VALUE)
                            .addComponent(friendsPanel, 500, 952, Short.MAX_VALUE))
                    .addComponent(playPanel, 88, 88, Short.MAX_VALUE));
        }
        else {
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(westPanel, 215, 215, Short.MAX_VALUE)
                            .addComponent(centrePanel, 600, 1450, Short.MAX_VALUE))
                    .addComponent(playPanel, 940, 1920, Short.MAX_VALUE));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                            .addComponent(westPanel, 500, 952, Short.MAX_VALUE)
                            .addComponent(centrePanel, 500, 952, Short.MAX_VALUE))
                    .addComponent(playPanel, 72, 88, Short.MAX_VALUE));
        }
    }
}
