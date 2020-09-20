/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

public class Client {
//    public static void main(String[] args) {
//        int port=9999;
//        Socket socket= new Socket()
//    }

    public void stringClient() {
        Socket socket = null;
        DataInputStream is = null;
        DataOutputStream os = null;
        ObjectInputStream ois = null;
        String maSV = "B16DCCN171";
        String hovaten = "Nguyễn Thị Lan Hương";
        int group = 1;
        try {
            socket = new Socket("10.170.46.220", 9999);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            os.writeUTF(maSV);
            os.writeUTF(hovaten);
            os.writeInt(group);
            int code = is.readInt();
            if (code == 0) {
                String str = is.readUTF();
                os.writeUTF(chuanHoa(str));
                String dai = is.readUTF();
                String ngan = is.readUTF();
                os.writeUTF(dem(ngan, dai) + "");
            } else {
                String dai = is.readUTF();
                String ngan = is.readUTF();
                os.writeUTF(dem(ngan, dai) + "");
                String str = is.readUTF();
                os.writeUTF(chuanHoa(str));
            }
            Answer answer = (Answer) ois.readObject();
            os.close();
            is.close();
            ois.close();
            socket.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public String chuanHoa(String str) {
        str = str.trim();
        str = str.toLowerCase();
        str = str.replaceAll("//s+", " ");
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

    public int dem(String dai, String ngan) {
        int count = 0;
        for (int i = 0; i < dai.length() - ngan.length() + 1; i++) {
            if (ngan.equals(dai.substring(i, i + ngan.length()))) {
                count++;
            }
        }
        return count;
    }

    public void numericClient() {
        Socket mySocket = null;
        DataInputStream is = null;
        DataOutputStream os = null;
        ObjectInputStream ois = null;
        String maSV = "B16DCCN171";
        String hovaten = "Nguyễn Thị Lan Hương";
        int group = 10;
        try {
            mySocket = new Socket("10.170.46.220", 10000);
            is = new DataInputStream(mySocket.getInputStream());
            os = new DataOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
            os.writeUTF(maSV);
            os.writeUTF(hovaten);
            os.writeInt(group);
            int code = is.readInt();
            int num1 = is.readInt();
            int num2 = is.readInt();
            if (code == 1) {
                os.writeInt(UCLN(num1, num2));
            } else {
                os.writeInt(BCNN(num1, num2));
            }
            int num3 = is.readInt();
            int num4 = is.readInt();
            if (code == 2) {
                os.writeInt(UCLN(num3, num4));
            } else {
                os.writeInt(BCNN(num3, num4));
            }
            int arrNum = is.readInt();
            int[] arr = new int[arrNum];
            for (int i = 0; i < arrNum; i++) {
                arr[i] = is.readInt();
            }
            arr = sort(arr);
            if (code == 1) {
                for (int i = 0; i < arrNum; i++) {
                    os.writeInt(arr[i]);
                }
            } else {
                for (int i = arrNum - 1; i >= 0; i--) {
                    os.writeInt(arr[i]);
                }
            }
            System.out.println(is.readUTF());
            Answer answer = (Answer) ois.readObject();
            os.close();
            is.close();
            ois.close();
            mySocket.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public int UCLN(int a, int b) {
        if (b == 0) {
            return a;
        }
        return UCLN(b, a % b);
    }

    public int BCNN(int a, int b) {
        return a * b / UCLN(a, b);
    }

    public int[] sort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.stringClient();
        client.numericClient();
    }
}
