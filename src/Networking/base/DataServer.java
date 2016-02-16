package Networking.base;

import Networking.base.DataRecibe;
import Networking.base.DataSend;
import Networking.base.GestionDeMensajes;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sma.index;

public abstract class DataServer extends Thread
{
    private int puerto;
    private ServerSocket Ssocket;
    private Socket socket;
    
    private DataRecibe D_r;
    private DataSend D_s;
    
    private String nombreConexion;
    private boolean pararHilo;
    
    private boolean connected;
    
    
    public DataServer(int puerto, String nombreConexion) 
    {        
        this.puerto = puerto; 
        this.nombreConexion = nombreConexion;
        
        pararHilo = connected = false;
        
        
        try 
        {
            Ssocket = new ServerSocket(puerto);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        start();
    }
    
    public void pararHilo()
    {
        pararHilo = true;
        cerrarConexioServer();
    }
    
    public void cerrarConexioServer()
    {   
        try 
        {
            D_r.cerrarConexion();
            D_s.cerrarConexion();
            socket.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.resume();
    }
    
    public void run()
    {
        while(!pararHilo)
        {
            System.out.println("Esperando conexión entrante por el puerto "+puerto+". Conexion de "+nombreConexion);
            System.out.println("Esperando Conexion...");
            
            try 
            {
                socket = Ssocket.accept();
                connected();

                System.out.println("Conectado");
                
                connected = true;

                D_s = new DataSend(socket);
                D_r = new DataRecibe(socket)
                {  
                    @Override
                    public void AnalizadorDeMensajes(String msj) 
                    {
                        if( msj.equalsIgnoreCase(GestionDeMensajes.Msj_cerrar ) )
                            cerrarConexioServer();
                        else
                            AnalizadorDeMensajesSERVER(msj);
                    }
                };
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        this.suspend();
        }
        
        System.out.println("mori");        
    }

    public boolean isConnected() {
        return connected;
    }
    
    public abstract void AnalizadorDeMensajesSERVER(String msj);
    
    public void enviarSMS(String msj)
    {
        if(index.DEBUG)
                System.out.println("Enviando a:"+socket.getInetAddress()+" MSJ="+msj+"\n*********************");
        D_s.enviar(msj);
    }

    public abstract void connected();
    
}
