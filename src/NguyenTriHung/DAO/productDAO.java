/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

import NguyenTriHung.DTO.productDTO;
import NguyenTriHung.DTO.userDTO;
import NguyenTriHung.connect.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author hung
 */
public class productDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public boolean check_add(int quantity, String name, float price, String description) {
        boolean result = true;
        if (name.trim().equals("") || description.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Không để trống dữ liệu", "Error!", 0);
            result = false;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "Price > 0", "Error!", 0);
            result = false;
        }
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity > 0", "Error!", 0);
            result = false;
        }
        return result;
    }

    public Vector<productDTO> showDetails() {
        Vector<productDTO> list = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("nameProduct");
                String category = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete1 = rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                list.add(new productDTO(ID, name, category, description, price, isdelete1, quantity));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public productDTO showList(int ID) {
        productDTO dto = new productDTO();
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, ID);
            rs = stm.executeQuery();
            if (rs.next()) {
                int ID1 = rs.getInt("ID");
                String name = rs.getString("nameProduct");
                String category = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete = rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                dto = new productDTO(ID, name, category, description, price, isdelete, quantity);
            }
        } catch (Exception e) {
        }
        return dto;
    }

    public boolean Insert(productDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "INSERT INTO tblproduct(ID, nameProduct, Category,"
                    + " price, description, isdelete, quantity) values(?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getID());
            stm.setString(2, dto.getName());
            stm.setString(3, dto.getCategory());
            stm.setInt(4, dto.getPrice());
            stm.setString(5, dto.getDescription());
            stm.setBoolean(6, dto.isIsdelete());
            stm.setInt(7, dto.getQuantity());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {

        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean Edit(productDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblproduct SET nameProduct=?, Category=?, price=?, description=?, isdelete=?, quantity=? WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getCategory());
            stm.setInt(3, dto.getPrice());
            stm.setString(4, dto.getDescription());
            stm.setBoolean(5, dto.isIsdelete());
            stm.setInt(6, dto.getQuantity());
            stm.setInt(7, dto.getID());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
        }
        return result;
    }

    public boolean delete(productDTO dto) {
        boolean result = false;
        boolean isdelete = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblproduct SET isdelete=? WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, dto.isIsdelete());
            stm.setInt(2, dto.getID());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public Vector<productDTO> Search_category(String category) {
        Vector<productDTO> dto = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE Category=? AND isdelete = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, category);
            stm.setBoolean(2, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("nameProduct");
                String category1 = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete1 = rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                dto.add(new productDTO(id, name, category, description, price, isdelete1, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

}
