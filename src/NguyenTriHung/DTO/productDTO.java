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
public class productDTO {

    private int ID;
    private String name, category, description;
    private int price;
    private boolean isdelete;
    private int quantity;

    public productDTO() {
    }

    public productDTO(int ID, String name, String category, String description, int price, boolean isdelete, int quantity) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.isdelete = isdelete;
        this.quantity = quantity;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Vector toVector() {
        Vector v = new Vector();
        v.add(ID);
        v.add(name);
        v.add(price);
        v.add(description);
        v.add(category);
        v.add(quantity);
        return v;
    }
    
    public Vector toVector2() {
        Vector v = new Vector();
        v.add(ID);
        v.add(name);
        v.add(price);
        v.add(description);
        v.add(quantity);
        return v;
    }
}
