package Networking;

import Componentes.NXT.Robot;
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
    
    private Robot robots[];
    
    public ConexionVisionArtificial(JLabel estado) 
    {
        super(Puertos.Recibe_sistemaVisionArtificial, "Esperando Vision ");        
        this.estado = estado;
        this.robots = index.robots;
    }

    @Override
    public void AnalizadorDeMensajesSERVER(String msj) 
    {
        String cabezera = msj.split( GestionDeMensajes.Msj_divisor )[0];
        String cuerpo = msj.split( GestionDeMensajes.Msj_divisor )[1];

        if( cabezera.equals( GestionDeMensajes.Msj_MDVtoSMA_DespachoSolicitudTrayectoria ) == true )
        {   
            //ID,teta,Distancia_desface,0.0
            String parts[] = cuerpo.split( GestionDeMensajes.Msj_divisor_2 );

            int IDRobot = Integer.parseInt( parts[0] );
            float teta = Float.parseFloat( parts[1] );
            double Distancia_desface = Double.parseDouble( parts[2] );
            float tetaDesface = Float.parseFloat( parts[3] );

            index.robots[ IDRobot-1].corregirTrayectoriaNXT(teta, Distancia_desface, tetaDesface);
        }

    }  
    
    public void solicitarCorreccionTrayectoria(int robotID, int Dirección, Point Posicion)
    {        
        enviarSMS(  GestionDeMensajes.SolicitarCorreccionTrayectoria(robotID, Dirección, Posicion)  );
    }
    
    public void solicitarCorreccionTrayectoria(int robotID, int Dirección) 
    {
        //se trata de la primera vez que el robot pide ser centrado
        solicitarCorreccionTrayectoria(robotID, Dirección, new Point(-1, -1) );
    }
    
    public void correccionTrayectoriaTerminada(int robotID)
    {
        enviarSMS( GestionDeMensajes.Msj_SMAtoMDV_correctedTrayectoriaAPPLIED +  GestionDeMensajes.Msj_divisor + robotID);
    }

    @Override
    public void connected() 
    {
        Tools.GestionLabels.CambiarLabel_correcto25x25(estado);
    }

}
