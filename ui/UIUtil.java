package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class UIUtil {

    /* ---------- BRAND PALETTE ---------- */
    public static final Color NAVY      = new Color(0x0A1128);
    public static final Color NAVY_DARK = new Color(0x001F54);
    public static final Color NAVY_MID  = new Color(0x034078);
    public static final Color CYAN      = new Color(0x1282A2);
    public static final Color OFFWHITE  = new Color(0xF6F7F9);   // card background
    public static final Color LIGHT = new Color(0xCCCCCC);  // brighter soft-gray
    // light label (for subtle UI)
    // light “card” background

    /* ---------- TYPOGRAPHY ---------- */
    public static final Font  FONT_L = new Font("Segoe UI", Font.BOLD , 24); // big headings
    public static final Font  FONT_M = new Font("Segoe UI", Font.BOLD , 20); // labels / buttons
    public static final Font  FONT_S = new Font("Segoe UI", Font.PLAIN, 14); // body text
    public static final Font FONT_XL = new Font("Segoe UI", Font.BOLD , 32); // Extra large font

    private UIUtil() {}   // utility class – prevent instantiation

    /* **********************************************************************
     *  G L O B A L   S T Y L E D   B U T T O N                            *
     *  ------------------------------------------------------------------ *
     *  - base colour  : NAVY_MID                                           *
     *  - hover colour : CYAN                                               *
     *  - press colour : NAVY_DARK                                          *
     *  - font         : FONT_M                                             *
     * ******************************************************************* */
    public static JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_M);
        btn.setForeground(OFFWHITE);
        btn.setBackground(NAVY_MID);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(CYAN); }
            @Override public void mouseExited (MouseEvent e) { btn.setBackground(NAVY_MID); }
            @Override public void mousePressed(MouseEvent e) { btn.setBackground(NAVY_DARK); }
            @Override public void mouseReleased(MouseEvent e){
                // return to hover colour if still inside, else base
                btn.setBackground(btn.getBounds().contains(e.getPoint()) ? CYAN : NAVY_MID);
            }
        });
        return btn;
    }
}
