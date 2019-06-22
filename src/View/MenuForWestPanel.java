package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuForWestPanel extends JMenuBar {
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenu help = new JMenu("help");
    private JMenu playback = new JMenu("Playback");
    private JMenu menu = new JMenu("");
    private JMenuItem newPlayList = new JMenuItem("New Playlist");
    private JMenuItem newPlayListFolder = new JMenuItem("New Playlist Folder");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem search = new JMenuItem("Search");
    private JMenuItem play = new JMenuItem("Play");
    private JMenuItem next = new JMenuItem("Next");
    private JMenuItem previous = new JMenuItem("Previous");
    private JMenuItem seekForward = new JMenuItem("SeekForward");
    private JMenuItem seekBackward = new JMenuItem("SeekBackward");
    private JMenuItem shuffle = new JMenuItem("Shuffle");
    private JMenuItem repeat = new JMenuItem("Repeat");
    private JMenuItem volumeUp = new JMenuItem("Volume Up");
    private JMenuItem volumeDown = new JMenuItem("Volume Down");
    private JMenuItem jpotifyHelp = new JMenuItem("JPotify Help");
    private JMenuItem aboutJPotify = new JMenuItem("About JPotify");

    MenuForWestPanel() {
        super();
        this.file.setMnemonic(KeyEvent.VK_F);
        this.file.add(newPlayList);
        this.file.add(newPlayListFolder);
        this.file.addSeparator();
        this.file.add(exit);
        this.edit.setMnemonic(KeyEvent.VK_E);
        this.edit.add(search);
        this.help.setMnemonic(KeyEvent.VK_H);
        this.help.add(jpotifyHelp);
        this.help.addSeparator();
        this.help.add(aboutJPotify);
        this.playback.setMnemonic(KeyEvent.VK_P);
        this.playback.add(play);
        this.playback.addSeparator();
        this.playback.add(next);
        this.playback.add(previous);
        this.playback.add(seekForward);
        this.playback.add(seekBackward);
        this.playback.addSeparator();
        this.playback.add(shuffle);
        this.playback.add(repeat);
        this.playback.addSeparator();
        this.playback.add(volumeUp);
        this.playback.add(volumeDown);
        this.menu.add(file);
        this.menu.add(edit);
        this.menu.add(playback);
        this.menu.add(help);
        this.setBackground(new Color(18, 18, 18));
        this.setForeground(new Color(18, 18, 18));
        this.setSize(40, 40);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.menu.setIcon(Icons.rescaleIcon(Icons.MENU_ICON, 35, 35));
        this.menu.setSize(35, 50);
        this.add(menu);
    }
}
