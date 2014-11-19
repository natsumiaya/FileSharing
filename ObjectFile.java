/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectfile;

import java.io.Serializable;

/**
 *
 * @author MAgus
 */
public class ObjectFile implements Serializable{
    private byte[] isi;
    private String nama;
    private String command;
    private String user;

    /**
     * @return the isi
     */
    public byte[] getIsi() {
        return isi;
    }

    /**
     * @param isi the isi to set
     */
    public void setIsi(byte[] isi) {
        this.isi = isi;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
}
