
package gkinexml;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 *
 * @author AntonioSousa
 */
public class PrincipalForm extends javax.swing.JFrame {

   private final Skeleton skeleton;
   private final AvatarStick avatar;
   private final LinkedHashMap<String, ImagemOpcao> opcoes =  new LinkedHashMap<String, ImagemOpcao>();
    
    /**
     * Creates new form PrincipalForm 
    */
    public PrincipalForm() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        
        // Cria e inicializa um novo skeleton
        skeleton = new Skeleton();
        avatar   = new AvatarStick(skeleton);
        
        /* Desenha a representacao do skeleton no panel */
        AvatarPanel ap = new AvatarPanel(avatar);
       try {
           PrintStream customOut = new PrintStream(new CustomOutputStream(textoXML), true, "UTF-8");
           System.setOut(customOut);
       } catch (IOException ex) {
           Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
       }
        ap.setPainelXML(textoXML);
        this.stickPanel.add(ap.control, BorderLayout.NORTH);
        this.stickPanel.add(ap, BorderLayout.CENTER);    
             
        /* Cria uma lista de opcoes relacionadas com as juntas */        
        int layout = BoxLayout.PAGE_AXIS;
       
        opcoes.put(Skeleton.CABECA,new ImagemOpcao( new ImageIcon(getClass().getResource("imgs/cabeca-15.png"))));
        opcoes.put(Skeleton.PULSO_ESQ, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/pulso-esq-14.png"))));
        opcoes.put(Skeleton.COTOV_ESQ, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/cotov-esq-13.png"))));
        opcoes.put(Skeleton.OMBRO_ESQ, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/ombro-esq-12.png"))));
        opcoes.put(Skeleton.BASE_CABECA, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/base-cabeca-11.png"))));
        opcoes.put(Skeleton.QUADRIL_CENTRO, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/quadril-centro-10.png"))));
        opcoes.put(Skeleton.QUADRIL_ESQ, new ImagemOpcao( new ImageIcon(getClass().getResource("imgs/quadril-esq-9.png"))));
        opcoes.put(Skeleton.JOELHO_ESQ, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/joelho-esq-8.png"))));
        opcoes.put(Skeleton.TORNOZ_ESQ, new ImagemOpcao( new ImageIcon(getClass().getResource("imgs/tornoz-esq-7.png"))));
        opcoes.put(Skeleton.PULSO_DIR, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/pulso-dir-6.png"))));
        opcoes.put(Skeleton.COTOV_DIR,  new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/cotov-dir-5.png"))));
        opcoes.put(Skeleton.OMBRO_DIR, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/ombro-dir-4.png"))));
        opcoes.put(Skeleton.QUADRIL_DIR, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/quadril-dir-3.png"))));
        opcoes.put(Skeleton.JOELHO_DIR, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/joelho-dir-2.png"))));
        opcoes.put(Skeleton.TORNOZ_DIR, new ImagemOpcao(new ImageIcon(getClass().getResource("imgs/tornoz-dir-1.png"))));
       
        for (ImagemOpcao opcao : opcoes.values()) {
            this.botoesPanel.add(opcao, layout);            
            opcao.addMouseListener(new LabelAdapter());
        }
        
        repaint();
    }

    
    private class LabelAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImagemOpcao l = (ImagemOpcao) e.getComponent(); 
            l.destaca();
            //l.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImagemOpcao l = (ImagemOpcao) e.getComponent();
            l.limpaDestaque();
            //l.setBorder(null);
        }
    }
    
        
    /*private void desenhaStickMan(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);

        Ellipse2D.Double head = new Ellipse2D.Double(5, 10, 50, 50);
        g2.draw(head);

        Line2D.Double body=new Line2D.Double(30,60, 30,150);
        g2.draw(body);

        Line2D.Double arm1=new Line2D.Double(30,60,75,75);
        g2.draw(arm1);
        Line2D.Double arm2=new Line2D.Double(30,60,-75,-75);
        g2.draw(arm2);
        
        this.stickPanel.repaint();

    }*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        elemPanel = new javax.swing.JPanel();
        stickPanel = new javax.swing.JPanel();
        botoesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelXML = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoXML = new javax.swing.JTextArea();
        btExportaXML = new javax.swing.JButton();
        menuPrincipal = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GKineXML ");

        panelPrincipal.setLayout(new javax.swing.BoxLayout(panelPrincipal, javax.swing.BoxLayout.Y_AXIS));

        elemPanel.setAlignmentX(0.0F);
        elemPanel.setAlignmentY(1.0F);
        elemPanel.setLayout(new javax.swing.BoxLayout(elemPanel, javax.swing.BoxLayout.LINE_AXIS));

        stickPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        stickPanel.setLayout(new java.awt.BorderLayout());
        elemPanel.add(stickPanel);

        botoesPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botoesPanel.setAlignmentY(0.0F);
        botoesPanel.setMaximumSize(new java.awt.Dimension(1, 1));
        botoesPanel.setMinimumSize(new java.awt.Dimension(1, 1));
        botoesPanel.setPreferredSize(new java.awt.Dimension(1, 1));
        botoesPanel.setLayout(new javax.swing.BoxLayout(botoesPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Elementos");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentY(0.0F);
        botoesPanel.add(jLabel1);

        jLabel2.setText("          ");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setAlignmentY(0.0F);
        botoesPanel.add(jLabel2);

        jLabel3.setText("           ");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setAlignmentY(0.0F);
        botoesPanel.add(jLabel3);

        elemPanel.add(botoesPanel);

        panelXML.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelXML.setLayout(new java.awt.BorderLayout());

        textoXML.setColumns(20);
        textoXML.setRows(31);
        textoXML.setWrapStyleWord(true);
        textoXML.setAlignmentY(0.0F);
        textoXML.setMinimumSize(new java.awt.Dimension(4, 30));
        textoXML.setName(""); // NOI18N
        jScrollPane1.setViewportView(textoXML);

        panelXML.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        btExportaXML.setText("Exportar XML");
        btExportaXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportaXMLActionPerformed(evt);
            }
        });
        panelXML.add(btExportaXML, java.awt.BorderLayout.PAGE_END);

        elemPanel.add(panelXML);

        panelPrincipal.add(elemPanel);

        getContentPane().add(panelPrincipal, java.awt.BorderLayout.CENTER);
        setJMenuBar(menuPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btExportaXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportaXMLActionPerformed
        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter("Export-GKine.xml", false))) {
            this.textoXML.write(fileOut);
            JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso!","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {   
           Logger.getLogger(PrincipalForm.class.getName()).log(Level.SEVERE, null, ex);
       }   
    }//GEN-LAST:event_btExportaXMLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botoesPanel;
    private javax.swing.JButton btExportaXML;
    private javax.swing.JPanel elemPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelXML;
    private javax.swing.JPanel stickPanel;
    private javax.swing.JTextArea textoXML;
    // End of variables declaration//GEN-END:variables
}
