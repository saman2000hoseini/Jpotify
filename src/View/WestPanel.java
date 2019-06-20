package View;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {
    private TransparentButton Home;
    private TransparentButton Browse;
    private TransparentButton Radio;
    private MenuForWestPanel menuForWestPanel;
    WestPanel() {
        super();
        Home = new TransparentButton("Home", Icons.rescaleIcon(Icons.VOLUME_ICON, 40, 40));
        Browse = new TransparentButton("Browse", Icons.rescaleIcon(Icons.VOLUME_ICON, 40, 40));
        Radio = new TransparentButton("Radio", Icons.rescaleIcon(Icons.VOLUME_ICON, 40, 40));
        menuForWestPanel = new MenuForWestPanel();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(menuForWestPanel, 0, 20, Short.MAX_VALUE)
                .addComponent(Home, 0, 0, Short.MAX_VALUE)
                .addComponent(Browse, 0, 0, Short.MAX_VALUE)
                .addComponent(Radio, 0, 0, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuForWestPanel, 0, 20, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Home, 0, 0, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Browse, 0, 0, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Radio, 0, 0, Short.MAX_VALUE));
    }
}
