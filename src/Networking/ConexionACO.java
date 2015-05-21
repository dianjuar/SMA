/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

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
    
    public ConexionACO(String host, int port, float VelocidadMaxima) 
    {
        super(host, port);        
        
        nAgentes = 3;
        this.VelocidadMaxima = VelocidadMaxima;
        
    }
    
    public void enviar_SiguientePaso(int robotID, int horientacion)
    {
        String sms;
       
        sms = Encabezado_Mensajes.Msj_nextStep + Encabezado_Mensajes.Msj_divisor + 
                robotID + Encabezado_Mensajes.Msj_divisor_2 + horientacion;
        
        send.enviar(sms);
    }

    @Override
    public void AnalizardorDeMensajesClient(String s) 
    {
        
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
