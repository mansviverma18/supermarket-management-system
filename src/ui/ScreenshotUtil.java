package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    /** Saves any Swing component as PNG (simple “screenshot”). */
    public static void saveComponent(Component cmp, File file) {
        BufferedImage img = new BufferedImage(
                cmp.getWidth(), cmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cmp.paint(img.getGraphics());
        try { ImageIO.write(img, "png", file); }
        catch (Exception ex) { ex.printStackTrace(); }
    }

    /** Simple helper that shows a file chooser and saves PNG of given component. */
    public static void quickSave(Component cmp, String defaultName) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(defaultName + ".png"));
        if (chooser.showSaveDialog(cmp) == JFileChooser.APPROVE_OPTION) {
            saveComponent(cmp, chooser.getSelectedFile());
            JOptionPane.showMessageDialog(cmp, "Saved!");
        }
    }
}
