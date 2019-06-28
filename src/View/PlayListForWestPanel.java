package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PlayListForWestPanel extends JList{
    private DefaultListModel defaultListModel;
    int rolledOverRowIndex = -1;

    PlayListForWestPanel(DefaultListModel defaultListModel, WestPanel westPanel) {
        super(defaultListModel);
        this.defaultListModel = defaultListModel;
        addMouseMotionListener(new MouseListenerForPlayList(this));
        setCellRenderer(new PlayListCellRendererForWestPanel(westPanel));
        setOpaque(true);
        setVisibleRowCount(-1);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFixedCellHeight(30);
        setLayoutOrientation(VERTICAL);
        setBackground(new Color(18,18,18));
        setBorder(BorderFactory.createEmptyBorder());
    }

    public class MouseListenerForPlayList implements MouseMotionListener {
        private JList list;
        MouseListenerForPlayList(JList list)
        {
            this.list = list;
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            int row = locationToIndex(e.getPoint());
            if (row != rolledOverRowIndex) {
                rolledOverRowIndex = row;
                ((PlayListCellRendererForWestPanel) getCellRenderer().getListCellRendererComponent
                        (list, defaultListModel.getElementAt(row), row, false, false)).setRolledOverIndex(row);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }
    }
}
