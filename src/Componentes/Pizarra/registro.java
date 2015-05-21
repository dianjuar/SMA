/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes.Pizarra;

/**
 *
 * @author diego_juliao
 */
public class registro
{
    private int RobotID;
    private registroCuerpo cuerpo;
    
    private final int lectores = 3;
    private boolean robotLeyo[];

    private void constructor(int RobotID)
    {
        robotLeyo = new boolean[ lectores ];
        
        for (int i = 0; i < robotLeyo.length; i++)
            robotLeyo[i]=false;
        
        robotLeyo[RobotID -1]=true;
    }
    
    public registro(int RobotID) 
    {
        constructor(RobotID);
        
        this.RobotID = RobotID;
        cuerpo = null;
    }

    public registro(int RobotID, registroCuerpo cuerpo) 
    {
        constructor(RobotID);   
        
        this.RobotID = RobotID;
        this.cuerpo = cuerpo;
    }    

    public int getRobotID() {
        return RobotID;
    }

    public registroCuerpo getCuerpo() {
        return cuerpo;
    }
    
    public boolean isRegistroLeido()
    {
        for (int i = 0; i < robotLeyo.length; i++)            
            if(!robotLeyo[i])
                return false;
        
        return true;
    }
    
    public void leido(int RobotID)
    {
        robotLeyo[RobotID-1] = true;
    }
    
    public boolean yaLoLeyo(int RobotID)
    {
        return robotLeyo[RobotID-1];
    }
}