package gkinexml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.vecmath.Point3d;

/**
 * Conexao entre um par de nós (como uma aresta de um grafo)
 * @author AntonioSousa
 * 
 */
public class Conexao {

    public static final int PESCOCO = 0;
    public static final int DORSO = 1;
    public static final int OMBRO_ESQ = 2;
    public static final int OMBRO_DIR = 3;
    public static final int BRACO_DIR = 4;
    public static final int BRACO_ESQ = 5;
    public static final int ANTEBRACO_ESQ = 6;
    public static final int ANTEBRACO_DIR = 7;
    public static final int PALMA_DIR = 8;
    public static final int PALMA_ESQ = 9;
    public static final int QUADRIL_DIR = 10;
    public static final int QUADRIL_ESQ = 11;
    public static final int COXA_DIR = 12;
    public static final int COXA_ESQ = 13;
    public static final int CANELA_ESQ = 14;
    public static final int CANELA_DIR = 15;
    public static final int PLANTA_DIR = 16;
    public static final int PLANTA_ESQ = 17;
    
    private int id;
    private JuntaSkeleton js1;
    private JuntaSkeleton js2;    
    private final double tamanho;

    public Conexao(int id, JuntaSkeleton n1, JuntaSkeleton n2) {
        this.id  = id;
        this.js1 = n1;
        this.js2 = n2;
        this.tamanho = js1.getCoordenada().distance(js2.getCoordenada());
        
        // Somente pode criar conexoes entre vizinhos
        n1.addVizinho(n2);
    }

    public double getTamConexao() {
        return (this.tamanho);
    }
    
    public JuntaSkeleton getJunta1() {
        return (js1);
    }
    
    public JuntaSkeleton getJunta2() {
        return (js2);
    }
    
/*    public void draw(Graphics g) {
        Point p1 = js1.getLocation();
        Point p2 = js2.getLocation();
        g.setColor(Color.darkGray);            
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }*/
}