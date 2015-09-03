package Networking;

import Networking.base.DataServer;
import Networking.base.Puertos;
import Networking.base.GestionDeMensajes;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sma.index;

/**
 *
 * @author diego_juliao
 */
public class ConexionVisionArtificial extends DataServer
{
    private JLabel estado;
    
    public ConexionVisionArtificial(JLabel estado) 
    {
        super(Puertos.Recibe_sistemaVisionArtificial, "Esperando Vision ");        
        this.estado = estado;
    }

    @Override
    public void AnalizadorDeMensajesSERVER(String msj) 
    {
        if( msj.equalsIgnoreCase(GestionDeMensajes.Msj_conectado ) == true )
            Tools.GestionLabels.CambiarLabel_correcto25x25(estado);
    }  
    
    public void solicitarCorreccionTrayectoria(int robotID, int Dirección, Point Posicion)
    {
        if(Posicion==null)//se trata de la primera vez que el robot pide ser centrado
            enviarSMS(  GestionDeMensajes.SolicitarCorreccionTrayectoria(robotID, Dirección, Posicion)  );
        
    }
    
    public void solicitarCorreccionTrayectoria(int robotID, int Dirección) 
    {
        solicitarCorreccionTrayectoria(robotID, Dirección, null);
    }

}
