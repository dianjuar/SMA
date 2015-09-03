/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.NXT.conexion;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;

/**
 *
 * @author diego_juliao
 */
public abstract class Bluethoot_conector 
{
    public static final int norte=0;
    public static final int noreste=1;
    public static final int este=2;
    public static final int sureste=3;   
    public static final int sur=4;
    public static final int suroeste=5;
    public static final int oeste=6;
    public static final int noroeste=7;
   
    private NXTConnector conector;
    private Bluethoot_envia bt_env;
    private Bluethoot_recibe bt_rec;
    
    private boolean conectado;
    
    public Bluethoot_conector() 
    {
        conectado = false;
        conector = new NXTConnector();
    }
    
    public void enviarSiguientePaso(int grados, float distancia)
    {  
        String gradosSt = "";
        
        if(grados == 0)
            gradosSt="000";
        else if (grados < 100)
            gradosSt="0"+grados;
        else
            gradosSt= String.valueOf( grados );
        
        String sms = Componentes.NXT.conexion.Gestion_MensajesNXT.Movimiento +gradosSt+distancia;
        
        bt_env.enviar(sms);
    }
    
    public boolean connect(String name, String address, int ID)
    { 
       System.out.println(" connecting to " + name + " " + address);

       conectado = conector.connectTo(name, address, NXTCommFactory.BLUETOOTH);
       System.out.println(" connect result " + conectado);
       
       if(!conectado)
           return conectado;
       
       bt_env = new Bluethoot_envia( conector.getDataOut() );
       bt_rec = new Bluethoot_recibe( conector.getDataIn() )
       {    
            @Override
            public void analizadorDeSMS(String sms) 
            {
                if(sms.compareTo( Gestion_MensajesNXT.Cerrar ) == 0)
                {
                    cerrarConexion(false);
                    System.out.println("Me mandaron a cerrar");
                }
                else
                    analizadorDeSMS_BT(sms);
            }
       };
       
       return conectado;
    }
    
    public abstract void analizadorDeSMS_BT(String sms);
    
    public void cerrarConexion(boolean ImClosing)
    {
        try 
        {
            if(ImClosing)
                bt_env.enviar( Gestion_MensajesNXT.Cerrar );
                
            bt_env.close();
            bt_rec.close();
            conector.close();
            conectado = false;
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Bluethoot_conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConectado() {
        return conectado;
    }    
    
    public void calibrar_SensorOptico()
    {
        bt_env.enviar( Gestion_MensajesNXT.Calibrar_SensorOptico );
    }
    
    public void enviarRobotID(int robotID)
    {
        bt_env.enviar( Gestion_MensajesNXT.RobotID+robotID );
    }
    
    //movimiento
    public void enviar_adelante()
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_MovimientoSimple_ADELANTE() );
    }
    
    public void enviar_atras()
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_MovimientoSimple_ATRAS() );
    }
    
    public void enviar_izq()
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_MovimientoSimple_IZQUIERDA() );
    }
    
    public void enviar_der()
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_MovimientoSimple_DERECHA() );
    }
    
    public void enviar_parar()
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_MovimientoSimple_PARAR() );
    }
}
