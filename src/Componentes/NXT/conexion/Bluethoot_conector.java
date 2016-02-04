/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.NXT.conexion;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    
    //se tomará el signo 3digitos para la parte entera y 2 para la parte decimal +###.##
    public String preparar_FloatParaEnviar(float numeroF)
    {
        String numeroFSt = numeroF >= 0.0 ? "+":"-"; 
        
        DecimalFormat df = new DecimalFormat("###.##");
        df.setRoundingMode(RoundingMode.DOWN);

        numeroF = numeroF * (numeroF < 0 ? -1:1);
        String numeroFFormat = df.format( numeroF );

        //si es un número entero
        if(!numeroFFormat.contains(","))
            numeroFFormat += ",0";

        int entero = Integer.valueOf( numeroFFormat.split(",")[0] ),
            decimal = Integer.valueOf( numeroFFormat.split(",")[1] );

        
        //perar la parte entera 
        if(entero == 0)
            numeroFSt += "000"; 
        else
            if (entero < 10)
                numeroFSt +="00"+entero;
            else
                if(entero < 100)
                    numeroFSt +="0"+entero;
                else
                    numeroFSt += String.valueOf( entero );

        
        numeroFSt += ".";
        
        //perar la parte decimal
        if (decimal == 0)
            numeroFSt +="00";
        else
            if( decimal < 10 )
                numeroFSt += "0"+decimal;
            else
                numeroFSt += String.valueOf( decimal );
        
        return numeroFSt;
    }
    
    public void enviarSiguientePaso(float grados, double distancia)
    {
        
        String sms = Componentes.NXT.conexion.Gestion_MensajesNXT.Movimiento +
                preparar_FloatParaEnviar(grados)+
                preparar_FloatParaEnviar((float) distancia);
        
        bt_env.enviar(sms);
    }
    
    public void corregirTrayectoria( float teta, double distanciaDesface, float tetaDesface )
    {
        String sms= Componentes.NXT.conexion.Gestion_MensajesNXT.CorreccionDeTrayectoria +
                    preparar_FloatParaEnviar(teta) + 
                    preparar_FloatParaEnviar((float) distanciaDesface) +
                    preparar_FloatParaEnviar(tetaDesface);
        
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

    public void enviarVelocidad(float v)
    {
        bt_env.enviar( Gestion_MensajesNXT.Enviar_SetVelocidad(v) );
    }

    public void enviarVelocidad(float VL, float VR) 
    {
        String sms= Gestion_MensajesNXT.SetVelocidad_izq_der +
                    preparar_FloatParaEnviar(VL) + 
                    preparar_FloatParaEnviar(VR);
        
        bt_env.enviar( sms );
    }
}
