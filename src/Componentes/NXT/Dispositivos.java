package Componentes.NXT;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dispositivos
{
    public static final dispositivo Frida = new dispositivo("Frida","00:16:53:06:30:55");
    public static final dispositivo Greta = new dispositivo("Greta","00:16:53:06:49:e0");
    public static final dispositivo Romer = new dispositivo("Romer","00:16:53:05:af:8e");
}

class dispositivo extends Thread
{
    public String nombre;
    public String direccion;

    public dispositivo(String nombre, String direccion)
    {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public void continuarHilo()
    {
        try 
        {
            sleep(500);
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(dispositivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.resume();
    }

}
