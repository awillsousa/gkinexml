
package gkinexml;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Point3d;

/**
 * O skeleton com as coordenadas determinadas para cada junta determinando 
 * o seu posicionamento
 * @author AntonioSousa
 */
public class Skeleton {  
  
    public static final String CABECA      = "cabeca";  
    public static final String BASE_CABECA = "base-cabeca";
    public static final String OMBRO_DIR   = "ombro-dir"; 
    public static final String COTOV_DIR   = "cotov-dir";
    public static final String PULSO_DIR   = "pulso-dir";
    public static final String MAO_DIR     = "mao-dir";     
    public static final String OMBRO_ESQ   = "ombro-esq";
    public static final String COTOV_ESQ   = "cotov-esq";
    public static final String PULSO_ESQ   = "pulso-esq";        
    public static final String MAO_ESQ     = "mao-esq";    
    public static final String QUADRIL_CENTRO = "quadril-centro";
    public static final String QUADRIL_DIR = "quadril-dir";    
    public static final String JOELHO_DIR  = "joelho-dir";
    public static final String TORNOZ_DIR  = "tornoz-dir";
    public static final String PE_DIR      = "pe-dir";
    public static final String QUADRIL_ESQ = "quadril-esq";    
    public static final String TORNOZ_ESQ  = "tornoz-esq";
    public static final String JOELHO_ESQ  = "joelho-esq";
    public static final String PE_ESQ      = "pe-esq";
    public static final String[] nomesPontos = {CABECA, BASE_CABECA, OMBRO_DIR, COTOV_DIR, PULSO_DIR, MAO_DIR, OMBRO_ESQ, COTOV_ESQ, PULSO_ESQ, MAO_ESQ, QUADRIL_CENTRO, QUADRIL_DIR, JOELHO_DIR, TORNOZ_DIR, PE_DIR, QUADRIL_ESQ, JOELHO_ESQ, TORNOZ_ESQ, PE_ESQ };
    
    private HashMap<String, Point3d> posturaInicial;
    private HashMap<String, Point3d> postura;
    public  final HashMap<String, JuntaSkeleton> juntas = new HashMap<String, JuntaSkeleton>();    
    public final ArrayList<Conexao> conexoes = new ArrayList<Conexao>();
    public ArrayList<Relacao>       relacoes = new ArrayList<Relacao>();
          
    
    /**
     * Cria um novo skeleton sem definir uma postura inicial
     * Será utilizada a postura hardcoded.
     * 
     */
    public Skeleton() {   
        // Inicializa os juntas do skeleton que sera utilizado          
        setPosturaInicial();
        inicializaSkeleton(posturaInicial);        
    }      
    
    /**
     * Cria um novo skeleton e usa como postura inicial os pontos passados através do parâmetro postura
     * @param postura
     */
    public Skeleton(HashMap<String, Point3d> postura) {   
        // Inicializa as juntas do skeleton que sera utilizado, definindo como
        // postura inicial a postura passada
        inicializaSkeleton(postura);        
    }      
    
    /**
     * Configura uma postura inicial hardcoded
     * @param 
     */
    private void setPosturaInicial() {
        
        Point3d pontoCabeca = new Point3d(300, 80, 0);
        double TAM_FRAME = 500;
        
        
        double ALTURA = 0;
        double DESLOC = 0;  
        
        final double TAM_PESCOCO   = (TAM_FRAME/10);
        final double TAM_OMBRO     = (TAM_FRAME/8);
        final double TAM_BRACO     = (TAM_FRAME/6);
        final double TAM_ANTEBRACO = (TAM_FRAME/5);        
        final double TAM_DORSO     = (TAM_FRAME/3);
        final double TAM_QUADRIL   = (TAM_FRAME/12);
        final double TAM_COXA      = (TAM_FRAME/3);
        final double TAM_CANELA    = (TAM_FRAME/4);
        final double TAM_MAO       = (TAM_FRAME/16);
        final double TAM_PE        = (TAM_FRAME/16);
        
    
        posturaInicial = new HashMap<>();
        
        posturaInicial.put(CABECA,      pontoCabeca);
        ALTURA = pontoCabeca.y + TAM_PESCOCO;
        DESLOC += TAM_OMBRO;
        posturaInicial.put(BASE_CABECA, new Point3d(pontoCabeca.x, ALTURA,0));
        posturaInicial.put(OMBRO_DIR,   new Point3d(pontoCabeca.x - DESLOC, ALTURA+15,0));
        posturaInicial.put(OMBRO_ESQ,   new Point3d(pontoCabeca.x + DESLOC, ALTURA+15,0));
        ALTURA += TAM_BRACO;
        DESLOC += 30;
        posturaInicial.put(COTOV_DIR,   new Point3d(pontoCabeca.x - DESLOC, ALTURA,0));
        posturaInicial.put(COTOV_ESQ,   new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));
        ALTURA += TAM_ANTEBRACO;
        DESLOC += 50;
        posturaInicial.put(PULSO_DIR,   new Point3d(pontoCabeca.x - DESLOC, ALTURA,0));
        posturaInicial.put(PULSO_ESQ,   new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));
        ALTURA += TAM_MAO;
        posturaInicial.put(MAO_DIR,      new Point3d(pontoCabeca.x - DESLOC, ALTURA,0));        
        posturaInicial.put(MAO_ESQ,      new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));        
        ALTURA = pontoCabeca.y + TAM_PESCOCO + TAM_DORSO;
        DESLOC = TAM_QUADRIL;
        posturaInicial.put(QUADRIL_CENTRO,  new Point3d(pontoCabeca.x, ALTURA,0));
        posturaInicial.put(QUADRIL_DIR, new Point3d(pontoCabeca.x - DESLOC, ALTURA+15,0));
        posturaInicial.put(QUADRIL_ESQ, new Point3d(pontoCabeca.x + DESLOC, ALTURA+15,0));
        ALTURA += TAM_COXA;
        DESLOC += 30;
        posturaInicial.put(JOELHO_DIR,  new Point3d(pontoCabeca.x - DESLOC, ALTURA,0));
        posturaInicial.put(JOELHO_ESQ,  new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));
        ALTURA += TAM_CANELA; 
        posturaInicial.put(TORNOZ_DIR,  new Point3d(pontoCabeca.x - DESLOC, ALTURA,0)); 
        posturaInicial.put(TORNOZ_ESQ,  new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));
        DESLOC += TAM_PE;
        posturaInicial.put(PE_DIR,      new Point3d(pontoCabeca.x - DESLOC, ALTURA,0));   
        posturaInicial.put(PE_ESQ,      new Point3d(pontoCabeca.x + DESLOC, ALTURA,0));       
    }
    
    /**
     * Inicializa a postura inicial do skeleton utilizando a postura passada como parâmetro
     * @param postura
     */
    private void inicializaSkeleton(HashMap<String, Point3d> postura) {        
        posturaInicial = postura;        
        novaPostura(posturaInicial);
        inicializaConexoes();
    }
    
    /**
     * Seta a postura atual para a postura passada como parâmetro
     * @param postura
     */
    public void novaPostura(HashMap<String, Point3d> postura) {  
        
        juntas.put(CABECA,      new JuntaSkeleton(CABECA,  "Cabeça", "ponto_cabeca", postura.get(CABECA)));
        juntas.put(BASE_CABECA, new JuntaSkeleton(BASE_CABECA, "Base da Cabeça", "base_cabeca",  postura.get(BASE_CABECA)));
        juntas.put(OMBRO_DIR,   new JuntaSkeleton(OMBRO_DIR,  "Ombro Direito", "ombro", postura.get(OMBRO_DIR)));
        juntas.put(COTOV_DIR,   new JuntaSkeleton(COTOV_DIR, "Cotovelo Direito", "cotovelo", postura.get(COTOV_DIR)));
        juntas.put(PULSO_DIR,   new JuntaSkeleton(PULSO_DIR, "Pulso Direito",    "pulso", postura.get(PULSO_DIR)));
        juntas.put(MAO_DIR,     new MaoSkeleton(MAO_DIR,  "Mão Direita",      "ponto_mao", postura.get(MAO_DIR)));
        juntas.put(OMBRO_ESQ,   new JuntaSkeleton(OMBRO_ESQ, "Ombro Esquerdo",   "ombro", postura.get(OMBRO_ESQ)));
        juntas.put(COTOV_ESQ,   new JuntaSkeleton(COTOV_ESQ,  "Cotovelo Esquerdo", "cotovelo", postura.get(COTOV_ESQ)));
        juntas.put(PULSO_ESQ,   new JuntaSkeleton(PULSO_ESQ, "Pulso Esquerdo",   "pulso", postura.get(PULSO_ESQ)));
        juntas.put(MAO_ESQ,     new MaoSkeleton(MAO_ESQ, "Mão Esquerda",     "ponto_mao", postura.get(MAO_ESQ)));
        juntas.put(QUADRIL_CENTRO, new JuntaSkeleton(QUADRIL_CENTRO,  "Base do Quadril(Centro)", "quadril", postura.get(QUADRIL_CENTRO)));
        juntas.put(QUADRIL_DIR, new JuntaSkeleton(QUADRIL_DIR, "Quadril Direito",    "quadril", postura.get(QUADRIL_DIR)));        
        juntas.put(JOELHO_DIR,  new JuntaSkeleton(JOELHO_DIR,  "Joelho Direito",     "joelho", postura.get(JOELHO_DIR)));
        juntas.put(TORNOZ_DIR,  new JuntaSkeleton(TORNOZ_DIR,  "Tornozelo Direito",  "tornozelo", postura.get(TORNOZ_DIR)));
        juntas.put(PE_DIR,      new JuntaSkeleton(PE_DIR,  "Pé Direito",         "ponto_pe", postura.get(PE_DIR)));
        juntas.put(QUADRIL_ESQ, new JuntaSkeleton(QUADRIL_ESQ, "Quadril Esquerdo",   "quadril", postura.get(QUADRIL_ESQ)));        
        juntas.put(JOELHO_ESQ,  new JuntaSkeleton(JOELHO_ESQ, "Joelho Esquerdo",   "joelho", postura.get(JOELHO_ESQ)));
        juntas.put(TORNOZ_ESQ,  new JuntaSkeleton(TORNOZ_ESQ, "Tornozelo Esquerdo", "tornozelo", postura.get(TORNOZ_ESQ)));
        juntas.put(PE_ESQ,      new JuntaSkeleton(PE_ESQ,  "Pé Esquerdo",       "ponto_pe", postura.get(PE_ESQ)));
        
        this.postura = postura;
    }
    
    /**
     * Cria as conexoes iniciais entre as juntas do skeleton
     * @param 
     */
    private void inicializaConexoes() {
        // Inicializa as conexoes existentes entre as juntas
        
        conexoes.add(new Conexao(Conexao.PESCOCO,       juntas.get(CABECA),         juntas.get(BASE_CABECA)));
        conexoes.add(new Conexao(Conexao.OMBRO_DIR,     juntas.get(BASE_CABECA),    juntas.get(OMBRO_DIR)));
        conexoes.add(new Conexao(Conexao.OMBRO_ESQ,     juntas.get(BASE_CABECA),    juntas.get(OMBRO_ESQ)));
        conexoes.add(new Conexao(Conexao.DORSO,         juntas.get(BASE_CABECA),    juntas.get(QUADRIL_CENTRO)));
        conexoes.add(new Conexao(Conexao.BRACO_ESQ,     juntas.get(OMBRO_ESQ),      juntas.get(COTOV_ESQ)));
        conexoes.add(new Conexao(Conexao.ANTEBRACO_ESQ, juntas.get(COTOV_ESQ),      juntas.get(PULSO_ESQ)));
        conexoes.add(new Conexao(Conexao.PALMA_ESQ,     juntas.get(PULSO_ESQ),      juntas.get(MAO_ESQ)));
        conexoes.add(new Conexao(Conexao.BRACO_DIR,     juntas.get(OMBRO_DIR),      juntas.get(COTOV_DIR)));
        conexoes.add(new Conexao(Conexao.ANTEBRACO_DIR, juntas.get(COTOV_DIR),      juntas.get(PULSO_DIR)));
        conexoes.add(new Conexao(Conexao.PALMA_DIR,     juntas.get(PULSO_DIR),      juntas.get(MAO_DIR)));
        conexoes.add(new Conexao(Conexao.QUADRIL_DIR,   juntas.get(QUADRIL_CENTRO), juntas.get(QUADRIL_DIR)));
        conexoes.add(new Conexao(Conexao.QUADRIL_ESQ,   juntas.get(QUADRIL_CENTRO), juntas.get(QUADRIL_ESQ)));
        conexoes.add(new Conexao(Conexao.COXA_DIR,      juntas.get(QUADRIL_DIR),    juntas.get(JOELHO_DIR)));
        conexoes.add(new Conexao(Conexao.CANELA_DIR,    juntas.get(JOELHO_DIR),     juntas.get(TORNOZ_DIR)));
        conexoes.add(new Conexao(Conexao.PLANTA_DIR,    juntas.get(TORNOZ_DIR),     juntas.get(PE_DIR)));
        conexoes.add(new Conexao(Conexao.COXA_ESQ,      juntas.get(QUADRIL_ESQ),    juntas.get(JOELHO_ESQ)));
        conexoes.add(new Conexao(Conexao.CANELA_ESQ,    juntas.get(JOELHO_ESQ),     juntas.get(TORNOZ_ESQ)));
        conexoes.add(new Conexao(Conexao.PLANTA_ESQ,    juntas.get(TORNOZ_ESQ),     juntas.get(PE_ESQ)));                  
    }
    
   public void resetaPostura() {
       juntas.clear();
       conexoes.clear();
       novaPostura(posturaInicial); 
       inicializaConexoes();
   }
    
    public HashMap<String, Point3d> getPostura() {        
        return (postura);
        
    }
    
    public float get3DJuntaX(int id) {
        return 0;
        
    }

    public float get3DJuntaY(int id) {
        return 0;
        
    }

    public float get3DJuntaZ(int id) {
        return 0;
        
    }

    public JuntaSkeleton get3DJunta(int id) {
        return null;
        
    }
    
}
