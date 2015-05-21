/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Componentes.NXT.Robot;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author diego_juliao
 */
public class GestionLabels 
{
    private static final String ruta_correcto25x25 = "./src/Media/Img/cargado25x25.png";
    private static final String ruta_incorrecto25x25 = "./src/Media/Img/sincargar25x25.png";
    private static final String ruta_esperando25x25 = "./src/Media/Img/connecting25x25.gif"; 
    private static final String ruta_baseDirecciones = "./src/Media/Img/Direcciones/";
    private static final String formatoImagen = ".png";
    
    
    public static void CambiarLabel_esperando25x25(JLabel l)
    {
        l.setIcon( new ImageIcon( ruta_esperando25x25 ) );
    }
    
    public static void CambiarLabel_correcto25x25(JLabel l)
    {
        l.setIcon( new ImageIcon( ruta_correcto25x25 ) );
    }
    
    public static void CambiarLabel_incorrecto25x25(JLabel l)
    {
        l.setIcon( new ImageIcon( ruta_incorrecto25x25 ) );
    }
    
    public static void CambiarLabel_siguietenRotacion(JLabel l,int dir)
    {
        if(dir == Robot.noroeste)
            dir = Robot.norte;
        else
            dir++;
        
        
        String ruta = ruta_baseDirecciones+dir+formatoImagen;
        System.out.println( ruta );
        l.setIcon( new ImageIcon( ruta ) );
    }
    
}
