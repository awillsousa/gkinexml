
package gkinexml;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.vecmath.Point3d;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/** 
 *
 * @author anton_000
 */
public final class AvatarStick extends Avatar{
    Skeleton sk;
    ArrayList<JuntaStick>         juntas = new ArrayList<JuntaStick>();
    ArrayList<ConexaoStick>     conexoes = new ArrayList<ConexaoStick>();
    ArrayList<JuntaStick>   selecionados = new ArrayList<JuntaStick>();
    HashMap<String, Integer>   numeracao = new HashMap<String, Integer>();
   // ArrayList<Relacao>          relacoes = new ArrayList<Relacao>();
    
    public AvatarStick(Skeleton sk) {
        // Relaciona os identificadores com a numeracao
        numeracao.put(Skeleton.CABECA, NUM_CABECA);
        numeracao.put(Skeleton.BASE_CABECA, NUM_BASE_CABECA);        
        numeracao.put(Skeleton.OMBRO_ESQ, NUM_OMBRO_ESQ);
        numeracao.put(Skeleton.COTOV_ESQ, NUM_COTOV_ESQ);
        numeracao.put(Skeleton.PULSO_ESQ, NUM_PULSO_ESQ);
        numeracao.put(Skeleton.MAO_ESQ, NUM_MAO_ESQ);
        numeracao.put(Skeleton.OMBRO_DIR, NUM_OMBRO_DIR);
        numeracao.put(Skeleton.COTOV_DIR, NUM_COTOV_DIR);
        numeracao.put(Skeleton.PULSO_DIR, NUM_PULSO_DIR);
        numeracao.put(Skeleton.MAO_DIR, NUM_MAO_DIR);
        numeracao.put(Skeleton.QUADRIL_CENTRO, NUM_QUADRIL_CENTRO);
        numeracao.put(Skeleton.QUADRIL_ESQ, NUM_QUADRIL_ESQ);
        numeracao.put(Skeleton.JOELHO_ESQ, NUM_JOELHO_ESQ);
        numeracao.put(Skeleton.TORNOZ_ESQ, NUM_TORNOZ_ESQ);
        numeracao.put(Skeleton.PE_ESQ, NUM_PE_ESQ);
        numeracao.put(Skeleton.QUADRIL_DIR, NUM_QUADRIL_DIR);
        numeracao.put(Skeleton.JOELHO_DIR, NUM_JOELHO_DIR);
        numeracao.put(Skeleton.TORNOZ_DIR, NUM_TORNOZ_DIR);
        numeracao.put(Skeleton.PE_DIR, NUM_PE_DIR);
        
        // Adiciona as juntas
        for (Map.Entry<String, JuntaSkeleton> junta : sk.juntas.entrySet()) { 
            String id = junta.getKey();
            JuntaSkeleton js = junta.getValue();
            Point p = new Point((int)js.getX(), (int)js.getY());
            int numId = numeracao.get(id);
            
            if (id.equals("cabeca")) {
                juntas.add(new JuntaStick(id, numId, js, 20, Color.MAGENTA, Forma.QuadRedondo));
            } else if (id.equals("mao-esq")||id.equals("mao-dir")) {
                juntas.add(new JuntaStick(id, numId, js, 8, Color.RED, Forma.Circular));
            } else if (id.equals("pe-esq")||id.equals("pe-dir")) {
                juntas.add(new JuntaStick(id, numId, js, 8, Color.RED, Forma.Circular));
            }else {
                juntas.add(new JuntaStick(id, numId, js));
            }
        }
        
        // Adiciona as conexoes
        for (Conexao c : sk.conexoes) {
            JuntaStick js1 = this.getJunta(c.getJunta1().getId());
            JuntaStick js2 = this.getJunta(c.getJunta2().getId());            
            conexoes.add(new ConexaoStick(js1, js2));
        }
        
        this.sk = sk;
    }
    
    
    public JuntaStick getJunta(String id) {
        for (JuntaStick n : juntas) {
            if (n.getId().equals(id)) {                
                return (n);
            }
        }
        return (null);
    }
      
    /**
     * Retorna a lista de juntas selecionadas.     
     * @return 
     */
    public ArrayList<JuntaStick> getSelecionados() {
        return (selecionados);
    }

    /**
     * Deseleciona todas as juntas selecionadas.    
     */
    public void selecionaNenhum() {
        if (!selecionados.isEmpty()) {                
            for (JuntaStick n : juntas) {
                n.seleciona(false);
            }
            selecionados.clear();
        }
    }

    /**
     * Seleciona uma única junta
     * @param p
     * @return 
     */
    public boolean selecionaUm(Point p) {
        for (JuntaStick n : juntas) {
            if (n.contem(p)) {
                if (!n.estaSelecionado()) {
                    selecionaNenhum();
                    n.seleciona(true);
                    selecionados.add(n);
                }
                return true;
            }
        }
        return false;
    }

     /**
     * Seleciona uma junta
     * @param p
     * @return 
     */
    public boolean seleciona(Point p) {
        for (JuntaStick n : juntas) {
            if (n.contem(p)) {
                if (!n.estaSelecionado()) {                    
                    n.seleciona(true);
                    selecionados.add(n);
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Seleciona as juntas que estejam dentro do retangulo r
     * @param r
     */
    public void selecionaRect(Rectangle r) {
        for (JuntaStick n : juntas) {
            if (r.contains(n.getCoord()));
            n.seleciona(true);
        }
    }

    /**
     * Alterna o estado de selecao da junta que contem o ponto p.
     * @param p
     */
    public void alternaSelecao(Point p) {
        for (JuntaStick n : juntas) {
            if (n.contem(p)) {
                n.seleciona(!n.estaSelecionado());
            }
        }
    }

    /**
     * Atualiza a posição de cada junta de d 
     * @param d
     */
    public void updatePosicao(Point d) {
        for (JuntaStick n : juntas) {
            if (n.estaSelecionado()) {
                n.getCoord().x += d.x;
                n.getCoord().y += d.y;
                n.setBorda(n.getBorda());
            }
        }
    }

    /**
     * Atualiza o tamanho do raio de toda as juntas.
     * @param r
     */
    public void updateRaio(int r) {
        for (JuntaStick n : juntas) {
            if (n.estaSelecionado()) {
                n.setRaio(r);                
            }
        }
    }

    /**
     * Atualiza a cor de cada junta.
     * @param cor
     */
    public void updateCor(Color cor) {
        for (JuntaStick n : juntas) {
            if (n.estaSelecionado()) {
                n.setCor(cor);
            }
        }
    }

    /**
     * Atualiza a forma de desenho de cada uma das juntas.
     */
    public void updateForma(Forma f) {
        for (JuntaStick n : juntas) {
            if (n.estaSelecionado()) {
                n.setForma(f);
            }
        }
    }

    /**
     * Retorna o skeleton relacionado ao avatar.
     */
    public Skeleton getSkeleton () {
        return (this.sk);
    }
    
    @Override
    public void monta(Skeleton sk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void desenha(Graphics g) {  
         for (ConexaoStick cs : conexoes) {
            cs.desenha(g);
        }
        for (JuntaStick js : juntas) {
            js.desenha(g);
        }       
    }
    
    public void desenha3D(Graphics g) {  
         for (ConexaoStick cs : conexoes) {
            cs.desenha(g);
        }
        for (JuntaStick js : juntas) {
            js.desenha(g);
        }       
    }
        
    void drawPoint(Graphics2D g2, Vector3D v) {
    g2.drawLine(0, 0,
            (int) (AvatarPanel.planeX.getOffset(v)),
            (int) (AvatarPanel.planeY.getOffset(v)));
    }
    
    /* Reseta o avatar para a postura inicial */
    public void resetaPostura() {
        juntas.clear();
        // Adiciona as juntas
        for (Map.Entry<String, JuntaSkeleton> junta : sk.juntas.entrySet()) { 
            String id = junta.getKey();
            JuntaSkeleton js = junta.getValue();
            Point p = new Point((int)js.getX(), (int)js.getY());
            int numId = numeracao.get(id);
            
            if (id.equals("cabeca")) {
                juntas.add(new JuntaStick(id, numId, js, 20, Color.MAGENTA, Forma.QuadRedondo));
            } else if (id.equals("mao-esq")||id.equals("mao-dir")) {
                juntas.add(new JuntaStick(id, numId, js, 8, Color.RED, Forma.Circular));
            } else if (id.equals("pe-esq")||id.equals("pe-dir")) {
                juntas.add(new JuntaStick(id, numId, js, 8, Color.RED, Forma.Circular));
            }else {
                juntas.add(new JuntaStick(id, numId, js));
            }
        }
        
        conexoes.clear();
        // Adiciona as conexoes
        for (Conexao c : sk.conexoes) {
            JuntaStick js1 = this.getJunta(c.getJunta1().getId());
            JuntaStick js2 = this.getJunta(c.getJunta2().getId());            
            conexoes.add(new ConexaoStick(js1, js2));
        }
    }
}
