package View;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class SongsTablePanel extends JPanel {
    private GroupLayout layout;
    private SongsTable songsTable;
    private ModernScrollPane modernScrollPane;
    SongsTablePanel()
    {
        super();
        String data[][] = {{"▶","✓","Keeper", "Ben Laver", "Keeper", "2019-25-6", "● ● ●", "3:14"},
                {"▶","✓","XX Intro", "Kate Simko, London...", "Kate Simko & London...", "2019-06-06","● ● ●" ,"3:07"},
                {"▶","✓","Planetary Disruption", "Isery", "Planetary Disruption", "2019-06-6","● ● ●" ,"3:26"},
                {"▶","✓","Scene 2", "Gui Boratto", "Pentagram", "2019-25-6","● ● ●" ,"2:53"},
                {"▶","✓","Fire Tree", "Keeno", "Futurist", "2019-25-6","● ● ●" ,"2:11"},
                {"▶","✓","If You Come Back(Aerocity Rework)", "Aerocity, Luis Miehlich", "If You Come Back(Aerocity Rework", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","Perfect", "Ed Sheeran", "Perfect", "2019-25-6","● ● ●" ,"4:31"},
                {"▶","✓","A Million Dreams in hossein's ass is the best and i am gay as fuck", "Benj Pasek is my nigga and sucks", "A Million Dreams in hossein's ass", "2019-25-6", "● ● ●","4:14"}};
        String columns[] = {"","","TITLE", "ARTIST", "ALBUM", "\uD83D\uDCC6", "","\uD83D\uDD52"};
        songsTable = new SongsTable(data, columns)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        songsTable.setBorder(null);
        modernScrollPane = new ModernScrollPane(songsTable);
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(32, 32)
                .addComponent(modernScrollPane, 200, 1035, 1035));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(modernScrollPane, 596, 596, 596));
        this.setLayout(layout);
        this.setBorder(null);
        this.setBackground(new Color(24, 24, 24));
    }

    public SongsTable getSongsTable() {
        return songsTable;
    }
}
