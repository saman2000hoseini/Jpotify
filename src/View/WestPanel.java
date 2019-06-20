package View;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {
    private TransparentButton Home;
    private TransparentButton Browse;
    private TransparentButton Radio;
    private TransparentButton newPlaylist;
    private MenuForWestPanel menuForWestPanel;
    private ScrollPaneForWestPanel scrollPaneForWestPanel;

    WestPanel() {
        super();
        Home = new TransparentButton("   Home", Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
        Browse = new TransparentButton("   Browse", Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
        Radio = new TransparentButton("   Radio", Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
        Home.setSize(215, 23);
        Browse.setSize(215, 23);
        Radio.setSize(215, 23);
        Home.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        Browse.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        Radio.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        Home.setHorizontalAlignment(SwingConstants.LEFT);
        Browse.setHorizontalAlignment(SwingConstants.LEFT);
        Radio.setHorizontalAlignment(SwingConstants.LEFT);
        Home.setForeground(new Color(180, 180, 180));
        Browse.setForeground(new Color(180, 180, 180));
        Radio.setForeground(new Color(180, 180, 180));
        menuForWestPanel = new MenuForWestPanel();
        scrollPaneForWestPanel = new ScrollPaneForWestPanel();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10,10,10)
                .addGroup(layout.createParallelGroup()
                    .addComponent(menuForWestPanel, 35, 35, 35)
                    .addComponent(Home, 215, 215, 215)
                    .addComponent(Browse, 215, 215, 215)
                    .addComponent(Radio, 215, 215, 215)
                    .addComponent(scrollPaneForWestPanel, 0, 100, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menuForWestPanel, 35, 35, 35)
                .addGap(25, 25, 25)
                .addComponent(Home, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(Browse, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(Radio, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(scrollPaneForWestPanel, 0, 100, Short.MAX_VALUE));
    }
}
