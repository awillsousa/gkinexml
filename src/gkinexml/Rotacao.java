
package gkinexml;

/**
 * Define uma rotacao a ser feita em torno de uma junta
 * @author anton_000
 */
enum SentidoRotacao { Horario, Antihorario }; 

public class Rotacao {
    private SentidoRotacao sentido;          // min_rot
    private Eixo eixo;              // eixo de rotacao
    private float angulo;           // valor do angulo de rotação
    
    public Rotacao(SentidoRotacao sentido, Eixo eixo, float angulo) {
        this.sentido = sentido;
        this.eixo    = eixo;
        this.angulo  = angulo;
    }
    
    public void setSentido (SentidoRotacao tr) {
        this.sentido = tr;
    }
    
    public SentidoRotacao getSentido() {
        return (this.sentido);
    }
    
    public void setAngulo(float a) {
        this.angulo = a;
    }
    
    public float getAngulo() {
        return (this.angulo);
    }
    
    public Eixo getEixoRotacao() {
        return (this.eixo);
    }
    
}
