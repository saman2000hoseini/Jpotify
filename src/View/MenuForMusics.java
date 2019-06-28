package View;

import Listeners.AddLibrariesListener;
import Listeners.AddSongToLibrary;
import Model.Library;
import Model.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class MenuForMusics extends JPopupMenu implements AddLibrariesListener
{
    private JMenu addToPlaylist = new JMenu("Add to playlist");
    private AddSongToLibrary addSongToLibrary = null;
    private Music music;
    public MenuForMusics()
    {
        this.add(addToPlaylist);
        this.setBackground(new Color(18, 18, 18));
        this.setForeground(new Color(18, 18, 18));
        this.setSize(40, 40);
//        ListenerForMouse listenerForMouse = new ListenerForMouse();
//        addToPlaylist.addMouseListener(listenerForMouse);
    }

    @Override
    public void addLibraries(Vector<Library> libraries)
    {
        addToPlaylist.removeAll();
        for(Library library:libraries)
        {
            JMenuItem jMenuItem = new JMenuItem(library.getName());
            addToPlaylist.add(jMenuItem);
            jMenuItem.addMouseListener(new ListenerForMouse(jMenuItem));
        }
    }

    private class ListenerForMouse implements MouseListener
    {
        JMenuItem jMenuItem;

        public ListenerForMouse(JMenuItem jMenuItem)
        {
            this.jMenuItem = jMenuItem;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            menuActions(e);
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            menuActions(e);
        }

        private void menuActions(MouseEvent e)
        {
            addSongToLibrary.addSongToLib(music,jMenuItem.getText());
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }
    public void setAddSongToLibrary(AddSongToLibrary addSongToLibrary)
    {
        this.addSongToLibrary = addSongToLibrary;
    }

    public void setMusic(Music music)
    {
        this.music = music;
    }
}

/*

import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JPopupMenu;
        import javax.swing.JToggleButton;
        import javax.swing.event.PopupMenuEvent;
        import javax.swing.event.PopupMenuListener;

public class MenuButton extends JToggleButton {

    JPopupMenu popup;

    public MenuButton(String name, JPopupMenu menu) {
        super(name);
        this.popup = menu;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = MenuButton.this;
                if (b.isSelected()) {
                    popup.show(b, 0, b.getBounds().height);
                } else {
                    popup.setVisible(false);
                }
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                MenuButton.this.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
}
*/