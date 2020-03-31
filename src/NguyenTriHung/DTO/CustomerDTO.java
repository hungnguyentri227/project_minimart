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
public class CustomerDTO {

    private String ID, FirstName, LastName, Tel, Email;
    private boolean isdelete;

    public CustomerDTO() {
    }
    
     public CustomerDTO(String ID, String FirstName, String LastName, String Tel, String Email, boolean isdelete) {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Tel = Tel;
        this.Email = Email;
        this.isdelete = isdelete;
    }
     
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(ID);
        v.add(FirstName);
        v.add(LastName);
        v.add(Tel);
        v.add(Email);
        v.add(isdelete);
        return v;
    }
    
    public Vector toVector2() {
        Vector v = new Vector();
        v.add(ID);
        v.add(LastName);
        v.add(Tel);
        return v;
    }

}
