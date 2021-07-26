
package gkinexml;

import java.util.HashMap;
import javax.vecmath.Point3d;

/**
 * Define um avatar abstrato
 * @author AntonioSousa
 */

public abstract class Avatar {
    
    public static final int NUM_CABECA      = 15;  
    public static final int NUM_BASE_CABECA = 11;
    public static final int NUM_OMBRO_DIR   = 4; 
    public static final int NUM_COTOV_DIR   = 5;
    public static final int NUM_PULSO_DIR   = 6;
    public static final int NUM_MAO_DIR     = 21;     
    public static final int NUM_OMBRO_ESQ   = 12;
    public static final int NUM_COTOV_ESQ   = 13;
    public static final int NUM_PULSO_ESQ   = 14;        
    public static final int NUM_MAO_ESQ     = 20;    
    public static final int NUM_QUADRIL_CENTRO = 10;
    public static final int NUM_QUADRIL_DIR = 3;    
    public static final int NUM_JOELHO_DIR  = 2;
    public static final int NUM_TORNOZ_DIR  = 1;
    public static final int NUM_PE_DIR      = 22;
    public static final int NUM_QUADRIL_ESQ = 9;    
    public static final int NUM_TORNOZ_ESQ  = 7;
    public static final int NUM_JOELHO_ESQ  = 8;
    public static final int NUM_PE_ESQ      = 23;
    
    
    
    public Avatar() {
        
    }

    public Avatar(Skeleton sk) {
        
    }
    
    public abstract void monta(Skeleton sk);
    
    /*
    public abstract void desenhaCabeca();

    public abstract void desenhaDorso();

    public abstract void desenhaBracoEsq();

    public abstract void desenhaAntebracoEsq();

    public abstract void desenhaBracoDir();

    public abstract void desenhaAntebracoDir();

    public abstract void desenhaCanelaEsq();

    public abstract void desenhaCoxaEsq();

    public abstract void desenhaCeanelaDir();

    public abstract void desenhaCoxaDir();
    
    public abstract void draw();
    */
    
}