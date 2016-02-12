/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import Componentes.NXT.Robot;
import Networking.base.DataClient;
import Networking.base.GestionDeMensajes;
import java.awt.Point;
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
    private Robot[] robots;
    
    public ConexionACO(String host, int port, Robot robot[]) 
    {
        super(host, port);        
        
        nAgentes = 3;
        this.robots = robot;
        
    }
    
    public void notifyExistense(int robotID)
    {
        String sms;
       
        sms = GestionDeMensajes.Msj_ImHere + GestionDeMensajes.Msj_divisor + robotID;
        
        send.enviar(sms);
    }

    @Override
    public void AnalizardorDeMensajesClient(String s) 
    {
        /*String sms = Encabezado_Mensajes.Msj_nextStep + Encabezado_Mensajes.Msj_divisor+
                ID + Encabezado_Mensajes.Msj_divisor_2 + mirada + Encabezado_Mensajes.Msj_divisor_2 + distancia;*/
        
        String encabezado, cuerpo = null;
        String mensajes[] = null;
        
        mensajes = s.split("\n");
        encabezado = s.split(GestionDeMensajes.Msj_divisor )[0];
        
        try
        {
            cuerpo = s.split(GestionDeMensajes.Msj_divisor )[1];
        }
        catch(IndexOutOfBoundsException e)
        {
            //se envía un mensaje sin cuepro.
        }
        
        if( encabezado.equalsIgnoreCase(GestionDeMensajes.Msj_ImHere ) )
        {
            int robotID = Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[0] );
            int mirada = Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[1] );
            float distancia = Float.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[2] );
            
            Point posicionDigital = new Point( Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[3] ),
                                               Integer.valueOf(cuerpo.split(GestionDeMensajes.Msj_divisor_2)[4] ));
            
            robots[robotID -1].recibirMovimiento( mirada, distancia, posicionDigital);
        } 
        else if( encabezado.equalsIgnoreCase( GestionDeMensajes.Msj_ACOtoSMA_setVelocidad) )
        {
            float velocidad = Float.valueOf( cuerpo );
            set_VelocidadRobots(velocidad);
        }
        else if( encabezado.equalsIgnoreCase(GestionDeMensajes.Msj_ACOtoSMA_Velocidades) )
        {
            for (String mensaje : mensajes) 
            {
                String SubCuerpo = null;
                SubCuerpo = mensaje.split(GestionDeMensajes.Msj_divisor )[1];
                
                int robotID = Integer.valueOf( SubCuerpo.split(GestionDeMensajes.Msj_divisor_2)[0] );
                float velocidadIz = Float.valueOf( SubCuerpo.split(GestionDeMensajes.Msj_divisor_2)[1] );
                float velocidadDer = Float.valueOf( SubCuerpo.split(GestionDeMensajes.Msj_divisor_2)[2] );

                robots[robotID-1].anadirInstruccionVelocidad( velocidadIz, velocidadDer );
            }
            
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
    
    private void set_VelocidadRobots(float v)
    {
        
        for (Robot robot : robots) 
        {
            if( robot.isConnected() )
                robot.setVelocidad(v);
        }
        
    }

}
