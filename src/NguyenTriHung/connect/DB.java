/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hung
 */
public class DB {

    static Connection conn = null;

    public static Connection getMyConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Minimart";
            conn = DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static ResultSet Getdata(String sql) {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("lỗi truy vấn");
            return null;
        }
    }

}
