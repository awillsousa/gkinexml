package gkinexml;

import java.util.ArrayList;
import javax.vecmath.Point3d;

/**
 * Essa classe refere-se aos pontos que terão os dados inseridos no arquivo XML 
 * gerado. 
 * @author AntonioSousa
 */
enum Eixo {X, Y, Z};
enum TipoRelacao {Simetria, Assimetria, Altura, Alinhamento };

class JuntaSkeleton {     
    private final String id;
    private final String descricao;        
    private Point3d coordenada;    
    private ArrayList<JuntaSkeleton> vizinhos;
    private final String tagXML;
    private Rotacao rotacaoX;
    private Rotacao rotacaoY;
    private Rotacao rotacaoZ;
    
    /**
     * Cria uma nova junta 
     * @param nome Define um nome identificador (preferencialmente único) para a junta
     * @param desc Define uma descricao textual para a junta 
     * @param x Coordenada x
     * @param y Coordenada y
     * @param z Coordenada z
     */    
    public JuntaSkeleton(String id, String desc, String tag, int x, int y, int z) {
        this.id = id;
        this.descricao = desc;          
        this.coordenada = new Point3d(x,y,z);          
        this.vizinhos = new ArrayList<JuntaSkeleton>() {};
        this.tagXML = tag;        
    }
    
    /**
     * Cria uma nova junta 
     * @param nome Define um nome identificador (preferencialmente único) para a junta
     * @param desc Define uma descricao textual para a junta 
     * @param coord3D Coordenada 3D da junta     
     */       
    public JuntaSkeleton(String id, String desc, String tag, Point3d coord3D) {
        this.id = id;        
        this.descricao = desc;          
        this.coordenada = coord3D;
        this.vizinhos = new ArrayList<JuntaSkeleton>() {};
        this.tagXML = tag;
    }
    
    
    /**
     * Verifica se a junta passada é vizinha
     * @param j Junta passada para consulta     
     */
    public boolean ehVizinho(JuntaSkeleton j) {
        for (JuntaSkeleton v : vizinhos) {
            if (v.getId() == j.getId())                 
                return (true);
        }        
        return (false);        
    }
    
    /**
     * Acrescenta uma junta na lista de vizinhanca, exceto se ele já estiver nela
     * @param v Junta vizinha
     */    
    public void addVizinho(JuntaSkeleton v) {
        if (!ehVizinho(v)) {
            vizinhos.add(v);        
        
            // Vizinhanca eh uma relacao de mao-dupla
            v.addVizinho(this);   
        }        
    }
    
    /**
     * Retorna a lista de vizinhos da junta
     * @param 
     */      
    public ArrayList<JuntaSkeleton> getVizinhos() {
        return(this.vizinhos);
    } 
    
    /**
     * Busca um vizinho pelo seu id
     * @param idVizinho 
     */          
    public JuntaSkeleton getVizinho(String idVizinho) {
        for (JuntaSkeleton v : vizinhos) {
            if (v.id == idVizinho)
                return (v);
        }
        
        return (null);
    }
   
    /**
     * Retorna o id da junta
     * @param  
     */     
    public String getId() {
        return (this.id);
    }
            
    /**
     * Retorna a descricao a junta
     * @param  
     */          
    public String getDescricao() {
        return (this.descricao);
    }
    
    /**
     * Retorna a coordenada X da junta
     * @param  
     */      
    public double getX() {
        return (this.coordenada.x);
    }
    
    /**
     * Retorna a coordenada Y da junta
     * @param  
     */     
    public double getY() {
        return (this.coordenada.y);
    }
    
    /**
     * Retorna a coordenada Z da junta
     * @param  
     */         
    public double getZ() {
        return (this.coordenada.z);
    }
    
    /**
     * Retorna a coordenada 3d da junta
     * @param  
     */     
    public Point3d getCoordenada() {
        return (this.coordenada);
    }
    
    /**
     * Configura uma rotacao passada para a junta
     * @param  
     */
    public void setRotacao(SentidoRotacao sentido, Eixo eixo, float angulo) {
        if (eixo == Eixo.X) {
            this.rotacaoX = new Rotacao(sentido, eixo, angulo);
        } else if (eixo == Eixo.Y) {
            this.rotacaoY = new Rotacao(sentido, eixo, angulo);
        } else if (eixo == Eixo.Z) {
            this.rotacaoZ = new Rotacao(sentido, eixo, angulo);
        }
    }
    
     /**
     * Retorna uma rotacao passada para a junta
     * @param  
     */
    public Rotacao getRotacao(Eixo eixo) {
        if (eixo == Eixo.X) {
            return (this.rotacaoX);
        } else if (eixo == Eixo.Y) {
            return (this.rotacaoY);
        } else if (eixo == Eixo.Z) {
            return (this.rotacaoZ);
        }
        
        return (null);
    }
    
    public String getInfo() {
        String info = "";
        
       /* if (this.rotacaoX != null) {
            info += "Rotacao em: " + Eixo.X.toString() + "\n"+ " Min. Rot.: " +  this.rotacaoX.getSentido().toString() + "\n"+ " Ângulo: " + this.rotacaoX.getAngulo() + "\n";
        }
        if (this.rotacaoY != null) {
            info += "Rotacao em: " + Eixo.Y.toString() + "\n"+ " Min. Rot.: " +  this.rotacaoY.getSentido().toString() + "\n"+ " Ângulo: " + this.rotacaoY.getAngulo() + "\n";
        }
        if (this.rotacaoZ != null) {
            info += "Rotacao em: " + Eixo.Z.toString() + "\n"+ " Min. Rot.: " +  this.rotacaoZ.getSentido().toString() + "\n"+ " Ângulo: " + this.rotacaoZ.getAngulo() + "\n";
        }
               */
        info += "<html>";
        if (this.rotacaoX != null) {
            info += geraLinhaInfo(this.rotacaoX);
        }
        if (this.rotacaoY != null) {
            info += geraLinhaInfo(this.rotacaoY);
        }
        if (this.rotacaoZ != null) {
            info += geraLinhaInfo(this.rotacaoZ);
        }
        info += "</html>";
        
        return (info);
    }
    
    private String geraLinhaInfo(Rotacao r) {
        String linha = "<strong>Rotacao em: </strong>" + r.getEixoRotacao().toString() + "<br>";
        linha += "<strong>Min. Rot.: </strong>" +  r.getSentido().toString() + "<br>";
        linha += "<strong>Ângulo: </strong>" + r.getAngulo() + "<br><br>";
        
        return (linha);
    }
}
