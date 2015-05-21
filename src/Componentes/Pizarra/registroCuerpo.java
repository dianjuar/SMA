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
public class registroCuerpo {
    
    private int alto, bajo;

    public registroCuerpo(boolean alto, boolean bajo)
    {
        this.alto = alto?1:0;
        this.bajo = bajo?1:0;
    }

    public int getAlto() {
        return alto;
    }

    public int getBajo() {
        return bajo;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setBajo(int bajo) {
        this.bajo = bajo;
    }
    
}