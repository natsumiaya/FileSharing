/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfile;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectfile.ObjectFile;

/**
 *
 * @author MAgus
 */
public class ThreadClient implements Runnable {
    private Socket socketClient;
    private ArrayList<ThreadClient> allThread;
    private BufferedReader br = null;
    private BufferedOutputStream bos = null;
    private DataInputStream dis = null;
    private SocketAddress sa = null;   
    private ObjectOutputStream ous;
    private ObjectInputStream ois;
    private String nama;
    private String fnama;
    private DataOutputStream doStream;
    public int BUFFER_SIZE = 1024;
    
    public ThreadClient(Socket socketClient, ArrayList<ThreadClient> allThread)
    {
        this.socketClient = socketClient;
        this.allThread = allThread;
        this.sa = socketClient.getRemoteSocketAddress();
    }
    
    @Override
    public void run()
    {
        try {
            ous  = new ObjectOutputStream(socketClient.getOutputStream());
            ois = new ObjectInputStream(socketClient.getInputStream());
            new File("F:/cache").mkdirs();
            ObjectFile objf = new ObjectFile();
            try {
                while((objf = (ObjectFile) ois.readObject()) != null)
                {
                    if(objf.getCommand().equals("konek"))
                    {
                        this.nama = objf.getUser();
                    }
                    else if(objf.getCommand().equals("kirim"))
                    {
                        System.out.println("tulis");
                        //ObjectFile objfn = (ObjectFile) ois.readObject();
                        this.fnama = objf.getNama();
                        File file = new File("F:/cache/" + objf.getNama());
                        doStream = new DataOutputStream(new FileOutputStream(file));
                        doStream.write(objf.getIsi());
                        System.out.println("file write");
                    }
                    for (int i = 0; i < this.allThread.size(); i++) {
                        System.out.println("nama : "+ allThread.get(i).nama);
                    }
                    for (int i = 0; i < this.allThread.size(); i++) {
                        System.out.println("fnama : "+ allThread.get(i).fnama);
                    }
                    
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            getSockClient().close();
            synchronized(this.allThread)
            {
                this.allThread.remove(this);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(String msg) throws IOException
    {
        this.bos.write(msg.getBytes());
        this.bos.flush();
    }
    
    public synchronized void sendMessage(String msg) throws IOException
    {
        for(int i=0;i<this.allThread.size();i++)
        {
            ThreadClient tc = this.allThread.get(i);
            tc.send(msg);
        }
    }

    /**
     * @return the sockClient
     */
    public Socket getSockClient() {
        return socketClient;
    }

    /**
     * @param sockClient the sockClient to set
     */
    public void setSocketClient(Socket sockClient) {
        this.socketClient = sockClient;
    }
    
}
