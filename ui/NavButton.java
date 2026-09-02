package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavButton extends JPanel {

    private final JLabel label;
    private final Color base = UIUtil.OFFWHITE;
    private float flashAlpha = 0f;           // 0–1 → lightning strip opacity
    private final Timer hoverTimer;

    public NavButton(String text, Icon icon, Runnable onClick) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180, 72));
        setOpaque(false);

        // label block
        label = new JLabel("  " + text, icon, JLabel.LEFT);
        label.setFont(UIUtil.FONT_M);
        label.setForeground(UIUtil.NAVY_DARK);
        label.setOpaque(true);
        label.setBackground(base);
        add(label, BorderLayout.CENTER);

        // fade‑in/out hover effect
        hoverTimer = new Timer(20, e -> {
            if (isHovering) flashAlpha = Math.min(1f, flashAlpha + .12f);
            else            flashAlpha = Math.max(0f, flashAlpha - .12f);
            repaint();
        });
        hoverTimer.start();

        addMouseListener(mouseAdapter(onClick));
        label.addMouseListener(mouseAdapter(onClick)); // whole area clickable
    }

    private boolean isHovering;

    private MouseAdapter mouseAdapter(Runnable onClick){
        return new MouseAdapter() {
            public void mouseEntered(MouseEvent e){ isHovering = true;   label.setBackground(UIUtil.CYAN);}
            public void mouseExited (MouseEvent e){ isHovering = false;  label.setBackground(base);      }
            public void mousePressed(MouseEvent e){ label.setBackground(UIUtil.NAVY_MID);}
            public void mouseReleased(MouseEvent e){
                label.setBackground(isHovering?UIUtil.CYAN:base);
                if (isHovering) onClick.run();
            }
        };
    }

    // lightning strip paint
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(flashAlpha>0){
            Graphics2D g2=(Graphics2D)g.create();
            g2.setComposite(AlphaComposite.SrcOver.derive(flashAlpha*0.6f));
            g2.setColor(UIUtil.OFFWHITE);
            g2.fillRect(0,0,4,getHeight());
            g2.dispose();
        }
    }
}
