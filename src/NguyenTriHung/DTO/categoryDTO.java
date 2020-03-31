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
public class categoryDTO {
    private int id;
    private String name;
    private boolean isdelete1;

    public categoryDTO() {
    }

    public categoryDTO(int id, String name, boolean isdelete1) {
        this.id = id;
        this.name = name;
        this.isdelete1 = isdelete1;
    }

    public boolean isIsdelete1() {
        return isdelete1;
    }

    public void setIsdelete1(boolean isdelete1) {
        this.isdelete1 = isdelete1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(id);
        v.add(name);
        v.add(isdelete1);
        return v;
    }
    
    public Vector toVector2() {
        Vector v = new Vector();
        v.add(name);
        return v;
    }
}
