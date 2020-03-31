/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

import NguyenTriHung.DTO.categoryDTO;
import NguyenTriHung.DTO.productDTO;
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
public class categoryDAO {
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
    
    public boolean check_category(int ID, String name) {
        boolean result = true;
        if (name.trim().equals("")) {
             JOptionPane.showMessageDialog(null, "Không để trống Name.", "Error!", 0);
             result = false;
        } 
        if (ID < 0 ) {
             JOptionPane.showMessageDialog(null, "ID lớn hơn 0.", "Error!", 0);
             result = false;
        }
        return result;
    }
    
    public Vector<categoryDTO> showDetails() {
        Vector<categoryDTO> list = new Vector<>();
        boolean isdelete1 = true;
        try {
            conn = DB.getMyConnection();
            String sql = "Select * FROM tblcategory WHERE isdelete1=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete1);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                boolean isdelete2 = rs.getBoolean("isdelete1");
                list.add(new categoryDTO(ID, name, isdelete2));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    
    
    
    public categoryDTO showLists(int id) {
        categoryDTO dto = new categoryDTO();
         try {
            conn = DB.getMyConnection();
            String sql = "Select * FROM tblcategory WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID1 = rs.getInt("ID");
                String name = rs.getString("name");
                boolean isdelete2 = rs.getBoolean("isdelete1");
                dto = new categoryDTO(ID1, name, isdelete2);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
         return dto;
    }
    
    
    
    public boolean Insert(categoryDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "INSERT INTO tblcategory(ID, name, isdelete1) values(?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getId());
            stm.setString(2, dto.getName());
            stm.setBoolean(3, dto.isIsdelete1());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Exist ID.");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean Edit(categoryDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblcategory SET name=?, isdelete1=? WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setBoolean(2, dto.isIsdelete1());
            stm.setInt(3, dto.getId());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean Delete(categoryDTO dto) {
        boolean result = false;
        boolean isdelete1 = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblcategory SET isdelete1=? WHERE ID=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, dto.isIsdelete1());
            stm.setInt(2, dto.getId());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<productDTO> SelectDataCategory(String category) {
        Vector<productDTO> dto = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT B.ID, B.nameProduct, B.Category, B.price, B.description, B.isdelete, B.quantity "
                    + "FROM tblcategory A, tblproduct B "
                    + "WHERE B.Category = ? AND A.name = B.Category AND B.isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, category);
            stm.setBoolean(2, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("nameProduct");
                String category1 = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete1= rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                dto.add(new productDTO(ID, name, category, description, price, isdelete1, quantity));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public productDTO findProduct_to_Category(String category) {
        productDTO dto = null;
        boolean isdelete= true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE Category=? and isdelete= ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, category);
            stm.setBoolean(2, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nameproduct = rs.getString("nameProduct");
                String category1 = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                boolean isdelete1 = rs.getBoolean("isdelete");     
                dto = new productDTO(id, nameproduct, category1, description, price, isdelete1, quantity);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return dto;
    }
}
