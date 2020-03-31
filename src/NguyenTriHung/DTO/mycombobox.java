/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.DTO;

/**
 *
 * @author hung
 */
public class mycombobox {
    public Object value;
    public Object text;

    public mycombobox() {
    }

    
    public mycombobox(Object value, Object text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String toString() {
        return text.toString();

    }
}
