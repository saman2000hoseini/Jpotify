package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayListPanel extends JPanel {
    private DefaultListModel defaultListModel;
    private PlayListForWestPanel list;
    private ModernScrollPane modernScrollPane;
    private GroupLayout layout = new GroupLayout(this);

    PlayListPanel(DefaultListModel defaultListModel, WestPanel westPanel) {
        this.defaultListModel = defaultListModel;
        list = new PlayListForWestPanel(defaultListModel, westPanel);
        setBackground(new Color(18,18,18));
        modernScrollPane = new ModernScrollPane(list, new Color(18, 18, 18));
        modernScrollPane.setHorizontalScrollBar(null);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(modernScrollPane));
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(modernScrollPane));
        setLayout(layout);
    }

    public PlayListForWestPanel getList() {
        return list;
    }
}
