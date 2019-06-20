package View;

import javax.swing.*;

public class MainPanel extends JPanel {
    static WestPanel westPanel = new WestPanel();
    static CentrePanel centrePanel = new CentrePanel();
    static FriendsPanel friendsPanel = new FriendsPanel();
    static PlayPanel playPanel = new PlayPanel();

    MainPanel() {
        super();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(westPanel, 340, 340, Short.MAX_VALUE)
                        .addComponent(centrePanel, 600, 1205, Short.MAX_VALUE)
                        .addComponent(friendsPanel, 0, 375, Short.MAX_VALUE))
                .addComponent(playPanel, 940, 1920, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(westPanel, 500, 855, Short.MAX_VALUE)
                        .addComponent(centrePanel, 500, 855, Short.MAX_VALUE)
                        .addComponent(friendsPanel, 500, 855, Short.MAX_VALUE))
                .addComponent(playPanel, 225, 225, Short.MAX_VALUE));
    }
}
