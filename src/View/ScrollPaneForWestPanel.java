package View;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneForWestPanel extends JScrollPane {
    private JLabel yourLibrary = new JLabel("   YOUR LIBRARY");
    private JLabel madeForYou = new JLabel("   Made For You");
    private JLabel recentlyPlayed = new JLabel("   Recently Played");
    private JLabel songs = new JLabel("   Songs");
    private JLabel albums = new JLabel("   Albums");
    private JLabel artists = new JLabel("   Artists");
    private JLabel playlists = new JLabel("   PLAYLISTS");
    private JPanel viewportPanel = new JPanel();

    ScrollPaneForWestPanel() {
        super();
        this.setSize(205, 300);
        yourLibrary.setForeground(new Color(145, 145, 145));
        yourLibrary.setFont(new Font("Proxima Nova Alt Lt", Font.BOLD, 13));
        madeForYou.setForeground(new Color(205, 205, 205));
        madeForYou.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        recentlyPlayed.setForeground(new Color(205, 205, 205));
        recentlyPlayed.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        songs.setForeground(new Color(205, 205, 205));
        songs.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        albums.setForeground(new Color(205, 205, 205));
        albums.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        artists.setForeground(new Color(205, 205, 205));
        artists.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        playlists.setForeground(new Color(145, 145, 145));
        playlists.setFont(new Font("Proxima Nova Alt Lt", Font.BOLD, 13));
        this.setBackground(new Color(18, 18, 18));
        this.setForeground(new Color(18, 18, 18));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.viewport.setBackground(new Color(18, 18, 18));
        this.viewport.setForeground(new Color(18, 18, 18));
        GroupLayout layout = new GroupLayout(viewportPanel);
        viewportPanel.setLayout(layout);
        viewportPanel.setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(yourLibrary, 0, 20, Short.MAX_VALUE)
                .addComponent(madeForYou, 0, 20, Short.MAX_VALUE)
                .addComponent(recentlyPlayed, 0, 20, Short.MAX_VALUE)
                .addComponent(songs, 0, 20, Short.MAX_VALUE)
                .addComponent(albums, 0, 20, Short.MAX_VALUE)
                .addComponent(artists, 0, 20, Short.MAX_VALUE)
                .addComponent(playlists, 0, 20, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(yourLibrary, 13, 13, 13)
                .addGap(15, 15, 15)
                .addComponent(madeForYou, 15, 15, 15)
                .addGap(15, 15, 15)
                .addComponent(recentlyPlayed, 0, 15, 15)
                .addGap(15, 15, 15)
                .addComponent(songs, 0, 15, 15)
                .addGap(15, 15, 15)
                .addComponent(albums, 0, 15, 15)
                .addGap(15, 15, 15)
                .addComponent(artists, 0, 15, 15)
                .addGap(30, 30, 30)
                .addComponent(playlists, 13, 13, 13));
        this.viewport.add(viewportPanel);
    }
}