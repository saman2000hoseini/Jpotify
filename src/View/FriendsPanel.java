package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FriendsPanel extends JPanel {
    private TransparentButton close = new TransparentButton(Icons.rescaleIcon(Icons.EXIT_ICON, 13, 13));
    private TransparentButton restoreDown = new TransparentButton(Icons.rescaleIcon(Icons.PREV_SIZE_ICON, 13, 13));
    private TransparentButton minimize = new TransparentButton(Icons.rescaleIcon(Icons.MINIMIZE_ICON, 13, 10));

    FriendsPanel() {
        super();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        ListenerForMouse listenerForMouse = new ListenerForMouse();
        setBackground(new Color(18, 18, 18));
        close.setBackground(new Color(24, 24, 24));
        restoreDown.setBackground(new Color(24, 24, 24));
        minimize.setBackground(new Color(24, 24, 24));
        close.addMouseListener(listenerForMouse);
        restoreDown.addMouseListener(listenerForMouse);
        minimize.addMouseListener(listenerForMouse);
        close.setSize(45, 30);
        restoreDown.setSize(45, 30);
        minimize.setSize(45, 30);
        close.setHorizontalAlignment(SwingConstants.CENTER);
        restoreDown.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        close.setVerticalAlignment(SwingConstants.CENTER);
        restoreDown.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(minimize, 45, 45, 45)
                .addComponent(restoreDown, 45, 45, 45)
                .addComponent(close, 45, 45, 45));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(minimize, 30, 30, 30)
                .addComponent(restoreDown, 30, 30, 30)
                .addComponent(close, 30, 30, 30));
        setVisible(true);
    }

    private class ListenerForMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                System.exit(0);
            }
            if (e.getSource() == restoreDown) {

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

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
