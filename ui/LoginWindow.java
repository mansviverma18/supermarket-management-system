package ui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        /* -------- frame basics -------- */
        setTitle("Login");
        setSize(480, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(UIUtil.NAVY);
        setLayout(new GridBagLayout());               // center the card

        /* -------- card panel -------- */
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(UIUtil.OFFWHITE);
        card.setBorder(new EmptyBorder(30, 40, 30, 40));
        card.setOpaque(true);                         // show bg

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 8, 10, 8);
        gc.fill   = GridBagConstraints.HORIZONTAL;

        /* -------- form controls -------- */
        JLabel userLbl = new JLabel("Username:");
        userLbl.setFont(UIUtil.FONT_M);
        JTextField userTxt = new JTextField(15);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setFont(UIUtil.FONT_M);
        JPasswordField passTxt = new JPasswordField(15);

        JButton login  = UIUtil.styledButton("Login");
        JButton signup = UIUtil.styledButton("Sign Up");

        /* -------- layout inside card -------- */
        gc.gridx = 0; gc.gridy = 0; card.add(userLbl, gc);
        gc.gridx = 1; card.add(userTxt, gc);

        gc.gridx = 0; gc.gridy = 1; card.add(passLbl, gc);
        gc.gridx = 1; card.add(passTxt, gc);

        gc.gridx = 0; gc.gridy = 2; card.add(login,  gc);
        gc.gridx = 1; card.add(signup, gc);

        /* -------- button actions -------- */
        login.addActionListener(e -> {
            if (UserDAO.login(userTxt.getText(),
                    String.valueOf(passTxt.getPassword()))) {
                dispose(); new MainWindow();
            } else JOptionPane.showMessageDialog(this, "Invalid credentials");
        });
        signup.addActionListener(e -> { dispose(); new SignupWindow(); });

        /* -------- add card to frame -------- */
        add(card);
        setVisible(true);
    }
}
