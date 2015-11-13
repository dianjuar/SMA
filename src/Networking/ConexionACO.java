/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import Componentes.NXT.Robot;
import Networking.base.DataClient;
import Networking.base.GestionDeMensajes;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sma.index;

/**
 *
 * @author diego_juliao
 */
public class ConexionACO extends DataClient
{    
    private final int nAgentes;
    private Robot robot[];
    
    public ConexionACO(String host, int port, Robot robot[]) 
    {
        super(host, port);        
        
        nAgentes = 3;
        this.robot = robot;
        
    }
    
    public void enviar_SiguientePaso(int robotID)
    {
        String sms;
       
        sms = GestionDeMensajes.Msj_nextStep + GestionDeMensajes.Msj_divisor + robotID;
        
        send.enviar(sms);
    }

    @Override
    public void AnalizardorDeMensajesClient(String s) 
    {
        /*String sms = Encabezado_Mensajes.Msj_nextStep + Encabezado_Mensajes.Msj_divisor+
                ID + Encabezado_Mensajes.Msj_divisor_2 + mirada + Encabezado_Mensajes.Msj_divisor_2 + distancia;*/
        
        String encabezado = s.split(GestionDeMensajes.Msj_divisor )[0];
        String cuerpo = s.split(GestionDeMensajes.Msj_divisor )[1];
        
        if( encabezado.equalsIgnoreCase( GestionDeMensajes.Msj_ACotoSMA_Inicio ) )
        {
            index.ACO_ready = true;
        }   
        else if( encabezado.equalsIgnoreCase(GestionDeMensajes.Msj_nextStep ) )
        {
            int robotID = Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[0] );
            int mirada = Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[1] );
            float distancia = Float.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[2] );
            
            robot[robotID -1].recibirMovimiento( mirada, distancia);
        } 
        else if( encabezado.equalsIgnoreCase( GestionDeMensajes.Msj_ACOtoSMA_setVelocidad) )
        {
            int velocidad = Integer.valueOf( cuerpo );
            set_VelocidadRobots(velocidad);
        }

        
    }
    
    public void connectTo(JLabel estado, JButton b, JTextField t)
    {
        this.connect();
        
        if(connected)
        {
            Tools.GestionLabels.CambiarLabel_correcto25x25(estado);
            send.enviar( armarMsjParametrosInicio() );
            
            b.setEnabled(false);
            t.setEnabled(false);
        }
    }
    
    public boolean isConnected()
    {
        return isConnected();
    }
    
    private String armarMsjParametrosInicio()
    {
        String msj;
        
        msj = GestionDeMensajes.Msj_PInicio_SMAtoACO;
        msj += GestionDeMensajes.Msj_divisor;
        msj += GestionDeMensajes.Msj_PInicio_SMAtoACO_HowMany + nAgentes + GestionDeMensajes.Msj_divisor_2;
        msj += GestionDeMensajes.Msj_PInicio_SMAtoACO_VelMax + Robot.VelocidadMaxima + GestionDeMensajes.Msj_divisor_2;
        msj += GestionDeMensajes.Msj_PInicio_SMAtoACO_VelIni + Robot.VelocidadInicial;
        
        return msj;
    }
    
    private void set_VelocidadRobots(int v)
    {
        for (int i = 0; i < robot.length; i++)
            robot[i].setVelocidad(v);
        
    }
    
}
