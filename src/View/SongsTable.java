package View;

import Listeners.SongsTableButtons;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;

public class SongsTable extends JTable {

    private int rollOverRowIndex = -1;
    private int rollOverColumnIndex = -1;
    private int rollOverHeaderColumnIndex = -1;
    private int selectedHeaderColumnIndex = -1;
    private TableRowSorter<TableModel> sorter;
    private SongsTableButtons songsTableButtons = null;
    private DefaultTableModel defaultTableModel;
    public SongsTable(DefaultTableModel defaultTableModel) {
        super(defaultTableModel);
        this.defaultTableModel = defaultTableModel;
        setBorder(null);
        setBackground(new Color(24, 24, 24));
        RollOverListener lst = new RollOverListener(this);
        HeaderRollOverListener hlst = new HeaderRollOverListener(this);
        setColumnSelectionAllowed(false);
        setDefaultRenderer(Object.class, new SongsTableCellRenderer(false, -1));
        setIntercellSpacing(new Dimension(0, 1));
        setShowVerticalLines(true);
        setRowSelectionAllowed(true);
        setGridColor(new Color(40, 40, 40));
        getTableHeader().setDefaultRenderer(new SongsTableCellRenderer(true, -1));
        addMouseMotionListener(lst);
        addMouseListener(lst);
        tableHeader.addMouseMotionListener(hlst);
        tableHeader.addMouseListener(hlst);
        setRowHeight(39);
        getTableHeader().setBackground(new Color(24, 24, 24));
        getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));
        getTableHeader().setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 28)), getTableHeader().getBorder()));
        getTableHeader().setPreferredSize(new Dimension(1004, 39));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getColumnModel().getColumn(0).setPreferredWidth(50);
        getColumnModel().getColumn(0).setMaxWidth(50);
        getColumnModel().getColumn(0).setMinWidth(50);
        getColumnModel().getColumn(1).setPreferredWidth(40);
        getColumnModel().getColumn(1).setMaxWidth(40);
        getColumnModel().getColumn(1).setMinWidth(40);
        getColumnModel().getColumn(2).setPreferredWidth(320);
        getColumnModel().getColumn(2).setMaxWidth(320);
        getColumnModel().getColumn(2).setMinWidth(320);
        getColumnModel().getColumn(3).setPreferredWidth(160);
        getColumnModel().getColumn(3).setMaxWidth(160);
        getColumnModel().getColumn(3).setMinWidth(160);
        getColumnModel().getColumn(4).setPreferredWidth(210);
        getColumnModel().getColumn(4).setMaxWidth(210);
        getColumnModel().getColumn(4).setMinWidth(210);
        getColumnModel().getColumn(5).setPreferredWidth(90);
        getColumnModel().getColumn(5).setMaxWidth(90);
        getColumnModel().getColumn(5).setMinWidth(90);
        getColumnModel().getColumn(6).setPreferredWidth(40);
        getColumnModel().getColumn(6).setMaxWidth(40);
        getColumnModel().getColumn(6).setMinWidth(40);
        getColumnModel().getColumn(7).setPreferredWidth(80);
        getColumnModel().getColumn(7).setMaxWidth(80);
        getColumnModel().getColumn(7).setMinWidth(80);
        sorter = new TableRowSorter<>(getModel());
        setRowSorter(sorter);
    }

    public void newFilter(String text) {
        RowFilter<TableModel, Object> filter = null;
        try {
            filter = RowFilter.regexFilter(text );
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(filter);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (c instanceof SongsTableCellRenderer) {
            if (row == rollOverRowIndex) {
                if ((row == rollOverRowIndex && column == rollOverColumnIndex) && column < 5 || column == 6) {
                    ((SongsTableCellRenderer) c).setRolledOver(true);
                    if (column == 1)
                        ((SongsTableCellRenderer) c).setText("☓");
                } else {
                    ((SongsTableCellRenderer) c).setRolledOver(false);
                }
                c.setBackground(new Color(40, 40, 40));
                if (column == 6 || column == 1 || column == 0)
                    c.setForeground(Color.white);
            } else {
                ((SongsTableCellRenderer) c).setRolledOver(false);
            }
        }
        return c;
    }


    private class HeaderRollOverListener extends MouseInputAdapter {
        JTable table;

        HeaderRollOverListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int col = columnAtPoint(e.getPoint());
            if (selectedHeaderColumnIndex != col) {
                selectedHeaderColumnIndex = col;
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSelectedHeaderColumn(col);
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSortOrder(1);
                getTableHeader().repaint();
            } else {
                selectedHeaderColumnIndex = col;
                int sortOrder = ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).getSortOrder();
                if (sortOrder == 0)
                    sortOrder = 1;
                else if (sortOrder == 1)
                    sortOrder = 2;
                else if (sortOrder == 2)
                    sortOrder = 0;
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSortOrder(sortOrder);
            }
        }

        public void mouseExited(MouseEvent e) {
            if (rollOverColumnIndex != -1)
                ((SongsTableCellRenderer) (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                        , getColumnName(rollOverColumnIndex), false, true, -1, rollOverColumnIndex))).setRolledOverHeaderColumn(-1);
            getTableHeader().repaint();
            rollOverHeaderColumnIndex = -1;
        }

        public void mouseMoved(MouseEvent e) {
            int col = columnAtPoint(e.getPoint());
            if (col != rollOverHeaderColumnIndex) {
                rollOverHeaderColumnIndex = col;
                if (col != -1)
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setRolledOverHeaderColumn(col);
                getTableHeader().repaint();
            }
        }
    }

    private class RollOverListener extends MouseInputAdapter {
        JTable table;

        RollOverListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            songsTableButtons.doAction(col, (String) dataModel.getValueAt(row,2), (String) dataModel.getValueAt(row,3));
            if (col==0)
            {
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                PlayPanel.playState = 2;
            }
            else
            {
                defaultTableModel.removeRow(row);
            }
        }

        public void mouseExited(MouseEvent e) {
            rollOverRowIndex = -1;
            rollOverColumnIndex = -1;
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            if (row != rollOverRowIndex || col != rollOverColumnIndex) {
                rollOverRowIndex = row;
                rollOverColumnIndex = col;
                if (rollOverColumnIndex != -1)
                    ((SongsTableCellRenderer) (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                            , getColumnName(rollOverColumnIndex), false, true, -1, rollOverColumnIndex))).setRolledOverHeaderColumn(-1);
                rollOverHeaderColumnIndex = -1;
                repaint();
            }
        }
    }

    public void setSongsTableButtons(SongsTableButtons songsTableButtons)
    {
        this.songsTableButtons = songsTableButtons;
    }
}