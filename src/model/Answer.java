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
public class Answer implements Serializable{
    static final long serialVersionUID = 2L;
    Student student;
    Object[] answer;
    boolean[] isRights;
    boolean alreadyRegistration;
    
}
