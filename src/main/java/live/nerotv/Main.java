package live.nerotv;

import live.nerotv.modrinth.ModrinthAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {}

        SwingUtilities.invokeLater(() -> {
            final String url = "https://github.com/danieldieeins/modrinth-java-api";
            JLabel linkLabel = new JLabel("<html><h4>Hey you!</h4><p>This is nerotvlive's modrinth Java API. For API documentation and help<br>click here: <a href=''>" + url + "</a><p></html>");
            linkLabel.setCursor(Cursor.getDefaultCursor());

            linkLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(new URI(url));
                        } catch (IOException | URISyntaxException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Couldn't open URL:\n" + ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            });

            JOptionPane.showMessageDialog(
                    null,
                    linkLabel,
                    "nerotvlive's modrinth Java API",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        ModrinthAPI.main(args);
    }
}