/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.Pizarra;

import Componentes.NXT.Robot;
import java.util.ArrayList;
import sma.index;

/**
 *
 * @author diego_juliao
 */
public class Pizarra 
{
    private ArrayList< registro > lista;
    
    private Robot R1,R2,R3;
    
    private int robotsCalibrados;

    public Pizarra(Robot R1, Robot R2, Robot R3)
    {
        lista = new ArrayList<>();
        this.R1=R1;
        this.R2=R2;
        this.R3=R3;
        
        robotsCalibrados = 0;
    }
    
    public void add( int robotID )
    {
        lista.add( new registro(robotID) );
    }
    
    public void add( int robotID, boolean alto, boolean bajo )
    {
        lista.add( new registro(robotID, new registroCuerpo(alto, bajo) ) );
        
        if( (alto == true && bajo == false) && (robotID == 1 || robotID == 2) ) //cuando el robot 1 y 2 ya han calibrado sus sensores el robot 3 lo hará
        {
            robotsCalibrados++;
            
            if(robotsCalibrados == 2)
            {
                R1.calibrarSensores();
                R2.calibrarSensores();
                R3.calibrarSensores();
            }
        }
        
        if( (alto == true && bajo == false) && (robotID == 3) )
        {
            R1.calibrarSensores();
            R2.calibrarSensores();
            R3.calibrarSensores();
            
            robotsCalibrados++;
        }
        
        if( robotsCalibrados == 3 ) //calibracion lista.
        {
            Tools.GestionLabels.CambiarLabel_correcto25x25( index.jLabel_calibrarSensoresOpticos );
        }
        
    }
    
}