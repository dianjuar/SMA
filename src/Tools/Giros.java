/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author diego_juliao
 */
public class Giros 
{
    private static int pasosParaLlegarA(int mirada, int miradaNueva, boolean horario)
    {
        int cont = 0;
        
        if(horario)
            for (int i = 0; i < 8; i++) 
            {
                if(mirada == miradaNueva)
                    break;

                mirada++;
                cont++;
                
                if(mirada == 8)
                    mirada=0;
            }
        else
            for (int i = 8; i >= 0; i--) 
            {
                if(mirada == miradaNueva)
                    break;

                mirada--;
                cont++;
                
                if(mirada == -1)
                    mirada = 7;
            }
        
        return cont;
    }
    
    public static int cuantosGradosGiraryHaciaDonde(int mirada, int miradaNueva)
    {
        int grados = 0;
        
        if(mirada != miradaNueva)
        {
            int pasosH = pasosParaLlegarA(mirada, miradaNueva, true);
            int pasosAH = pasosParaLlegarA(mirada, miradaNueva, false);
            
            int pasos = pasosH < pasosAH ? pasosH:pasosAH;
  
            grados = pasos*45  * pasosH < pasosAH ? 1 : -1;
            
        }
        
        return grados;
    }
}
