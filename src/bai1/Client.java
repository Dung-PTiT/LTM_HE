/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Student;



/**
 *
 * @author Win 8.1 VS 10 Update
 */
public class Client {
    public static void main(String[] args) {
        String ServerIP = "10.170.46.220";
        int ServerPort = 11001;
        String maSV = "B16DCCN181";
        String hovaten = "Nguyen Van Huy";
        String IP = "10.170.46.165";
        int group = 1;
        try {
            Socket socket=new Socket(ServerIP,ServerPort);
            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream outO= new ObjectOutputStream(socket.getOutputStream());
            Student student = new Student(maSV, hovaten, IP, group);
            outO.writeObject(student);
            int code=in.readInt();
            if(code==1){
                String str=in.readUTF().trim();
                str = str.replaceAll("\\s+"," ");
                String temp[] = str.split(" ");
                str = ""; 
                for (int i = 0; i < temp.length; i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
                    if (i < temp.length - 1) 
                    str += " ";
                }
                out.writeUTF(str);
                String xau2 =in.readUTF();
                String  xau3 =in.readUTF();
                int solan=0;
                for(int i=0;i<xau2.length();i++){
                    if(xau2.substring(i, i+xau3.length()).contains(xau3)){
                        solan= solan+1;
                        i=i+xau3.length();
                    }
                    if(i>xau2.length()){
                        break;
                    }
                }
                out.writeUTF(""+solan);
            }else if(code==2){
                String xau2 =in.readUTF();
                String  xau3 =in.readUTF();
                int solan=0;
                for(int i=0;i<xau2.length();i++){
                    if(xau2.substring(i, i+xau3.length()).contains(xau3)){
                        solan= solan+1;
                        i=i+xau3.length();
                    }
                    if(i>xau2.length()){
                        break;
                    }
                }
                out.writeUTF(""+solan);
                
                String str=in.readUTF().trim();
                str = str.replaceAll("\\s+"," ");
                String temp[] = str.split(" ");
                str = ""; 
                for (int i = 0; i < temp.length; i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
                    if (i < temp.length - 1) 
                    str += " ";
                }
                out.writeUTF(str);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
}
