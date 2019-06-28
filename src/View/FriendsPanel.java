package View;

import Model.FriendsActivity;
import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class FriendsPanel extends JPanel {
    private TransparentButton close = new TransparentButton("✕", false);
    private TransparentButton restoreDown = new TransparentButton("◻", false);
    private TransparentButton minimize = new TransparentButton("⚊", false);
    private ListenerForMouse listenerForMouse = new ListenerForMouse();
    private FriendsTablePanel friendsTablePanel;
    private DefaultTableModel defaultTableModel;
    private String[] tableHeader = {"Friend Activity"};
    private FriendsActivity f;
    private FriendsActivity[][] friendsActivities;

    FriendsPanel(int frameHeight) {
        super();
        try {
            f = new FriendsActivity(new Music("C:\\Users\\asus\\Music\\Anime\\MIYAVI vs KenKen - Flashback.mp3", "Roham", "test", "oof", null, null, "Rock", "Album")
                    , "testUser");
            friendsActivities = new FriendsActivity[12][1];
            for (int i = 0; i < 12; i++) {
                friendsActivities[i][0] = f;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        defaultTableModel = new DefaultTableModel(friendsActivities, tableHeader);
        friendsTablePanel = new FriendsTablePanel(defaultTableModel, frameHeight);
        listenerForMouse = new ListenerForMouse();
        close.addMouseListener(listenerForMouse);
        restoreDown.addMouseListener(listenerForMouse);
        minimize.addMouseListener(listenerForMouse);
        setBackground(new Color(18, 18, 18));
        close.setBackground(new Color(24, 24, 24));
        restoreDown.setBackground(new Color(24, 24, 24));
        minimize.setBackground(new Color(24, 24, 24));
        close.setSize(45, 30);
        restoreDown.setSize(45, 30);
        minimize.setSize(45, 30);
        close.setForeground(new Color(255, 255, 255));
        restoreDown.setForeground(new Color(255, 255, 255));
        minimize.setForeground(new Color(255, 255, 255));
        close.setHorizontalAlignment(SwingConstants.CENTER);
        restoreDown.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        close.setVerticalAlignment(SwingConstants.CENTER);
        restoreDown.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        setVisible(true);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(minimize, 45, 45, 45)
                        .addComponent(restoreDown, 45, 45, 45)
                        .addComponent(close, 45, 45, 45))
                .addComponent(friendsTablePanel, 255, 255, 255));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(minimize, 30, 30, 30)
                        .addComponent(restoreDown, 30, 30, 30)
                        .addComponent(close, 30, 30, 30))
                .addGap(15, 15, 15)
                .addComponent(friendsTablePanel, 220, 220, 1000));
    }

    private class ListenerForMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                System.exit(0);
            }
            if (e.getSource() == restoreDown) {
                if (((JFrame) (getParent().getParent().getParent().getParent().getParent())).getWidth() == Toolkit.getDefaultToolkit().getScreenSize().getWidth()
                        && ((JFrame) (getParent().getParent().getParent().getParent().getParent())).getHeight() == Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 40) {
                    ((MainFrame) (getParent().getParent().getParent().getParent().getParent())).setFullScreenMode(false);
                    ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setSize(950, 600);
                } else {
                    Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
                    ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setBounds(0, 0, (int) dimPant.getWidth(), (int) dimPant.getHeight() - 40);
                    ((MainFrame) (getParent().getParent().getParent().getParent().getParent())).setFullScreenMode(true);
                }
            }
            if (e.getSource() == minimize) {
                ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setState(Frame.ICONIFIED);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == close) {
                close.setBackground(new Color(255, 22, 14));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(100, 100, 100));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(100, 100, 100));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == close) {
                close.setBackground(new Color(24, 24, 24));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(24, 24, 24));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(24, 24, 24));
            }
        }
    }
}
