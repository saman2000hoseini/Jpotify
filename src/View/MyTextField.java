package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.geom.Path2D;

public class MyTextField extends JTextField {
    MyTextField() {
        super();
        this.setBorder(null);
        this.setOpaque(false);
        this.isOptimizedDrawingEnabled();
        this.setVisible(true);
        this.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                repaint();
            }
            public void removeUpdate(DocumentEvent e) {
                repaint();
            }
            public void insertUpdate(DocumentEvent e) {
                repaint();
            }
        });
    }

    int calculate(int wth, String text, Graphics2D g)
    {
        StringMetrics sm = new StringMetrics(g);
        for (int i = 1 ; i < text.length(); i++)
        {
            String s = text.substring(0, i);
            if (sm.getWidth(s) >= wth)
                return s.length();
        }
        return 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.setColor(new Color(255, 251, 244, 255));
        gd.fillRect(this.getHeight() / 2, 0, this.getWidth() - this.getHeight(), this.getHeight());
        gd.fillOval(0, 0, this.getHeight(), this.getHeight());
        gd.fillOval(this.getWidth() - this.getHeight(), 0, this.getHeight(), this.getHeight());
        gd.setColor(new Color(0, 0, 0, 255));
        gd.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 13));
        String text = getText();
        StringMetrics s = new StringMetrics(gd);
        double w = s.getWidth(text);
        if (w > (getWidth() - getHeight())) {
            System.out.println(text);
            text = text.substring(calculate((int)w - (getWidth() - getHeight()), text, gd), text.length());
        }
        gd.drawString(text, this.getHeight()/2, this.getHeight()/2 + 5);
        gd.dispose();
    }
}