/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DAO;

import NguyenTriHung.DTO.orderDTO;
import NguyenTriHung.connect.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author hung
 */
public class billDAO {

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

    public orderDTO showBill() {
        orderDTO dto = null;
        try {
            conn = DB.getMyConnection();

            String sql = "select distinct idcustomer, date FROM tblorder GROUP BY idcustomer, date";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getIdcustomer());
            stm.setString(2, dto.getDate());
            rs = stm.executeQuery();

            while (rs.next()) {
                String idcustomer = rs.getString("idcustomer");
                String date = rs.getString("date");
                dto = new orderDTO(idcustomer, date);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return dto;
    }

    public Vector<orderDTO> showBill_Product(String idcustomer, String date, String orderID) {
        Vector<orderDTO> dto = new Vector<>();
        try {
            conn = DB.getMyConnection();
            String sql = "select name, price, quantity, tong from tblorder where idcustomer = ? and date = ? and orderID= ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, idcustomer);
            stm.setString(2, date);
            stm.setString(3, orderID);
            rs = stm.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int tong = rs.getInt("tong");
                dto.add(new orderDTO(name, price, quantity, tong)) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }
}
