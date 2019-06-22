package View;

import javax.swing.*;
import java.awt.*;

public class CentrePanel extends JPanel {
    private CustomTextField search = new CustomTextField(175, 24, Icons.rescaleIcon(Icons.SEARCH2_ICON, 15, 15)
    , Icons.rescaleIcon(Icons.CLOSE2_ICON, 10, 10));
    private JLabel previous = new JLabel(Icons.rescaleIcon(Icons.LEFT_ARROW_ICON, 19, 19));
    private JLabel next = new JLabel(Icons.rescaleIcon(Icons.RIGHT_ARROW_ICON, 19, 19));
    CentrePanel (){
        super();
        setBackground(new Color(24, 24, 24));
        search.setBackground(new Color(24, 24, 24));
        search.textField.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        setVisible(true);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                                  .addContainerGap(11, 11)
                                  .addComponent(previous, 20, 20, 20)
                                  .addGap(11, 11, 11)
                                  .addComponent(next, 20, 20, 20)
                                  .addGap(17, 17,17)
                                  .addComponent(search, 175, 175 ,175));
        layout.setVerticalGroup(layout.createSequentialGroup()
                                .addContainerGap(5,5)
                                .addGroup(layout.createParallelGroup()
                                          .addComponent(previous, 20, 20, 20)
                                          .addComponent(next, 20, 20, 20)
                                          .addComponent(search, 24 ,24 ,24)));
    }
}
