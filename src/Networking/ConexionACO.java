/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import Componentes.NXT.Robot;
import Networking.base.DataClient;
import Networking.base.Encabezado_Mensajes;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author diego_juliao
 */
public class ConexionACO extends DataClient
{    
    private final int nAgentes;
    private final float VelocidadMaxima;
    private Robot robot[];
    
    public ConexionACO(String host, int port, float VelocidadMaxima, Robot robot[]) 
    {
        super(host, port);        
        
        nAgentes = 3;
        this.robot = robot;
        this.VelocidadMaxima = VelocidadMaxima;
        
    }
    
    public void enviar_SiguientePaso(int robotID)
    {
        String sms;
       
        sms = Encabezado_Mensajes.Msj_nextStep + Encabezado_Mensajes.Msj_divisor + robotID;
        
        send.enviar(sms);
    }

    @Override
    public void AnalizardorDeMensajesClient(String s) 
    {
        /*String sms = Encabezado_Mensajes.Msj_nextStep + Encabezado_Mensajes.Msj_divisor+
                ID + Encabezado_Mensajes.Msj_divisor_2 + mirada + Encabezado_Mensajes.Msj_divisor_2 + distancia;*/
        
        String encabezado = s.split( Encabezado_Mensajes.Msj_divisor )[0];
        String cuerpo = s.split( Encabezado_Mensajes.Msj_divisor )[1];
        
        if( encabezado.equals( Encabezado_Mensajes.Msj_nextStep ) )
        {
            int robotID = Integer.valueOf( cuerpo.split(Encabezado_Mensajes.Msj_divisor_2)[0] );
            int mirada = Integer.valueOf( cuerpo.split(Encabezado_Mensajes.Msj_divisor_2)[1] );
            float distancia = Float.valueOf( cuerpo.split(Encabezado_Mensajes.Msj_divisor_2)[2] );
            
            robot[robotID -1].recibirMovimiento( mirada, distancia);
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
        
        msj = Encabezado_Mensajes.Msj_PInicio_SMAtoACO;
        msj += Encabezado_Mensajes.Msj_divisor;
        msj += Encabezado_Mensajes.Msj_PInicio_SMAtoACO_HowMany + nAgentes + Encabezado_Mensajes.Msj_divisor_2;
        msj += Encabezado_Mensajes.Msj_PInicio_SMAtoACO_VelMax + VelocidadMaxima;
        
        return msj;
    }
    
}
