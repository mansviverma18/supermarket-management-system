package dao;

import db.DBConnection;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static List<Product> all() {
        List<Product> list = new ArrayList<>();
        String q = "SELECT * FROM products";
        try (Connection c = DBConnection.get();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(q)) {
            while (rs.next())
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("category"), rs.getInt("quantity"), rs.getDouble("price")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static List<Product> search(String key) {
        List<Product> list = new ArrayList<>();
        String q = "SELECT * FROM products WHERE name LIKE ? OR category LIKE ?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(q)) {
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("category"), rs.getInt("quantity"), rs.getDouble("price")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static void insert(Product p) {
        String q = "INSERT INTO products(name,category,quantity,price) VALUES(?,?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(q)) {
            ps.setString(1, p.getName()); ps.setString(2, p.getCategory());
            ps.setInt(3, p.getQuantity()); ps.setDouble(4, p.getPrice());
            ps.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void update(Product p) {
        String q = "UPDATE products SET name=?,category=?,quantity=?,price=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(q)) {
            ps.setString(1, p.getName()); ps.setString(2, p.getCategory());
            ps.setInt(3, p.getQuantity()); ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getId());
            ps.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void delete(int id) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?")) {
            ps.setInt(1, id); ps.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
