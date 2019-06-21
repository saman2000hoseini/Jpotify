package View;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {
    private TransparentButton home;
    private TransparentButton browse;
    private TransparentButton radio;
    private TransparentButton addNewPlaylist;
    private MenuForWestPanel menuForWestPanel;
    private ScrollPaneForWestPanel scrollPaneForWestPanel;
    private JSeparator newPlaylistSeparator;

    WestPanel() {
        super();
        newPlaylistSeparator = new JSeparator();
        home = new TransparentButton("   Home", Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
        browse = new TransparentButton("   Browse", Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
        radio = new TransparentButton("   Radio", Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
        addNewPlaylist = new TransparentButton("  New Playlist", Icons.rescaleIcon(Icons.ADD_NEW_ICON, 25, 25));
        home.setSize(215, 23);
        browse.setSize(215, 23);
        radio.setSize(215, 23);
        addNewPlaylist.setSize(215, 40);
        home.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        browse.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        radio.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        addNewPlaylist.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 16));
        home.setHorizontalAlignment(SwingConstants.LEFT);
        browse.setHorizontalAlignment(SwingConstants.LEFT);
        radio.setHorizontalAlignment(SwingConstants.LEFT);
        addNewPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
        addNewPlaylist.setVerticalAlignment(SwingConstants.CENTER);
        home.setForeground(new Color(180, 180, 180));
        browse.setForeground(new Color(180, 180, 180));
        radio.setForeground(new Color(180, 180, 180));
        addNewPlaylist.setForeground(new Color(180, 180, 180));
        menuForWestPanel = new MenuForWestPanel();
        scrollPaneForWestPanel = new ScrollPaneForWestPanel();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10,10,10)
                .addGroup(layout.createParallelGroup()
                    .addComponent(menuForWestPanel, 35, 35, 35)
                    .addComponent(home, 215, 215, 215)
                    .addComponent(browse, 215, 215, 215)
                    .addComponent(radio, 215, 215, 215)
                    .addComponent(scrollPaneForWestPanel, 205, 205, 205)
                    .addComponent(newPlaylistSeparator)
                    .addComponent(addNewPlaylist, 215, 215, 215)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menuForWestPanel, 35, 35, 35)
                .addGap(25, 25, 25)
                .addComponent(home, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(browse, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(radio, 23, 23, 23)
                .addGap(20, 20, 20)
                .addComponent(scrollPaneForWestPanel, 0, 470, 470)
                .addComponent(newPlaylistSeparator)
                .addComponent(addNewPlaylist, 40, 40, 40)
                .addContainerGap(15, 15));
    }
}
