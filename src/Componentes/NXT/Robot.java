/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.NXT;

import Componentes.NXT.conexion.Bluethoot_conector;
import Componentes.NXT.conexion.Gestion_MensajesNXT;
import Networking.ConexionACO;
import javax.swing.JLabel;
import sma.index;
/**
 *
 * @author diego_juliao
 */
public class Robot extends dispositivo
{
    private Bluethoot_conector bl_con;
    private int robotID;
    
    private ConexionACO conect_ACO;

    private int agentesCalibrados;
    
    public static final int norte=0;
    public static final int noreste=1;
    public static final int este=2;
    public static final int sureste=3;   
    public static final int sur=4;
    public static final int suroeste=5;
    public static final int oeste=6;
    public static final int noroeste=7;
       
    private int horientacion;
    private JLabel Jlabel_horientacion;
     
    public Robot(dispositivo dis, int robotID, ConexionACO conect_ACO, JLabel labelHorientacion) 
    {
        super(dis.nombre, dis.direccion);
        
        this.Jlabel_horientacion = labelHorientacion;
        
        agentesCalibrados = 0;
        horientacion = norte;
        
        bl_con = new Bluethoot_conector()
        {
            @Override
            public void analizadorDeSMS_BT(String sms) 
            {
                String encabezado = sms.split(Gestion_MensajesNXT.Separador )[0];
                String cuerpo = sms.split(Gestion_MensajesNXT.Separador )[1];
                
                if( encabezado.equals(Gestion_MensajesNXT.Calibrar_SensorOptico ) )
                {   
                    /*Encabezado_MensajesNXT.Calibrar_SensorOptico + Encabezado_MensajesNXT.Separador+
			  (alto?1:0) + (bajo?1:0)*/
                    int alto = cuerpo.charAt(0) - 48 ;
                    int bajo = cuerpo.charAt(1) - 48 ; 
                            
                    index.p.add(robotID, alto == 1, bajo == 1);
                }
            }
        };
        
        this.robotID = robotID;
        this.conect_ACO = conect_ACO;
    }
    
    public int gethorientacion()
    {
        return horientacion;
    }

    public void sethorientacion(int horientacion) 
    {
        if(horientacion >= 8)
            horientacion = norte;
        
        this.horientacion = horientacion;
        Tools.GestionLabels.CambiarLabel_siguietenRotacion(Jlabel_horientacion, horientacion);
    }
    
    public void SEND_siguientePaso()
    {
        conect_ACO.enviar_SiguientePaso(robotID);
    }
    
    public void RECIBE_siguientePaso(int horientacion)//mandar al robot a ejecutar lo que llegó
    {
        
    }
    
    @Override
    public void run()
    {
        for(;;)
        {
            SEND_siguientePaso();
            this.suspend();
        }
    }
    
    public void recibirMovimiento(int mirada, float distancia)    
    {
        bl_con.enviarSiguientePaso( Tools.Giros.cuantosGradosGiraryHaciaDonde(this.horientacion, mirada) , distancia);
    }
    
    public boolean conectar()
    {
        bl_con.connect(nombre, direccion, robotID);
        
        if( isConnected() )
            bl_con.enviarRobotID(robotID);
        
        return isConnected();
    }
    
    public void desconectar()
    {        
        if(bl_con.isConectado())
            bl_con.cerrarConexion(true);
    }

    public boolean isConnected() {
        return bl_con.isConectado();
    }
    
    public int getIndexRobot() {
        return robotID;
    }
        
    public void calibrarSensores()
    {
        bl_con.calibrar_SensorOptico();
    }
    
    //movimiento simple
    public void adelante()
    {
        bl_con.enviar_adelante();
    }
    
    public void atras()
    {
        bl_con.enviar_atras();
    }
    
    public void izq()
    {
        bl_con.enviar_izq();
    }
    
    public void der()
    {
        bl_con.enviar_der();
    }
    
    public void parar()
    {
        bl_con.enviar_parar();
    }

}
