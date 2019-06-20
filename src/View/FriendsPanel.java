package View;

import javax.swing.*;
import java.awt.*;

public class FriendsPanel extends JPanel {
    private JLabel close = new JLabel(Icons.rescaleIcon(Icons.VOLUME_ICON, 10, 10));
    private JLabel restoreDown = new JLabel(Icons.rescaleIcon(Icons.VOLUME_ICON, 10, 10));
    private JLabel minimize = new JLabel(Icons.rescaleIcon(Icons.VOLUME_ICON, 10, 10));
    FriendsPanel(){
        super();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(minimize, 0, 10, Short.MAX_VALUE)
                .addComponent(restoreDown, 0, 10, Short.MAX_VALUE)
                .addComponent(close, 0, 10, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(minimize, 0, 10, Short.MAX_VALUE)
                .addComponent(restoreDown, 0, 10, Short.MAX_VALUE)
                .addComponent(close, 0, 10, Short.MAX_VALUE));
        setBackground(new Color(18, 18, 18));
        setVisible(true);
    }
}
