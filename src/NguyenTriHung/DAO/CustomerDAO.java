/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

import NguyenTriHung.DTO.CustomerDTO;
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
public class CustomerDAO {

    public final String check_Tel = "[0-9]{10}";
    public final String check_ID = "[C][U][S][-][0-9]{1,1000}";
    public final String check_email = "[0-9a-zA-Z ]{1,30}[@][a-zA-Z]{1,10}[.][a-zA-Z]{1,2}";

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public boolean check_Duplicate(String tel, String email) {
        boolean result = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblcustomer";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                if (tel.equals(rs.getString("tel"))) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại đã được sử dụng.", "Error!", 0);
                    result = false;
                }
                else if (email.equals(rs.getString("email"))) {
                    JOptionPane.showMessageDialog(null, "Email đã được sử dụng.", "Error!", 0);
                    result = false;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public boolean check_Insert(String id, String firstname, String lastname, String tel, String email) {
        boolean result = true;
        if (id.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "không để trống ô ID.", "Error.", 0);
            return false;
        }
        if (firstname.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "không để trống ô firstname.", "Error.", 0);
            return false;
        }
        if (lastname.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "không để trống ô lastname.", "Error.", 0);
            return false;
        }
        if (tel.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "không để trống ô Tel.", "Error.", 0);
            return false;
        }
        if (email.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "không để trống ô Email.", "Error.", 0);
            return false;
        }
        if (!id.matches(check_ID)) {
            JOptionPane.showMessageDialog(null, "ID Sai format [CUS-***].", "Error.", 0);
            return false;
        }
        if (!tel.matches(check_Tel)) {
            JOptionPane.showMessageDialog(null, "Tel: Sai format [0-9] và nhập 10 số.", "Error.", 0);
            return false;
        }
        if (!email.matches(check_email)) {
            JOptionPane.showMessageDialog(null, "Email: Sai format [***@***.**]", "Error.", 0);
            return false;
        }
        return result;

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

    public Vector<CustomerDTO> showDetails() {
        Vector<CustomerDTO> list = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblcustomer WHERE isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                boolean isdelete1 = rs.getBoolean("isdelete");
                list.add(new CustomerDTO(id, firstname, lastname, tel, email, isdelete1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public CustomerDTO showList(String id) {
        CustomerDTO dto = new CustomerDTO();
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblcustomer WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String id1 = rs.getString("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                boolean isdelete1 = rs.getBoolean("isdelete");
                dto = new CustomerDTO(id1, firstname, lastname, tel, email, isdelete1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean Insert(CustomerDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "INSERT into tblcustomer(id, firstname, lastname, tel, email, isdelete) values(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getID());
            stm.setString(2, dto.getFirstName());
            stm.setString(3, dto.getLastName());
            stm.setString(4, dto.getTel());
            stm.setString(5, dto.getEmail());
            stm.setBoolean(6, dto.isIsdelete());
            if (stm.executeUpdate() > 0) {
                result = true;
            }

        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Exist ID.", "Error.", 0);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean Update(CustomerDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblcustomer SET firstname=?, lastname=?, tel=?, email=?, isdelete=? WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getFirstName());
            stm.setString(2, dto.getLastName());
            stm.setString(3, dto.getTel());
            stm.setString(4, dto.getEmail());
            stm.setBoolean(5, dto.isIsdelete());
            stm.setString(6, dto.getID());
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

    public boolean Delete(CustomerDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "UPDATE tblcustomer SET isdelete=? WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, dto.isIsdelete());
            stm.setString(2, dto.getID());
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

}
