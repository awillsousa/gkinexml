
package gkinexml;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author anton_000
 */

public class GeradorXML {
    Skeleton sk;
    Document documento;
    
    public GeradorXML(Skeleton s) {
        this.sk = s;
        this.documento = DocumentHelper.createDocument();
    }
    
   public void geraXML() {
      try {
            MaoSkeleton maoDir   = (MaoSkeleton)   sk.juntas.get(Skeleton.MAO_DIR);
          JuntaSkeleton pulsoDir = (JuntaSkeleton) sk.juntas.get(Skeleton.PULSO_DIR);
          JuntaSkeleton cotovDir = (JuntaSkeleton) sk.juntas.get(Skeleton.COTOV_DIR);
          JuntaSkeleton ombroDir = (JuntaSkeleton) sk.juntas.get(Skeleton.OMBRO_DIR);
            MaoSkeleton maoEsq   = (MaoSkeleton)   sk.juntas.get(Skeleton.MAO_ESQ);
          JuntaSkeleton pulsoEsq = (JuntaSkeleton) sk.juntas.get(Skeleton.PULSO_ESQ);          
          JuntaSkeleton cotovEsq = (JuntaSkeleton) sk.juntas.get(Skeleton.COTOV_ESQ);          
          JuntaSkeleton ombroEsq = (JuntaSkeleton) sk.juntas.get(Skeleton.OMBRO_ESQ);
          
          JuntaSkeleton quadrilCentro = (JuntaSkeleton) sk.juntas.get(Skeleton.QUADRIL_CENTRO);
          JuntaSkeleton quadrilDir = (JuntaSkeleton) sk.juntas.get(Skeleton.QUADRIL_DIR);
          JuntaSkeleton quadrilEsq = (JuntaSkeleton) sk.juntas.get(Skeleton.QUADRIL_ESQ);
          JuntaSkeleton joelhoDir  = (JuntaSkeleton) sk.juntas.get(Skeleton.JOELHO_DIR);
          JuntaSkeleton joelhoEsq  = (JuntaSkeleton) sk.juntas.get(Skeleton.JOELHO_ESQ);
          JuntaSkeleton tornozDir  = (JuntaSkeleton) sk.juntas.get(Skeleton.TORNOZ_DIR);
          JuntaSkeleton tornozEsq  = (JuntaSkeleton) sk.juntas.get(Skeleton.TORNOZ_ESQ);
          JuntaSkeleton peDir      = (JuntaSkeleton) sk.juntas.get(Skeleton.PE_DIR);
          JuntaSkeleton peEsq      = (JuntaSkeleton) sk.juntas.get(Skeleton.PE_ESQ);
          
         Document document = DocumentHelper.createDocument();
         Element root = document.addElement( "ponto_cabeca" );
         Element membros = root.addElement("membros");
         
         Element superiores = membros.addElement("superiores");
         
         Element lado_direito_superior = superiores.addElement("lado_direito");
         
         Element no1_angulo_entre_lds_x = lado_direito_superior.addElement("no1_angulo_entre");
            String formato = maoDir.getFormato().toString(); 
            for (Eixo te : Eixo.values()) {
                Rotacao r = pulsoDir.getRotacao(te); 
                if (r != null) {
                    Element ponto_mao_lds = no1_angulo_entre_lds_x.addElement("ponto_mao");               
                       ponto_mao_lds.addElement("formato_atributos").addText(formato);   // formato da mão direita
                       ponto_mao_lds.addElement("min_rot_atributos").addText(r.getSentido().toString());
                       ponto_mao_lds.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element pulso_lds = no1_angulo_entre_lds_x.addElement("pulso");
                         pulso_lds.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         pulso_lds.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                         pulso_lds.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element cotovelo_lds = no1_angulo_entre_lds_x.addElement("cotovelo");
                         cotovelo_lds.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         cotovelo_lds.addElement("eixo_atributos").addText(String.valueOf(r.getAngulo()));                 
                }
            }
         // AINDA FALTA INSERIR OS VALORES DO ELEMENTO no1_angulo_entre_lds para os eixos y e z
                 
         Element no2_angulo_entre_lds_x = lado_direito_superior.addElement("no2_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = cotovDir.getRotacao(te);
                if (r != null) {
                    Element pulso_lds_2 = no2_angulo_entre_lds_x.addElement("pulso");
                         pulso_lds_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         pulso_lds_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element cotovelo_lds_2 = no2_angulo_entre_lds_x.addElement("cotovelo");
                         cotovelo_lds_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         cotovelo_lds_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                         cotovelo_lds_2.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element ombro_lds_2 = no2_angulo_entre_lds_x.addElement("ombro");
                        ombro_lds_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        ombro_lds_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
         // AINDA FALTA INSERIR OS VALORES DO ELEMENTO no2_angulo_entre_lds para os eixos y e z
                
         Element no3_angulo_entre_lds_x = lado_direito_superior.addElement("no3_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = ombroDir.getRotacao(te);
                if (r != null) {
                    Element cotovelo_lds_3 = no3_angulo_entre_lds_x.addElement("cotovelo");
                         cotovelo_lds_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         cotovelo_lds_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element ombro_lds_3 = no3_angulo_entre_lds_x.addElement("ombro");
                        ombro_lds_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        ombro_lds_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        ombro_lds_3.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_lds_3 = no3_angulo_entre_lds_x.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_lds_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_lds_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
         // AINDA FALTA INSERIR OS VALORES DO ELEMENTO no4_angulo_entre_lds para os eixos y e z
        /*         
         Element no4_angulo_entre_lds = lado_direito_superior.addElement("no4_angulo_entre");
            Element braco_lds_4 = no4_angulo_entre_lds.addElement("braco");
                 braco_lds_4.addElement("min_rot_atributos").addText("");
                 braco_lds_4.addElement("eixo_atributos").addText("");

            Element ombro_lds_4 = no4_angulo_entre_lds.addElement("ombro");
                ombro_lds_4.addElement("min_rot_atributos").addText("");
                ombro_lds_4.addElement("eixo_atributos").addText("");
                ombro_lds_4.addElement("angulo_atributos").addText("");
                
            Element peito_lds_4 = no4_angulo_entre_lds.addElement("peito");
                 peito_lds_4.addElement("min_rot_atributos").addText("");
                 peito_lds_4.addElement("eixo_atributos").addText("");
         */
        // AINDA FALTA INSERIR OS VALORES DO ELEMENTO no4_angulo_entre_lds para os eixos y e z         
                 
         
         Element lado_esquerdo_superior = superiores.addElement("lado_esquerdo");
         Element no1_angulo_entre_les_x = lado_esquerdo_superior.addElement("no1_angulo_entre");
            formato = maoEsq.getFormato().toString(); 
            for (Eixo te : Eixo.values()) {
                Rotacao r = pulsoEsq.getRotacao(te); 
                if (r != null) {
                    Element ponto_mao_les = no1_angulo_entre_les_x.addElement("ponto_mao");
                       ponto_mao_les.addElement("formato_atributos").addText(formato);   // formato da mão direita
                       ponto_mao_les.addElement("min_rot_atributos").addText(r.getSentido().toString());
                       ponto_mao_les.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element pulso_les = no1_angulo_entre_les_x.addElement("pulso");
                         pulso_les.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         pulso_les.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                         pulso_les.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element cotovelo_les = no1_angulo_entre_les_x.addElement("cotovelo");
                         cotovelo_les.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         cotovelo_les.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());  
                }
            }
         Element no2_angulo_entre_les_x = lado_esquerdo_superior.addElement("no2_angulo_entre");
         for (Eixo te : Eixo.values()) {
            Rotacao r = cotovEsq.getRotacao(te);    
            if (r != null) {
               Element pulso_les_2 = no2_angulo_entre_les_x.addElement("pulso");
                       pulso_les_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                       pulso_les_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                  Element cotovelo_les_2 = no2_angulo_entre_les_x.addElement("cotovelo");
                       cotovelo_les_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                       cotovelo_les_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                       cotovelo_les_2.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                  Element ombro_les_2 = no2_angulo_entre_les_x.addElement("ombro");
                      ombro_les_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                      ombro_les_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
            }   
         }
         Element no3_angulo_entre_les_x = lado_esquerdo_superior.addElement("no3_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = ombroEsq.getRotacao(te); 
                if (r != null) {
                    Element cotovelo_les_3 = no3_angulo_entre_les_x.addElement("cotovelo");
                         cotovelo_les_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         cotovelo_les_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element ombro_les_3 = no3_angulo_entre_les_x.addElement("ombro");
                        ombro_les_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        ombro_les_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        ombro_les_3.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_les_3 = no3_angulo_entre_les_x.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_les_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_les_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
            /*
         Element no4_angulo_entre_les = lado_esquerdo_superior.addElement("no4_angulo_entre");
            Element braco_les_4 = no4_angulo_entre_les.addElement("braco");
                 braco_les_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                 braco_les_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

            Element ombro_les_4 = no4_angulo_entre_les.addElement("ombro");
                ombro_les_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                ombro_les_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                ombro_les_4.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));
                
            Element peito_les_4 = no4_angulo_entre_les.addElement("peito");
                 peito_les_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                 peito_les_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
         */
            
         Element inferiores = membros.addElement("inferiores");
         
         Element lado_direito_inferior = inferiores.addElement("lado_direito");
         Element no1_angulo_entre_ldi = lado_direito_inferior.addElement("no1_angulo_entre");
         for (Eixo te : Eixo.values()) {   
                Rotacao r = tornozDir.getRotacao(te); 
                if (r != null) {
                    Element ponto_pe_ldi_1 = no1_angulo_entre_ldi.addElement("ponto_pe");
                         ponto_pe_ldi_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         ponto_pe_ldi_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element tornozelo_ldi_1 = no1_angulo_entre_ldi.addElement("tornozelo");
                        tornozelo_ldi_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        tornozelo_ldi_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        tornozelo_ldi_1.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element joelho_ldi_1 = no1_angulo_entre_ldi.addElement("joelho");
                         joelho_ldi_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         joelho_ldi_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
         }   
         Element no2_angulo_entre_ldi_x = lado_direito_inferior.addElement("no2_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = joelhoDir.getRotacao(te); 
                if (r != null) {
                    Element tornozelo_ldi_2 = no2_angulo_entre_ldi_x.addElement("tornozelo");
                         tornozelo_ldi_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         tornozelo_ldi_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element joelho_ldi_2 = no2_angulo_entre_ldi_x.addElement("joelho");
                         joelho_ldi_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         joelho_ldi_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                         joelho_ldi_2.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element quadril_ldi_2 = no2_angulo_entre_ldi_x.addElement("quadril");
                        quadril_ldi_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_ldi_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
         Element no3_angulo_entre_ldi_x = lado_direito_inferior.addElement("no3_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = quadrilDir.getRotacao(te); 
                if (r != null) {
                    Element joelho_ldi_3 = no3_angulo_entre_ldi_x.addElement("joelho");
                         joelho_ldi_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         joelho_ldi_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element quadril_ldi_3 = no3_angulo_entre_ldi_x.addElement("quadril");
                        quadril_ldi_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_ldi_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        quadril_ldi_3.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_ldi_3 = no3_angulo_entre_ldi_x.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_ldi_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_ldi_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());                 
                }
            }
            
         Element no4_angulo_entre_ldi = lado_direito_inferior.addElement("no4_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = quadrilDir.getRotacao(te); 
                if (r != null) {
                    Element coxa_ldi_4 = no4_angulo_entre_ldi.addElement("coxa");
                         coxa_ldi_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         coxa_ldi_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element quadril_ldi_4 = no4_angulo_entre_ldi.addElement("quadril");
                        quadril_ldi_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_ldi_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        quadril_ldi_4.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_ldi_4 = no4_angulo_entre_ldi.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_ldi_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_ldi_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
            
         Element lado_esquerdo_inferior = inferiores.addElement("lado_esquerdo");
         Element no1_angulo_entre_lei = lado_esquerdo_inferior.addElement("no1_angulo_entre");
         for (Eixo te : Eixo.values()) {
            Rotacao r = tornozEsq.getRotacao(te); 
            if (r != null) {
                Element ponto_pe_lei_1 = no1_angulo_entre_lei.addElement("ponto_pe");
                     ponto_pe_lei_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                     ponto_pe_lei_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                Element tornozelo_lei_1 = no1_angulo_entre_lei.addElement("tornozelo");
                    tornozelo_lei_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                    tornozelo_lei_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                    tornozelo_lei_1.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                Element joelho_lei_1 = no1_angulo_entre_lei.addElement("joelho");
                     joelho_lei_1.addElement("min_rot_atributos").addText(r.getSentido().toString());
                     joelho_lei_1.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
            }
         }
         
         Element no2_angulo_entre_lei_x = lado_esquerdo_inferior.addElement("no2_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = joelhoEsq.getRotacao(te); 
                if (r != null) {
                    Element tornozelo_lei_2 = no2_angulo_entre_lei_x.addElement("tornozelo");
                         tornozelo_lei_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         tornozelo_lei_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element joelho_lei_2 = no2_angulo_entre_lei_x.addElement("joelho");
                         joelho_lei_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         joelho_lei_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                         joelho_lei_2.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element quadril_lei_2 = no2_angulo_entre_lei_x.addElement("quadril");
                        quadril_lei_2.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_lei_2.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
         Element no3_angulo_entre_lei_x = lado_esquerdo_inferior.addElement("no3_angulo_entre");
           for (Eixo te : Eixo.values()) {
                Rotacao r = quadrilEsq.getRotacao(Eixo.X); 
                if (r != null) {
                    Element joelho_lei_3 = no3_angulo_entre_lei_x.addElement("joelho");
                         joelho_lei_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         joelho_lei_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element quadril_lei_3 = no3_angulo_entre_lei_x.addElement("quadril");
                        quadril_lei_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_lei_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        quadril_lei_3.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_lei_3 = no3_angulo_entre_lei_x.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_lei_3.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_lei_3.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());   
                }
           }
           
         Element no4_angulo_entre_lei = lado_esquerdo_inferior.addElement("no4_angulo_entre");
            for (Eixo te : Eixo.values()) {
                Rotacao r = quadrilEsq.getRotacao(te); 
                if (r != null) {
                    Element coxa_lei_4 = no4_angulo_entre_lei.addElement("coxa");
                         coxa_lei_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         coxa_lei_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());

                    Element quadril_lei_4 = no4_angulo_entre_lei.addElement("quadril");
                        quadril_lei_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                        quadril_lei_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                        quadril_lei_4.addElement("angulo_atributos").addText(String.valueOf(r.getAngulo()));

                    Element eixo_vertical_centro_lei_4 = no4_angulo_entre_lei.addElement("eixo_vertical_centro");
                         eixo_vertical_centro_lei_4.addElement("min_rot_atributos").addText(r.getSentido().toString());
                         eixo_vertical_centro_lei_4.addElement("eixo_atributos").addText(r.getEixoRotacao().toString());
                }
            }
            
         Element relacoes_entre = root.addElement("relacoes_entre");
         Element elementos = relacoes_entre.addElement("elementos");
         for (Relacao rel : sk.relacoes) {
             
             if (rel.getTipo() == TipoRelacao.Alinhamento) {
                Element alinhamento = elementos.addElement("alinhamento");
                alinhamento.addElement("opcao1").addElement("opcoes_atributos").addText(rel.getElementos().get(0).getId());
                alinhamento.addElement("opcao2").addElement("opcoes_atributos").addText(rel.getElementos().get(1).getId());
                alinhamento.addElement("em_relacao").addElement("opcoes_atributos").addText(rel.getAlinhamento().toString());             
             } else if (rel.getTipo() == TipoRelacao.Altura) {
                Element altura = elementos.addElement("altura");
                altura.addElement("opcao1").addElement("opcoes_atributos").addText(rel.getElementos().get(0).getId());
                altura.addElement("opcao2").addElement("opcoes_atributos").addText(rel.getElementos().get(1).getId());                
                 elementos.addElement("altura").addElement("altura_atributos").addText(String.valueOf(rel.getValorAltura()));
             } else if (rel.getTipo() == TipoRelacao.Assimetria) {
                 Element assimetria = elementos.addElement("assimetria");
                 assimetria.addElement("opcao1").addElement("opcoes_atributos").addText(rel.getElementos().get(0).getId());
                 assimetria.addElement("opcao2").addElement("opcoes_atributos").addText(rel.getElementos().get(1).getId());                
                 assimetria.addElement("em_relacao").addElement("opcoes_atributos").addText(String.valueOf(rel.getJuntaAssimetria().getId()));
             } else if (rel.getTipo() == TipoRelacao.Simetria) {
                 Element simetria = elementos.addElement("simetria");
                 simetria.addElement("opcao1").addElement("opcoes_atributos").addText(rel.getElementos().get(0).getId());
                 simetria.addElement("opcao2").addElement("opcoes_atributos").addText(rel.getElementos().get(1).getId());                
                 simetria.addElement("em_relacao").addElement("opcoes_atributos").addText(String.valueOf(rel.getJuntaSimetria().getId()));
             }
         }
                  
         // Pretty print the document to System.out
         OutputFormat format = OutputFormat.createPrettyPrint();
         XMLWriter writer;
         writer = new XMLWriter( System.out, format ); 
         writer.write( document );
         this.documento = document;
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public Document getDocumento() {
       return (this.documento);
   }
}
