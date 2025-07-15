package dao;

import db.DBConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    /* ---------- public API ---------- */

    public static boolean login(String user, String pass) {
        String sql = "SELECT id FROM users WHERE username=? AND password=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            return ps.executeQuery().next();
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }

    public static boolean register(User u) {
        if (isUsernameTaken(u.getUsername())) return false;
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) { ex.printStackTrace(); return false; }
    }

    /* ---------- helper ---------- */

    private static boolean isUsernameTaken(String user) {
        String sql = "SELECT 1 FROM users WHERE username=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) { ex.printStackTrace(); return true; }
    }
}
