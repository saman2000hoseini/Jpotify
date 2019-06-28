package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FriendsTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;
    private ModernScrollPane modernScrollPane;
    private FriendsTable friendsTable;
    private GroupLayout layout;
    FriendsTablePanel(DefaultTableModel defaultTableModel, int frameHeight)
    {
        super();
        this.defaultTableModel = defaultTableModel;
        friendsTable = new FriendsTable(defaultTableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        friendsTable.setSize(220, frameHeight);
        friendsTable.setBackground(new Color(18, 18, 18));
        modernScrollPane = new ModernScrollPane(friendsTable, new Color(18, 18, 18));
        modernScrollPane.setHorizontalScrollBar(null);
        modernScrollPane.setBackground(new Color(18, 18, 18));
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(18, 18, 18));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(modernScrollPane, 220, 220, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(modernScrollPane, frameHeight - 45, frameHeight - 45, frameHeight - 45)
        );
        this.setLayout(layout);
    }

    public void update(int frameHeight)
    {
        this.getLayout().removeLayoutComponent(this);
        friendsTable.setSize(220, frameHeight);
        friendsTable.repaint();
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(modernScrollPane, 220, 220, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(modernScrollPane, frameHeight - 45, frameHeight - 45, frameHeight - 45)
        );
        this.setLayout(layout);
    }

}
