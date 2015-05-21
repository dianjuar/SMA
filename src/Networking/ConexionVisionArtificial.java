package Networking;

import Networking.base.DataServer;
import Networking.base.Puertos;
import Networking.base.Encabezado_Mensajes;
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
        if( msj.equalsIgnoreCase(Encabezado_Mensajes.Msj_conectado ) == true )
            Tools.GestionLabels.CambiarLabel_correcto25x25(estado);
    }  

}
