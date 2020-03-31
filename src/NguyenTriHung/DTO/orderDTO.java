/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DTO;

import java.util.Vector;

/**
 *
 * @author hung
 */
public class orderDTO extends Vector<orderDTO>{

    private String nameProduct;
    private int price;
    private int quantity;
    private int price_x_quatity;
    public String orderID;
    public String idcustomer;
    public String date;
    private String nameStaff;
    
    private int total;

    public orderDTO() {
    }

    public orderDTO(String nameProduct, int price, int quantity, String orderID, String idcustomer, String date, String nameStaff) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.idcustomer = idcustomer;
        this.date = date;
        this.nameStaff = nameStaff;
    }

    public orderDTO(String nameProduct, int price, int quantity, int price_x_quatity, String orderID, String idcustomer, String date) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.price_x_quatity = price_x_quatity;
        this.orderID = orderID;
        this.idcustomer = idcustomer;
        this.date = date;
    }

    public orderDTO(String nameProduct, int price, int quantity, int price_x_quatity, String orderID, String idcustomer, String date, String nameStaff) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.price_x_quatity = price_x_quatity;
        this.orderID = orderID;
        this.idcustomer = idcustomer;
        this.date = date;
        this.nameStaff = nameStaff;
    }
    
    

    public orderDTO(String idcustomer, String date) {
        this.idcustomer = idcustomer;
        this.date = date;
    }

    public orderDTO(String nameProduct, int price, int quantity, int price_x_quatity) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.price_x_quatity = price_x_quatity;
    }
    
    

    public orderDTO(String idcustomer, String date, int total) {
        this.idcustomer = idcustomer;
        this.date = date;
        this.total = total;
    }

    public orderDTO(String orderID, String idcustomer, String date, int total) {
        this.orderID = orderID;
        this.idcustomer = idcustomer;
        this.date = date;
        this.total = total;
    }
    
    
    
    
    
    

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }
  
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_x_quatity() {
        return price_x_quatity;
    }

    public void setPrice_x_quatity(int price_x_quatity) {
        this.price_x_quatity = price_x_quatity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(String idcustomer) {
        this.idcustomer = idcustomer;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    


    
    

    public Vector toVector() {
        Vector v = new Vector();
        v.add(nameProduct);
        v.add(price);
        v.add(quantity);
        v.add(price_x_quatity);
        return v;
    }
    
    public Vector toVector2() {
        Vector v = new Vector();
        v.add(orderID);
        v.add(idcustomer);
        v.add(date);
        v.add(total);
        return v;
    }
}
