/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.base;

import java.awt.Point;

/**
 *
 * @author diego_juliao
 */
public class GestionDeMensajes 
{
    public static final String Msj_divisor = "->";
        public static final String Msj_divisor_2 = "-";   
        
    public static final String Msj_cerrar = "close";
    
    public static final String Msj_conectado = "connect"; 
    
    public static final String Msj_PInicio_SMAtoACO = "iniSMA2ACO";
        public static final String Msj_PInicio_SMAtoACO_HowMany = "HowMany";
        public static final String Msj_PInicio_SMAtoACO_VelMax = "VelMax";
        
    public static final String Msj_nextStep = "nextS"; 
    
    
    public static final String Msj_SMAtoMDV_solicitudTrayectoria = "CorrectMe";
    
    public static String SolicitarCorreccionTrayectoria(int RobotID, int Posicion, Point posicionRobot)
    {
        String RobotPoint = posicionRobot == null ? "-1"+Msj_divisor_2+"-1" 
                            :
                            posicionRobot.x + Msj_divisor_2 + posicionRobot.y;
        
        String msj = Msj_SMAtoMDV_solicitudTrayectoria + Msj_divisor + 
                     RobotID + Msj_divisor_2 +
                     Posicion + Msj_divisor_2 + 
                     RobotPoint;
        
        return msj;
    }

       



    
    
}