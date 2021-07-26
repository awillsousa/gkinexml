package gkinexml;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Adaptada da classe criada por John B. Matthews; 
 * Distribuída sob a GPL.
 */
public class AvatarPanel extends JComponent {

//    private static final int WIDE = 640;
//    private static final int HIGH = 480;
    private final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
    public static Plane planeX = new Plane(new Vector3D(1, 0, 0));
    public static Plane planeY = new Plane(new Vector3D(0, 1, 0));
    private final int WIDE = 800;
    private final int HIGH = 600;
    ControlPanel control = new ControlPanel();
    private final AvatarStick avatar;
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private final Rectangle mouseRect = new Rectangle();
    private boolean selecionando = false;
    private final RotacaoForm formRotacao = new RotacaoForm();
    private JTextArea painelXML;
   
    
    public AvatarPanel(AvatarStick as) {        
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
        this.avatar = as;
        
        repaint();
    }
     
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        avatar.desenha(g);
        if (selecionando) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
    }
   
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            selecionando = false;
            mouseRect.setBounds(0, 0, 0, 0);            
                        
            if (avatar.getSelecionados().size() > 0) {
                int qtSelec = avatar.getSelecionados().size();
                if (qtSelec == 1) {
                    JuntaSkeleton js = avatar.getSelecionados().get(0).getJuntaSkeleton();
                    selecionando = false; 
                    if (e.isPopupTrigger()) {                                         
                            if (js instanceof MaoSkeleton)                
                                control.popup.add(control.subMenuMao);
                            else
                                control.popup.remove(control.subMenuMao);

                            showPopup(e);                   
                    } else {             
                            if (js instanceof MaoSkeleton) {
                                MaoSkeleton mao = (MaoSkeleton) avatar.getSelecionados().get(0).getJuntaSkeleton();
                                control.formaMaoCombo.setSelectedItem(mao.getFormato());
                            }                   
                    }
                } else if (qtSelec == 2) {
                   if (e.isPopupTrigger()) {
                       
                   }
                }
            } else {
                selecionando = true;                
            }
            
            e.getComponent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePt = e.getPoint();
            if (e.isShiftDown()) {
                avatar.seleciona(mousePt);
            } else if (avatar.selecionaUm(mousePt)) {
                selecionando = false;
            } else {
                avatar.selecionaNenhum();
                selecionando = true;
            }
            e.getComponent().repaint();
        }
        
        private void showPopup(MouseEvent e) {
            control.popup.show(e.getComponent(), e.getX(), e.getY());
        }
        
        public void mouseEntered(MouseEvent me) {
            //ToolTipManager.sharedInstance().setDismissDelay(60000);
        }

        public void mouseExited(MouseEvent me) {
            //ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
        }
    }
    
    private class MouseMotionHandler extends MouseMotionAdapter {
        @Override
            public void mouseMoved(MouseEvent e) {
                for (JuntaStick js : avatar.juntas) {
                    if(js.contem(e.getPoint())) {
                        String info = js.getJuntaSkeleton().getInfo();
                        setToolTipText(info);
                        break;
                    }
                    else
                        setToolTipText(null);
                }
                 
                ToolTipManager.sharedInstance().mouseMoved(e);                     
            }
    }

    public JToolBar getControlPanel() {
        return control;
    }

    private class ControlPanel extends JToolBar {
        
        private final Action     posInicial = new ResetAction("Posição Inicial");
        private final Action     rotacionar = new RotacaoAction("Rotação");
        private final Action       formaMao = new FormaMaoAction("Formato");
        private final Action       relacao  = new RelacaoAction("Relação");
        private final Action    color = new ColorAction("Cor");
        private final Action  connect = new ConnectAction("Conectar");        
        private final Action   geraXML = new GeraXMLAction("Gerar XML");
        private final JButton rotacionarButton = new JButton(rotacionar);
        private final JComboBox      eixoCombo = new JComboBox();
        private final JComboBox  formaMaoCombo = new JComboBox();
        private final JComboBox  relacaoCombo = new JComboBox();        
        private final CorIcone         hueIcon = new CorIcone(Color.blue);
        private JPopupMenu      popup = new JPopupMenu();
        private JMenu subMenuMao;
        private JMenu subMenuEixo;
        private JMenu subMenuRelacao;

        ControlPanel() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            //this.setBackground(Color.LIGHT_GRAY);

            // Gera os componentes da barra de ferramentas
            this.add(new JButton(posInicial)); 
            //this.addSeparator(new Dimension(2,1));                     
            this.add(new JLabel(" Eixo:"));
            this.add(eixoCombo);
            this.add(rotacionarButton);
            //this.addSeparator(new Dimension(2,1));       
            this.add(new JLabel(" Mão em:"));
            this.add(formaMaoCombo);
            //this.addSeparator(new Dimension(2,1));
            this.add(new JLabel(" Relação:"));
            this.add(relacaoCombo);
            this.add(new JButton(relacao));
            
            this.add(new JButton(geraXML));
            
            //Gera os componentes do menu popup            
            popup.add(new JMenuItem(color));
            popup.add(new JMenuItem(connect)); 
            
            // Preenche combo dos eixos
            subMenuEixo = new JMenu("Rotacionar");
            for (Eixo k : Eixo.values()) {
                eixoCombo.addItem(k);
                subMenuEixo.add(new JMenuItem(new EixoItemAction(k)));
            }
            popup.add(subMenuEixo);
            
            // Preenche combo dos formatos de mão e gera o submenu correspondente
            subMenuMao = new JMenu("Formato");
            for (FormatoMao f : FormatoMao.values()) {
                formaMaoCombo.addItem(f);
                subMenuMao.add(new JMenuItem(new FormaMaoItemAction(f)));
            }
            
            popup.add(subMenuMao);
            formaMaoCombo.addActionListener(formaMao);
            
            // Preenche combo das relações e gera o submenu correspondente
            subMenuRelacao = new JMenu("Relação");
            for (TipoRelacao t : TipoRelacao.values()) {
                relacaoCombo.addItem(t);
                subMenuRelacao.add(new JMenuItem(new RelacaoItemAction(t)));
            }
            
            popup.add(subMenuRelacao);
            //relacaoCombo.addActionListener(relacao);
        }
        
        class RelacaoItemAction extends AbstractAction {
            private TipoRelacao tipo;
            
            public RelacaoItemAction(TipoRelacao r) {
                super(r.toString());
                this.tipo = r;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if ((avatar.getSelecionados().isEmpty()) || (avatar.getSelecionados().size() == 1)) {
                    JOptionPane.showMessageDialog(null,"Selecione pelo menos duas juntas para estabelecer uma relação!","ERRO",JOptionPane.ERROR_MESSAGE);
                } else {
                    ArrayList<JuntaSkeleton> lista = new ArrayList<JuntaSkeleton>();
                    for (JuntaStick j : avatar.getSelecionados()) {
                        lista.add(j.getJuntaSkeleton());
                    }
                    // chamada para o dialog para definir os valores da relação 
                    avatar.getSkeleton().relacoes.add(new Relacao(this.tipo,lista));                    
                }                
                repaint();
            }
        }
        
        class EixoItemAction extends AbstractAction {

            private Eixo k;

            public EixoItemAction(Eixo k) {
                super(k.toString());
                this.k = k;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (avatar.getSelecionados().size() > 1) {
                    JOptionPane.showMessageDialog(null,"Selecione apenas uma única junta para aplicar uma rotação!","ERRO",JOptionPane.ERROR_MESSAGE);
                } else if (avatar.getSelecionados().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Selecione pelo menos uma junta!","ERRO",JOptionPane.ERROR_MESSAGE);
                } else {
                    JuntaSkeleton j = avatar.getSelecionados().get(0).getJuntaSkeleton();
                    formRotacao.Abrir(j, k);
                    formRotacao.setVisible(true);
                }                
                    repaint();
            }
        }
        
        class FormaMaoItemAction extends AbstractAction {

            private FormatoMao formato;

            public FormaMaoItemAction(FormatoMao f) {
                super(f.toString());
                this.formato = f;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                formaMaoCombo.setSelectedItem(formato);
            }
        }
    }

    private class ResetAction extends AbstractAction {

        public ResetAction(String name) {
            super(name);
        }
         
        @Override
        public void actionPerformed(ActionEvent e) {
            avatar.getSkeleton().resetaPostura();
            avatar.resetaPostura();
                                    
            repaint();
        }
    }

    private class ColorAction extends AbstractAction {

        public ColorAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = control.hueIcon.getColor();
            color = JColorChooser.showDialog(
                AvatarPanel.this, "Choose a color", color);
            if (color != null) {
                avatar.updateCor(color);
                control.hueIcon.setColor(color);
                control.repaint();
                repaint();
            }
        }
    }

    private class ConnectAction extends AbstractAction {

        public ConnectAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<JuntaStick> selecionados = avatar.getSelecionados();
            if (selecionados.size() > 1) {
                for (int i = 0; i < selecionados.size() - 1; ++i) {
                    JuntaStick n1 = selecionados.get(i);
                    JuntaStick n2 = selecionados.get(i + 1);
                    avatar.conexoes.add(new ConexaoStick(n1, n2));
                }
            }
            repaint();
        }
    }

    private class FormaMaoAction extends AbstractAction {

        public FormaMaoAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource();
            FormatoMao formato = (FormatoMao) combo.getSelectedItem();
            
            if (avatar.getSelecionados().size() > 2) {
                JOptionPane.showMessageDialog(null,"Selecione no máximo as duas mãos para definir formato!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else if (avatar.getSelecionados().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Selecione pelo menos uma mão para definir o formato!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else {
                JuntaSkeleton j = avatar.getSelecionados().get(0).getJuntaSkeleton();
                if (j instanceof MaoSkeleton) {
                    MaoSkeleton mao = (MaoSkeleton) j;
                    mao.setFormato(formato);
                }                    
            }
        
            repaint();
        }
    }

    private class RotacaoAction extends AbstractAction {

        public RotacaoAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {      
            if (avatar.getSelecionados().size() > 1) {
                JOptionPane.showMessageDialog(null,"Selecione apenas uma única junta para aplicar uma rotação!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else if (avatar.getSelecionados().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Selecione pelo menos uma junta!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else {
                JuntaSkeleton j = avatar.getSelecionados().get(0).getJuntaSkeleton();
                formRotacao.Abrir(j, (Eixo) control.eixoCombo.getSelectedItem());
                formRotacao.setVisible(true);
            }
            
            repaint();
        }
    }

    private class RelacaoAction extends AbstractAction {

        public RelacaoAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            TipoRelacao tipo = (TipoRelacao) control.relacaoCombo.getSelectedItem();
            
            if ((avatar.getSelecionados().isEmpty()) || (avatar.getSelecionados().size() == 1)) {
                    JOptionPane.showMessageDialog(null,"Selecione pelo menos duas juntas para estabelecer uma relação!","ERRO",JOptionPane.ERROR_MESSAGE);
            } else {
                ArrayList<JuntaSkeleton> lista = new ArrayList<>();
                for (JuntaStick j : avatar.getSelecionados()) {
                    lista.add(j.getJuntaSkeleton());
                }
                // chamada para o dialog para definir os valores da relação 
                Relacao r = new Relacao(tipo,lista);
                avatar.getSkeleton().relacoes.add(r);
                
                if (tipo == TipoRelacao.Alinhamento) {
                   TipoAlinhamento[] ta = TipoAlinhamento.values();
                   TipoAlinhamento alinhamento = (TipoAlinhamento) JOptionPane.showInputDialog(null, 
                            "Escolha o tipo de alinhamento.",
                            "Alinhamento",
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            ta, 
                            ta[0]);
                   r.setAlinhamento(alinhamento);
                } else if (tipo == TipoRelacao.Altura) {
                   String altura = JOptionPane.showInputDialog(null, "Digite o valor da altura:"); 
                   r.setValorAltura(Float.valueOf(altura));
                } else if (tipo == TipoRelacao.Assimetria) {                   
                   String nomeJunta = (String) JOptionPane.showInputDialog(null, 
                            "Escolha o elemento definidor da relação de assimetria.",
                            "Relação",
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            Skeleton.nomesPontos, 
                            Skeleton.nomesPontos[0]);
                   r.setJuntaAssimetria(avatar.getSkeleton().juntas.get(nomeJunta));
                } else if (tipo == TipoRelacao.Simetria) {
                    String nomeJunta = (String) JOptionPane.showInputDialog(null, 
                            "Escolha o elemento definidor da relação de simetria.",
                            "Relação",
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            Skeleton.nomesPontos, 
                            Skeleton.nomesPontos[0]);
                   r.setJuntaSimetria(avatar.getSkeleton().juntas.get(nomeJunta));
                }
            }     
        
            repaint();
        }
    }
    
    private class GeraXMLAction extends AbstractAction {
                    
        public GeraXMLAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GeradorXML geradorXML = new GeradorXML(avatar.getSkeleton());
            painelXML.setText("");
            geradorXML.geraXML();
            
            /*
            painelXML.setText(geradorXML.getDocumento().asXML());
                    */
            repaint();
        }
    }
    
    public JTextArea getPainelXML() {        
        return (this.painelXML);
    }
    
    public void setPainelXML(JTextArea j) {
        this.painelXML = j;
    }
}
