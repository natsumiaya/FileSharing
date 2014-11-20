/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfile;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
            while (true) 
            {                
                Socket socketClient = server.accept();
                System.out.println(socketClient.getInetAddress().toString()+" Client masuk");
                synchronized(allThread)
                {
                    ThreadClient tc = new ThreadClient(socketClient,allThread);
                    allThread.add(tc);
                    Thread t = new Thread(tc);
                    t.start();
                }
            }            
        } 
        catch(Exception e)
        {
        }
                
    }
    
}
