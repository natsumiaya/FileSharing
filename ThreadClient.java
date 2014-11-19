/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfile;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
    private ArrayList<ThreadClient> alThread;
    private BufferedReader br = null;
    private BufferedOutputStream bos = null;
    private DataInputStream dis = null;
    private SocketAddress sa = null;
    public int BUFFER_SIZE = 1024;   
    private ObjectOutputStream ous;
    private ObjectInputStream ois;
    private String nama;
    
    public ThreadClient(Socket socketClient, ArrayList<ThreadClient> alThread)
    {
        this.socketClient = socketClient;
        this.alThread = alThread;
        this.sa = socketClient.getRemoteSocketAddress();
    }
    
    @Override
    public void run()
    {
        try {
            ous  = new ObjectOutputStream(socketClient.getOutputStream());
            ois = new ObjectInputStream(socketClient.getInputStream());
            ObjectFile of = new ObjectFile();
            try {
                while((of = (ObjectFile) ois.readObject()) != null)
                {
                    if(of.getCommand().equals("konek"))
                    {
                        this.nama = of.getUser();
                    }
                    for (int i = 0; i < this.alThread.size(); i++) {
                        System.out.println("nama : "+ alThread.get(i).nama);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            getSockClient().close();
            synchronized(this.alThread)
            {
                this.alThread.remove(this);
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
        for(int i=0;i<this.alThread.size();i++)
        {
            ThreadClient tc = this.alThread.get(i);
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
    public void setSockClient(Socket sockClient) {
        this.socketClient = sockClient;
    }
    
}

/*
Chat Message Class
*/
public class ChatMessage implements Serializable
{
  private static long serialVersionUID = 1l
  public String type, sender, content, recipient;
  
  public ChatMessage(String type, String sender, String content, String recipient)
  {
    this.type = type;
    this.sender = sender;
    this.content = content;
    this.recipient = recipient;
  }
  public String toString()
  {
   return "{type='"+type+"', sender='"+sender+"', content='"+content+"',recipient='"+recipient+"'}";
  }
}
}
