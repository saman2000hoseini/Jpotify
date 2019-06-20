package View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    static WestPanel westPanel = new WestPanel();
    static CentrePanel centrePanel = new CentrePanel();
    static FriendsPanel friendsPanel = new FriendsPanel();
    static PlayPanel playPanel = new PlayPanel();

    MainPanel() {
        super();
        GroupLayout layout = new GroupLayout(this);
        this.setBackground(new Color(24, 24, 24));
        this.setLayout(layout);
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
}
