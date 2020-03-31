/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

import NguyenTriHung.DTO.categoryDTO;
import NguyenTriHung.DTO.mycombobox;
import NguyenTriHung.DTO.orderDTO;
import NguyenTriHung.DTO.productDTO;
import NguyenTriHung.DTO.userDTO;
import NguyenTriHung.connect.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author hung
 */
public class orderDAO {

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

    public boolean check_Order(String customerID) {
        if (customerID.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Để trống ô CustomerID kìa.", "Error!", 0);
            return false;
        }
        return true;
    }

    public Vector<userDTO> showDetail_USER() {
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
        } finally {
            closeConnection();
        }
        return list;
    }

    public userDTO showList_USER(String id) {
        userDTO dto = new userDTO();
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tbluser WHERE id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
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
        } finally {
            closeConnection();
        }
        return dto;
    }

    public Vector<mycombobox> showDetails_Category() {
        Vector<mycombobox> mb = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblcategory WHERE isdelete1=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                boolean isdelete1 = rs.getBoolean("isdelete1");
                mb.add(new mycombobox(ID, name));
            }
        } catch (Exception e) {
        }
        return mb;
    }

    public Vector<productDTO> showDetails_Product(String category) {
        Vector<productDTO> dto = new Vector<>();
        boolean isdelete = true;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE Category=? AND isdelete=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, category);
            stm.setBoolean(2, isdelete);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String nameproduct = rs.getString("nameProduct");
                String category1 = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete1 = rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                dto.add(new productDTO(ID, nameproduct, category, description, price, isdelete1, quantity));
            }
        } catch (Exception e) {
        }
        return dto;
    }

    public Vector<orderDTO> showDetails_Order(String id) {
        Vector<orderDTO> list = new Vector<>();
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblorder where orderID=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int tong = rs.getInt("tong");
                String orderID = rs.getString("orderID");
                String idcustomer = rs.getString("idcustomer");
                String namestaff = rs.getString("namestaff");
                String date = rs.getString("date");
                list.add(new orderDTO(name, price, quantity, orderID, idcustomer, date, namestaff));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public Vector<orderDTO> showDetails_Allorder() {
        Vector<orderDTO> list = new Vector<>();
        try {
            conn = DB.getMyConnection();
            String sql = "select distinct idcustomer, date, orderID, sum(tong) as 'SUM' "
                    + "from tblorder "
                    + "GROUP BY idcustomer, date, orderID";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idcustomer = rs.getString("idcustomer");
                String date = rs.getString("date");
                String orderID = rs.getString("orderID");
                int total = rs.getInt("SUM");
                list.add(new orderDTO(orderID, idcustomer, date, total));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public orderDAO() {
    }

    public boolean Insert_Order(orderDTO dto, List<orderDTO> listBillDetail) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            for (orderDTO dTO : listBillDetail) {
                String sql = "INSERT INTO tblorder(name, price, quantity, tong, orderID, idcustomer, namestaff, date) values(?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dTO.getNameProduct());
                stm.setInt(2, dTO.getPrice());
                stm.setInt(3, dTO.getQuantity());
                stm.setInt(4, dTO.getPrice_x_quatity());
                stm.setString(5, dTO.getOrderID());
                stm.setString(6, dto.getIdcustomer());
                stm.setString(7, dTO.getNameStaff());
                stm.setString(8, dto.getDate());
                if (stm.executeUpdate() > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean Insert_Order2(orderDTO dto, List<orderDTO> listBillDetail) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "INSERT INTO tblorder(name, price, quantity, tong, orderID, idcustomer, namestaff, date) values(?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getNameProduct());
            stm.setInt(2, dto.getPrice());
            stm.setInt(3, dto.getQuantity());
            stm.setInt(4, dto.getPrice_x_quatity());
            stm.setString(5, dto.getOrderID());
            stm.setString(6, dto.getIdcustomer());
            stm.setString(7, dto.getNameStaff());
            stm.setString(8, dto.getDate());
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

    public boolean update_quantity(productDTO dto) {
        boolean result = false;
        try {
            conn = DB.getMyConnection();
            String sql = "Update tblproduct Set quantity = ? WHERE nameproduct=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getQuantity());
            stm.setString(2, dto.getName());
            if (stm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public productDTO findNameProduct(String nameProduct) {
        productDTO dto = null;
        try {
            conn = DB.getMyConnection();
            String sql = "SELECT * FROM tblproduct WHERE nameProduct=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, nameProduct);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String nameProduct1 = rs.getString("nameProduct");
                String category = rs.getString("Category");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                boolean isdelete = rs.getBoolean("isdelete");
                int quantity = rs.getInt("quantity");
                dto = new productDTO(id, nameProduct1, category, description, price, isdelete, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }


}
