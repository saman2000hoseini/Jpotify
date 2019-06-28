package View;

import Model.FriendsActivity;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FriendsTableCellRenderer extends JPanel implements TableCellRenderer {
    private boolean isHeader;
    private int rolledOverRow;
    private FriendsActivity friendsActivity;
    private JLabel userName;
    private JLabel title;
    private JLabel album;
    private JLabel artist;
    private JLabel image;

    GroupLayout layout = new GroupLayout(this);

    FriendsTableCellRenderer(boolean isHeader, int rolledOverRow) {
        this.isHeader = isHeader;
        this.rolledOverRow = rolledOverRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(18, 18, 18));
        friendsActivity = (FriendsActivity) value;
        userName = new JLabel(friendsActivity.getUser());
        title = new JLabel(friendsActivity.getMusicArtist());
        album = new JLabel(friendsActivity.getMusicAlbum());
        artist = new JLabel(friendsActivity.getMusicArtist());
        image = new JLabel(ImageEditor.rescaleImage(ImageEditor.circleMaskImage(friendsActivity.getMusicImage()), 50, 50));
        userName.setForeground(Color.white);
        title.setForeground(new Color(120, 120, 120));
        album.setForeground(new Color(120, 120, 120));
        artist.setForeground(new Color(120, 120, 120));
        userName.setBackground(new Color(18, 18, 18));
        title.setBackground(new Color(18, 18, 18));
        album.setBackground(new Color(18, 18, 18));
        artist.setBackground(new Color(18, 18, 18));
        userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 16));
        album.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        artist.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        title.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(image, 50, 50, 50)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(userName, 100, 150, 150)
                        .addComponent(title, 100, 150, 150)
                        .addComponent(album, 100, 150, 150)
                        .addComponent(artist, 100, 150, 150)));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(image, 50, 50, 50)
                        .addGap(25, 25, 25))
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(userName, 20, 20, 20)
                        .addComponent(title, 20, 20, 20)
                        .addComponent(artist, 20, 20, 20)
                        .addComponent(album, 20, 20, 20)
                        .addGap(10, 10, 10)));
        this.setLayout(layout);
        return this;
    }

    public static void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }

}
