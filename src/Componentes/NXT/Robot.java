/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.NXT;

import Componentes.NXT.conexion.Bluethoot_conector;
import Componentes.NXT.conexion.Gestion_MensajesNXT;
import Networking.ConexionACO;
import Networking.ConexionVisionArtificial;
import java.awt.Point;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sma.index;
/**
 *
 * @author diego_juliao
 */
public class Robot extends dispositivo
{
    public static final float VelocidadMaxima = (float) 33.333;
    public static final float VelocidadInicial = (float) 15.0;
    public static float velocidad;

    private Point posicionDigital;
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
    private JTextField sonic,lumin;
    private ConexionVisionArtificial conect_VA;
    
    private boolean calibratedLigth;
    
    private int pasos;
    
    private Point randomP_DEBUG;
    private Random rng;
    
    public Robot(dispositivo dis, int robotID, ConexionVisionArtificial conect_VA,
                 JLabel label_Horientacion, JTextField sonic, JTextField lumin) 
    {
        super(dis.nombre, dis.direccion);
        
        pasos = 0;
        velocidad = VelocidadInicial;
        this.Jlabel_horientacion = label_Horientacion;
        this.conect_VA = conect_VA;
        
        posicionDigital = new Point(-1, -1);
        
        agentesCalibrados = 0;
        horientacion = norte;
        
        calibratedLigth = false;
        
        bl_con = new Bluethoot_conector()
        {
            @Override
            public void analizadorDeSMS_BT(String sms) 
            {
                String encabezado = null;
                String cuerpo = null;
                
                System.out.println(sms);
                
                if(sms.contains(Gestion_MensajesNXT.Separador))
                {
                    encabezado = sms.split(Gestion_MensajesNXT.Separador )[0];
                    cuerpo = sms.split(Gestion_MensajesNXT.Separador )[1];
                }
                
                if( sms.equals( Gestion_MensajesNXT.MovimientoTERMINADO ) )
                {
                    continuarHilo();
                }
                else if( sms.equals( Gestion_MensajesNXT.CorreccionTERMINADO ) )
                {
                    conect_VA.correccionTrayectoriaTerminada(robotID);
                    bl_con.enviarVelocidad( velocidad ); //al terminar la corrección de trayectoria el robot establece la velocidad que tenía
                    continuarHilo();
                }
                else if( encabezado.equals(Gestion_MensajesNXT.Calibrar_SensorOptico ) )
                {   
                    /*Encabezado_MensajesNXT.Calibrar_SensorOptico + Encabezado_MensajesNXT.Separador+
			  (alto?1:0) + (bajo?1:0)*/
                    int alto = cuerpo.charAt(0) - 48 ;
                    int bajo = cuerpo.charAt(1) - 48 ; 
                            
                    index.p.add(robotID, alto == 1, bajo == 1);
                    
                    calibratedLigth = alto == 1 && bajo == 1;
                }
                else if( encabezado.equals(Gestion_MensajesNXT.Sensor ) )
                {
                    //siempre se envía de primero el Sonico y luego el lumínico
                    sonic.setText(cuerpo.split( Gestion_MensajesNXT.Separador2 )[0] );
                    lumin.setText( cuerpo.split( Gestion_MensajesNXT.Separador2 )[1] );
                    
                    System.out.println(cuerpo.split( Gestion_MensajesNXT.Separador2 )[1]);
                }
            }
        };
        
        this.robotID = robotID;
        
        rng = new Random();
        randomP_DEBUG = new Point(5,5);
    }

    public void setConect_ACO(ConexionACO conect_ACO) {
        this.conect_ACO = conect_ACO;
    }

    public boolean isCalibratedLigth() {
        return calibratedLigth;
    }
    
    public int gethorientacion()
    {
        return horientacion;
    }

    public void sethorientacion(int horientacion) 
    {
        this.horientacion = horientacion;
        Tools.GestionLabels.CambiarLabel_Rotacion(Jlabel_horientacion, horientacion);
    }
    
    public void setSiguiente_horientacion()
    {
        horientacion++;
        if(horientacion >= 8)
            horientacion = norte;
        
        sethorientacion(horientacion);
    }
    
    public void SEND_siguientePaso()
    {
        conect_ACO.enviar_SiguientePaso(robotID);
    }
    
    public void corregirTrayectoriaNXT( float teta, double distanciaDesface, float tetaDesface )
    {
        bl_con.enviarVelocidad( VelocidadInicial ); //velocidad para que el robot haga su corrección de trayectoria correctamente
        bl_con.corregirTrayectoria( teta, distanciaDesface, tetaDesface );
    }
    
    public void RECIBE_siguientePaso(int horientacion)//mandar al robot a ejecutar lo que llegó
    {
        
    }
    
    @Override
    public void run()
    {
        
        //se piede la corrección de trayectoria por primera vez, para corregir el error humano de colocar el robot
       /* corregirTrayectoria();
        this.suspend();*/
        
        Random rng = new Random();
        
        for(;;)
        {
            generarPuntoAleatorio_debug(sur, robotID, norte);
            corregirTrayectoria( randomP_DEBUG );
            this.suspend();
        }         
                
        /*for(;;)
        {
            if( pasos++ % 5 == 0 )
            {
                corregirTrayectoria( posicionDigital );
                this.suspend();
            }
            else
            {
                SEND_siguientePaso();
                this.suspend();
            }
        }*/
    }
    
    private void generarPuntoAleatorio_debug(int max, int limiteInf, int limiteSup)
    {
        /*randomP_DEBUG = new Point( limiteInf + rng.nextInt( max - limiteSup ), 
                                   limiteInf + rng.nextInt( max - limiteSup ));*/
        
        Point vect[] = { new Point(1,2),
                         new Point(9,2),
                         new Point(1,8),
                         new Point(9,8),
                         new Point(5,8),
                         new Point(5,2)};
        
        randomP_DEBUG = new Point( vect[ rng.nextInt( vect.length ) ] );
    }
    
    private void corregirTrayectoria()
    {
        conect_VA.solicitarCorreccionTrayectoria(robotID, horientacion);
    }
    
    private void corregirTrayectoria(Point pos)
    {
        conect_VA.solicitarCorreccionTrayectoria(robotID, horientacion, pos);
    }
    
    public void recibirMovimiento(int mirada, float distancia, Point posicionDigital)    
    {
        bl_con.enviarSiguientePaso( Tools.Giros.cuantosGradosGiraryHaciaDonde(this.horientacion, mirada) , distancia*100);
        horientacion = mirada;
        this.posicionDigital = posicionDigital;
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

    public boolean isConnected() 
    {
        if(index.DEBUG)
            return true;
        
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

    public void setVelocidad(float v) 
    {    
        velocidad = v;
        bl_con.enviarVelocidad(v);
    }

}
