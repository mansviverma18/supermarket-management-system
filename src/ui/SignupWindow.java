package ui;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupWindow extends JFrame {

    public SignupWindow() {
        setTitle("Sign Up");
        setSize(480, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(UIUtil.NAVY);
        setLayout(new GridBagLayout());

        /* -------- card -------- */
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(UIUtil.OFFWHITE);
        card.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 8, 10, 8);
        gc.fill   = GridBagConstraints.HORIZONTAL;

        /* -------- fields -------- */
        JLabel userLbl = new JLabel("Choose Username:");
        userLbl.setFont(UIUtil.FONT_M);
        JTextField userTxt = new JTextField(15);

        JLabel passLbl = new JLabel("Choose Password:");
        passLbl.setFont(UIUtil.FONT_M);
        JPasswordField passTxt = new JPasswordField(15);

        JButton register = UIUtil.styledButton("Register");
        JButton back     = UIUtil.styledButton("Back");

        /* -------- layout -------- */
        gc.gridx=0; gc.gridy=0; card.add(userLbl,gc);
        gc.gridx=1;            card.add(userTxt,gc);

        gc.gridx=0; gc.gridy=1; card.add(passLbl,gc);
        gc.gridx=1;            card.add(passTxt,gc);

        gc.gridx=0; gc.gridy=2; card.add(register,gc);
        gc.gridx=1;            card.add(back,gc);

        /* -------- actions -------- */
        register.addActionListener(e -> {
            String u = userTxt.getText().trim();
            String p = String.valueOf(passTxt.getPassword()).trim();
            if (u.isEmpty()||p.isEmpty()){ JOptionPane.showMessageDialog(this,"Fill all fields"); return; }
            if (UserDAO.register(new User(u,p))){
                JOptionPane.showMessageDialog(this,"Signup successful!");
                dispose(); new LoginWindow();
            } else JOptionPane.showMessageDialog(this,"Username taken");
        });

        back.addActionListener(e -> { dispose(); new LoginWindow(); });

        add(card);
        setVisible(true);
    }
}
