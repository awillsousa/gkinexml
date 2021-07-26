
package gkinexml;

import javax.vecmath.Point3d;

/**
 * 
 * @author anton_000
 */ 
enum FormatoMao { Punho, Serpente, Garça, Pantera, Tigre };

public class MaoSkeleton extends JuntaSkeleton {
    private FormatoMao formato;
    
    public MaoSkeleton(String id, String desc, String tag, Point3d coord3D) {
        super(id, desc, tag, coord3D);
        this.formato = FormatoMao.Punho;
    }
    
    public MaoSkeleton(String id, String desc, String tag, FormatoMao form, int x, int y, int z) {
        super(id, desc, tag, x, y, z);
        this.formato = form;
    }
    
    
    
    public void setFormato(FormatoMao formato) {
        this.formato = formato;
    }
    
    public FormatoMao getFormato() {
        return (this.formato);
    }
    
}
