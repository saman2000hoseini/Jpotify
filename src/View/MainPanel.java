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
                        .addComponent(westPanel, 0, 340, Short.MAX_VALUE)
                        .addComponent(centrePanel, 0, 1240, Short.MAX_VALUE)
                        .addComponent(friendsPanel, 0, 340, Short.MAX_VALUE))
                .addComponent(playPanel, 0, 100, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(westPanel, 0, 855, Short.MAX_VALUE)
                        .addComponent(centrePanel, 0, 855, Short.MAX_VALUE)
                        .addComponent(friendsPanel, 0, 855, Short.MAX_VALUE))
                .addComponent(playPanel, 0, 225, Short.MAX_VALUE));
    }
}
