package gkinexml;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * Define um ponto desenhado no painel - a representacao grafica de uma articulacao 
 * Refere-se apenas ao desenho 
 * @author AntonioSousa
 */
enum Forma { Circular, QuadRedondo, Quadrada; }  // define o formato do nó

public class JuntaStick {

    public static final int raioPadrao = 10;
    public static final Color corPadrao = Color.BLUE;
    
    private JuntaSkeleton juntaSK;
    private static int ultNum = 0;
    private final int num;
    private final String id;     
    private Point     p;
    private int    raio;
    private Color  cor;
    private Forma  forma;
    private boolean   selecionado = false;
    private Rectangle borda = new Rectangle();

    /**
    * Cria uma nova junta.
    * 
    * @param p
    * @param r
    * @param color
    * @param kind
     */
    public JuntaStick(String id, int numId, JuntaSkeleton js) {
        this.id    = id;  
        this.num = numId;
        this.juntaSK = js;
        this.p     =  new Point((int)js.getX(), (int)js.getY());
        this.raio  = JuntaStick.raioPadrao;
        this.cor   = JuntaStick.corPadrao;
        this.forma = Forma.Circular;
        setBorda(borda);             
    }

    public JuntaStick(String id, int numId, JuntaSkeleton js, int r, Color color, Forma formato) {
        this.id    = id; 
        this.num = numId;
        this.juntaSK = js;
        this.p     = new Point((int)js.getX(), (int)js.getY());
        this.raio  = r;
        this.cor   = color;
        this.forma = formato;
        setBorda(borda);
    }
    
    public String getId() {
       return (this.id);
    }
    
    public int getNum() {
        return (++ultNum);
    }
    
    /**
     * Calcula a borda retangular da junta.
     */
    void setBorda(Rectangle b) {
        b.setBounds(p.x - raio, p.y - raio, 2 * raio, 2 * raio);
    }

    /**
     * Retorna a borda da junta.
     */
    public Rectangle getBorda() {
        return (this.borda);
    }
    
    public void setRaio(int r) {
        this.raio = r;
        setBorda(borda);
    }
    
    public int getRaio() {
        return (this.raio);
    }
    
    public void setForma(Forma f) {
        this.forma = f;
    }
    
    public void setCor(Color c) {
        this.cor = c;
    }
    
    public Color getCor() {
        return (this.cor);
    }
    
    /**
     * Desenha a junta.
     * @param g
     */
    public void desenha(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5)); g2.setColor(cor); 
        g2.setColor(this.cor);
        
        int fontSize = 20;
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));                 

        if (this.forma == Forma.Circular) {
            g.fillOval(borda.x, borda.y, borda.width, borda.height);            
        } else if (this.forma == Forma.QuadRedondo) {
            g.fillRoundRect(borda.x, borda.y, borda.width, borda.height, raio, raio);
        } else if (this.forma == Forma.Quadrada) {
            g.fillRect(borda.x, borda.y, borda.width, borda.height);
        }
        
        g.setColor(Color.red);
        g.drawString(String.valueOf(this.num), borda.x, borda.y);
        
        if (selecionado) {
            g.setColor(Color.darkGray);
            g.drawRect(borda.x, borda.y, borda.width, borda.height);
        }
    }

    /**
     * Retorna a localizacao da junta.
     * @return 
     */
    public Point getCoord() {
        return p;
    }

    /**
     * Retorna a junta do skeleton relacionada com a junta do avatar.
     * @return 
     */
    public JuntaSkeleton getJuntaSkeleton() {
        return (this.juntaSK);
    }
    
    /**
     * Retorna true se a junta contem p.
     * @param p
     * @return 
     */
    public boolean contem(Point p) {
        return borda.contains(p);
    }

    /**
     * Retorna true se a junta esta selecionada.
     * @return 
     */
    public boolean estaSelecionado() {
        return selecionado;
    }

    /**
     * Marca a junta como selecionada.
     * @param selecionado
     */
    public void seleciona(boolean selecionado) {
        this.selecionado = selecionado;
    }

}