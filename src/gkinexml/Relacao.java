
package gkinexml;

import java.util.ArrayList;

/**
 *
 * @author anton_000
 */

enum TipoAlinhamento {Paralelo, Inclinado, MesmaLinha };

public class Relacao {
    private ArrayList<JuntaSkeleton> elementos = new ArrayList<>();
    private TipoRelacao tipo;
    private JuntaSkeleton juntaSimetria;
    private JuntaSkeleton juntaAssimetria;
    private float valorAltura;
    private TipoAlinhamento alinhamento;
    
    public Relacao(TipoRelacao tipo, ArrayList<JuntaSkeleton> lista) {
        this.tipo = tipo;
        this.elementos = lista;
    } 
    
    public TipoRelacao getTipo() {
        return (this.tipo);
    }
    
    public ArrayList<JuntaSkeleton> getElementos() {
        return (this.elementos);
    }
    
    public boolean addElemento(JuntaSkeleton junta) {
        return (elementos.add(junta));
        
    }
    
    public boolean removeElemento(JuntaSkeleton junta) {
        return (elementos.remove(junta));
    }
    
    public void setAlinhamento(TipoAlinhamento t) {
        this.alinhamento = t;
    }
    
    public void setJuntaSimetria(JuntaSkeleton j) {
        this.juntaSimetria = j;
    }
    
    public void setJuntaAssimetria(JuntaSkeleton j) {
        this.juntaAssimetria = j;
    }
    
    public void setValorAltura(float v) {
        this.valorAltura = v;
    }
    
    public TipoAlinhamento getAlinhamento() {
        return (this.alinhamento);
    }
    
    public JuntaSkeleton getJuntaSimetria() {
        return (this.juntaSimetria);
    }
    
    public JuntaSkeleton getJuntaAssimetria() {
        return (this.juntaAssimetria);
    }
    
    public float getValorAltura() {
        return (this.valorAltura);
    }
    
    
}
