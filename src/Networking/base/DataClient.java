/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.base;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego_juliao
 */
public abstract class DataClient 
{
    private Socket s;
    private String host;
    private int port;
    
    protected DataSend send;
    protected DataRecibe recibe;
    protected boolean connected;

    public DataClient(String host, int port) 
    {
        connected = false;
        this.host = host;
        this.port = port;
    }
    
    protected void connect()
    {
        try 
        {
            s = new Socket(host, port);
            connected = true;
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DataClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        send = new DataSend(s);
        recibe = new DataRecibe(s) 
        {    
            @Override
            public void AnalizadorDeMensajes(String msj) {
               
                AnalizardorDeMensajesClient(msj);
            }
        };
    }
    
    public abstract void AnalizardorDeMensajesClient(String s);

    public boolean isConnected() {
        return connected;
    }
    
}
