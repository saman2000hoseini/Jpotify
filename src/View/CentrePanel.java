package View;

import javax.swing.*;
import java.awt.*;

public class CentrePanel extends JPanel {
    private MyTextField search = new MyTextField();
    CentrePanel (){
        super();
        search.setSize(100,30);
        setBackground(new Color(24, 24, 24));
        setVisible(true);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                                   .addComponent(search, 100, 100 ,100));
        layout.setVerticalGroup(layout.createSequentialGroup()
                                 .addComponent(search, 30 ,30 ,30));
    }
}
