/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Win 8.1 VS 10 Update
 */
public class Student implements Serializable{
    static final long serialVersionUID = 1L;
    String maSV;
    String hovaten;
    String IP;
    int group;

    public Student(String maSV, String hovaten, String IP, int group) {
        this.maSV = maSV;
        this.hovaten = hovaten;
        this.IP = IP;
        this.group = group;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
    
    
}
