/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientfile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAgus
 */
public class ClientFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerForm form = new ServerForm();
        form.setVisible(true);
        try {
            // TODO code application logic here
            File f = new File("F:/TEMP/PDM.jpg");
            byte[] bytef = new byte[(int)f.length()];
            DataInputStream diStream = new DataInputStream(new FileInputStream(f));
            diStream.read(bytef);
            /*Socket socket = new Socket("localhost", 9090);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            ObjectFile of = new ObjectFile();
            of.setIsi(bytef);
            of.setNama(f.getName());
            of.setUser("AGUS");
            of.setCommand("KIRIM");
            
            ous.writeObject(of);
            ous.flush();
            ous.reset();
            
            ois.close();
            ois.close();
            socket.close();*/
        } catch (IOException ex) {
            Logger.getLogger(ClientFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
