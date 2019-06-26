package View;

import javax.swing.*;
import java.awt.*;

public class CustomLabelForSongsPanel extends JPanel {
    private JLabel label;

    CustomLabelForSongsPanel(String text, int width, int height) {
        super();
        this.setSize(width, height);
        label = new JLabel(text);
        label.setSize(this.getWidth() - this.getHeight(), this.getHeight());
        label.setForeground(new Color(220, 220, 220));
        label.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label, this.getWidth(), this.getWidth(), this.getWidth()));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(label, this.getHeight(), this.getHeight(), this.getHeight()));
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        StringMetrics s = new StringMetrics(gd);
        gd.setColor(new Color(29, 178, 73, 255));
        gd.fillOval(0, 0, this.getHeight(), this.getHeight());
        gd.fillRect(this.getHeight() / 2, 0, this.getWidth() - this.getHeight(), this.getHeight());
        gd.fillOval(this.getWidth() - this.getHeight(), 0, this.getHeight(), this.getHeight());
        gd.dispose();
    }
}
