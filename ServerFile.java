/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfile;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import objectfile.ObjectFile;

/**
 *
 * @author MAgus
 */
public class ServerFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ArrayList<ThreadClient> allThread = new ArrayList<>();
            ServerSocket server = new ServerSocket(9090);
            Socket socket;
            new File("F:/cache").mkdirs();
            
            ObjectFile of = null;
            while (true) {                
            
                socket = server.accept();
                System.out.println(socket.getInetAddress().toString()+" masuk\r\n");
                synchronized(allThread)
                {
                    ThreadClient tc = new ThreadClient(socket,allThread);
                    allThread.add(tc);
                    Thread t = new Thread(tc);
                    t.start();
                }
                

                
                //System.out.println(of.getIsi() + of.getNama() + of.getUser() + of.getCommand());
                
                
            }
            //socket.close();
            //server.close();
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
                
    }
    
}
