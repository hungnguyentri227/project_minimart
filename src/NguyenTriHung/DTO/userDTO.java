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
public class userDTO extends Vector<userDTO>{

    public String id, username, password, fullname, tel, email, role;
    public boolean isdelete;
    public String password_moi;

    public userDTO() {
    }

    public userDTO(String username, String password, boolean isdelete) {
        this.username = username;
        this.password = password;
        this.isdelete = isdelete;
    }

    public userDTO(String password, String password_moi) {
        this.password = password;
        this.password_moi = password_moi;
    }
    
    

    
    public userDTO(String id, String username, String password, String fullname, String tel, String email, String role, boolean isdelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.tel = tel;
        this.email = email;
        this.role = role;
        this.isdelete = isdelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(id);
        v.add(username);
        v.add(password);
        v.add(fullname);
        v.add(tel);
        v.add(email);
        v.add(role);
        return v;
    }
    
    public Vector toVector2() {
        Vector v = new Vector();
        v.add(id);
        v.add(username);
        v.add(fullname);
        return v;
    }
    

}
