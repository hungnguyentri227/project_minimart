/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

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
public class userDAO {

    userDTO dto = new userDTO();

    public final String check_Tel = "[0-9]{10}";
    public final String check_ID = "[0-9]{1,1000}";
    public final String check_email = "[0-9a-zA-Z ]{1,30}[@][a-zA-Z]{1,10}[.][a-zA-Z]{1,2}";

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public static ResultSet Ktra_trung() {
        String sql = "SELECT * FROM tbluser";
        return DB.Getdata(sql);
    }

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

    public boolean Validation_Login(String username, String password) {
        boolean result = true;
        if (username.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Không để trống ô USERNAME.");
            return false;
        }
        if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Không để trống ô PASSWORD.");
            return false;
        }
        return result;
    }

    public boolean Validation_Dangky(String ID, String username, String password,
            String fullname, String tel, String email, String role) {
        boolean result = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tbluser";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập đã sử dụng.", "Error!", 0);
                    return false;
                }
            }
        } catch (Exception e) {
        }

        if (ID.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô ID trống kìa.", "Error!", 0);
            return false;
        }
        if (username.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô username trống kìa.", "Error!", 0);
            return false;
        }
        if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô password trống kìa.", "Error!", 0);
            return false;
        }
        if (fullname.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô fullname trống kìa.", "Error!", 0);
            return false;
        }
        if (tel.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô Tel trống kìa.", "Error!", 0);
            return false;
        }
        if (email.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô email trống kìa.", "Error!", 0);
            return false;
        }
        if (role.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô role trống kìa.", "Error!", 0);
            return false;
        }
        if (role.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(null, "Đã có admin rồi.", "Error!", 0);
            return false;
        }
        if (!ID.matches(check_ID)) {
            JOptionPane.showMessageDialog(null, "ID là số từ 0-9.", "Error!", 0);
            return false;
        }
        if (!tel.matches(check_Tel)) {
            JOptionPane.showMessageDialog(null, "Phone là số và nhập 10 số.", "Error!", 0);
            return false;
        }
        if (!email.matches(check_email)) {
            JOptionPane.showMessageDialog(null, "Email không khớp [***@***.**].", "Error!", 0);
            return false;
        }
        return true;
    }

    public boolean Validation_Edit(String password, String fullname, String tel, String email, String role) {
        boolean result = true;
        if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô password trống kìa.", "Error!", 0);
            return false;
        }
        if (fullname.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô fullname trống kìa.", "Error!", 0);
            return false;
        }
        if (tel.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô Tel trống kìa.", "Error!", 0);
            return false;
        }
        if (email.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "ô email trống kìa.", "Error!", 0);
            return false;
        }
        if (role.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(null, "Đã có admin rồi.", "Error!", 0);
            return false;
        }
        if (!tel.matches(check_Tel)) {
            JOptionPane.showMessageDialog(null, "Phone là số và nhập 10 số.", "Error!", 0);
            return false;
        }
        if (!email.matches(check_email)) {
            JOptionPane.showMessageDialog(null, "Email không khớp [***@***.**].", "Error!", 0);
            return false;
        }
        return result;
    }

    public Vector<userDTO> showDetails() {
        Vector<userDTO> list = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tbluser WHERE isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                String role = rs.getString("role");
                boolean isdelete1 = rs.getBoolean("isdelete");
                list.add(new userDTO(ID, username, password, fullname, tel, email, role, isdelete1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public userDTO showLists(String id) {
        userDTO dto = new userDTO();
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tbluser WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                String role = rs.getString("role");
                boolean isdelete1 = rs.getBoolean("isdelete");
                dto = new userDTO(ID, username, password, fullname, tel, email, role, isdelete1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean Insert(userDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "INSERT INTO tbluser(id, username, password, fullname, tel, email, role, isdelete) values (?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setString(2, dto.getUsername());
            stm.setString(3, dto.getPassword());
            stm.setString(4, dto.getFullname());
            stm.setString(5, dto.getTel());
            stm.setString(6, dto.getEmail());
            stm.setString(7, dto.getRole());
            stm.setBoolean(8, dto.isIsdelete());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Trùng ID rồi.", "Error!", 0);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean Update(userDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tbluser SET username=?, password=?, fullname=?, tel=?,"
                    + " email=?, role=?, isdelete=? WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getFullname());
            stm.setString(4, dto.getTel());
            stm.setString(5, dto.getEmail());
            stm.setString(6, dto.getRole());
            stm.setBoolean(7, dto.isIsdelete());
            stm.setString(8, dto.getId());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(userDTO dto) {
        boolean result = false;
        boolean isdelete = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tbluser SET isdelete=? WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete);
            stm.setString(2, dto.getId());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public userDTO Login(String username, String password) {
        userDTO dto = new userDTO();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tbluser WHERE username=? and password=? AND isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setBoolean(3, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String fullname  = rs.getString("fullname");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                String role = rs.getString("role");
                boolean isdelete1  = rs.getBoolean("isdelete");
                dto = new userDTO(id, username1, password1, fullname, tel, email, role, isdelete1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean changePassWord(String newPassword, String userID) throws Exception {
        boolean check = false;
        try {
            conn = DB.getMyConnection();
            String sql = "Update tbluser Set password = ? Where id = ?";       
            stm = conn.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, userID);
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
