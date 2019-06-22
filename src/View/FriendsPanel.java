package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FriendsPanel extends JPanel {
    private TransparentButton close = new TransparentButton("✕");
    private TransparentButton restoreDown = new TransparentButton("◻");
    private TransparentButton minimize = new TransparentButton("⚊");

    FriendsPanel() {
        super();
        this.setSize(255, 952);
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
