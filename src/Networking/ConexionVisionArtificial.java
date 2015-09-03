package Networking;

import Networking.base.DataServer;
import Networking.base.Puertos;
import Networking.base.GestionDeMensajes;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import sma.index;

/**
 *
 * @author diego_juliao
 */
public class ConexionVisionArtificial extends DataServer
{
    private JLabel estado;
    private JButton robotsButtons[];
    
    public ConexionVisionArtificial(JLabel estado, JButton robotsButtons[]) 
    {
        super(Puertos.Recibe_sistemaVisionArtificial, "Esperando Vision ");        
        this.estado = estado;
        this.robotsButtons = robotsButtons;
    }

    @Override
    public void AnalizadorDeMensajesSERVER(String msj) 
    {
        if( msj.equalsIgnoreCase(GestionDeMensajes.Msj_conectado ) == true )
        {
            Tools.GestionLabels.CambiarLabel_correcto25x25(estado);
            
            for (JButton StarButtonRobot : robotsButtons)
                StarButtonRobot.setEnabled(true);
        }
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
