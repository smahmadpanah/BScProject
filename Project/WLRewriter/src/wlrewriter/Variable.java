/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlrewriter;

import java.util.ArrayList;

/**
 *
 * @author Mohammad
 */
public class Variable {

    public String name;
    public String type;

    public Variable(String name) {
        this.name = name;
        type = "";
    }

    public Variable(String name, String type) {
        this.name = name;
        this.type = type;
    }
    

}
