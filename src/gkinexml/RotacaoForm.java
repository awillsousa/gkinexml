/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gkinexml;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author anton_000
 */
public class RotacaoForm extends javax.swing.JFrame {
    JuntaSkeleton junta;
    
    /**
     * Creates new form RotacaoForm
     */
    public RotacaoForm() {
        initComponents();
        this.radioEixoX.setEnabled(false);
        this.radioEixoY.setEnabled(false);
        this.radioEixoZ.setEnabled(false);
    }
    
    public RotacaoForm(JuntaSkeleton junta, Eixo eixo) {
        initComponents();
        this.junta = junta;
        if (junta != null) {
           SelecionaEixo(eixo);
           
           // Se existe uma rotacao ja definida, preenche os campos com os valores dela
           if (junta.getRotacao(eixo) !=  null)
           {
                if (junta.getRotacao(eixo).getSentido().equals(SentidoRotacao.Horario)) {
                     this.comboSentido.setSelectedIndex(0);
                } else {
                     this.comboSentido.setSelectedIndex(1);
                }

                this.txAngulo.setText(String.valueOf(junta.getRotacao(eixo).getAngulo()));
           }
        } 
    }
    
    public void Abrir(JuntaSkeleton junta, Eixo eixo) {        
        this.junta = junta;        
        if (junta != null) {
           // Se existe uma rotacao ja definida, preenche os campos com os valores dela
           if (junta.getRotacao(eixo) !=  null)
           {
                if (junta.getRotacao(eixo).getSentido().equals(SentidoRotacao.Horario)) {
                     this.comboSentido.setSelectedIndex(0);
                } else {
                     this.comboSentido.setSelectedIndex(1);
                }

                this.txAngulo.setText(String.valueOf(junta.getRotacao(eixo).getAngulo()));
           } else {
               ZeraCampos();
           } 
           SelecionaEixo(eixo);
        } 
    }
    
    public void ZeraCampos() {
        this.radioEixoX.setSelected(true);
        this.comboSentido.setSelectedIndex(0);
        this.txAngulo.setText("");
    }
    
    public void SelecionaEixo(Eixo eixo) {
        if (eixo == Eixo.X) {
              this.radioEixoX.setSelected(true);              
           } else if (eixo == Eixo.Y) {
               this.radioEixoY.setSelected(true);
           } else if (eixo == Eixo.Z) {
               this.radioEixoZ.setSelected(true);
           }
        
    }
    
    public Eixo getEixoSelecionado() {
        if (this.radioEixoX.isSelected()) {
           return (Eixo.X); 
        } else if (this.radioEixoY.isSelected()) {
            return (Eixo.Y);
        } else {
            return (Eixo.Z);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        comboSentido = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        radioEixoX = new javax.swing.JRadioButton();
        radioEixoY = new javax.swing.JRadioButton();
        radioEixoZ = new javax.swing.JRadioButton();
        txAngulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btGravar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setTitle("Rota????o");

        comboSentido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hor??rio", "Anti-hor??rio" }));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("Eixo de Rota????o");

        buttonGroup1.add(radioEixoX);
        radioEixoX.setText("Eixo X");

        buttonGroup1.add(radioEixoY);
        radioEixoY.setText("Eixo Y");

        buttonGroup1.add(radioEixoZ);
        radioEixoZ.setText("Eixo Z");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(radioEixoX)
                .addGap(18, 18, 18)
                .addComponent(radioEixoY)
                .addGap(16, 16, 16)
                .addComponent(radioEixoZ)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioEixoY)
                    .addComponent(radioEixoZ)
                    .addComponent(radioEixoX))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txAngulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txAnguloKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setLabelFor(comboSentido);
        jLabel1.setText("Sentido");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setLabelFor(txAngulo);
        jLabel2.setText("??ngulo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Eixo de Rota????o");

        btGravar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btGravar.setText("Gravar");
        btGravar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGravarMouseClicked(evt);
            }
        });

        btCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btGravar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCancelar))
                        .addComponent(comboSentido, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txAngulo, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSentido, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGravar)
                    .addComponent(btCancelar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txAnguloKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txAnguloKeyTyped

    private void btGravarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGravarMouseClicked
        SentidoRotacao s = (this.comboSentido.getSelectedIndex() == 0 ? SentidoRotacao.Horario : SentidoRotacao.Antihorario);
        Eixo eixo = this.getEixoSelecionado();
        float angulo = 180;
        if (!this.txAngulo.getText().isEmpty()) {
            angulo = Float.valueOf(this.txAngulo.getText());
        } else {
            JOptionPane.showMessageDialog(this,"Digite o valor do ??ngulo de rota????o!","ERRO",JOptionPane.ERROR_MESSAGE);
        }
               
        this.junta.setRotacao(s, eixo, angulo);
        
        this.setVisible(false);
    }//GEN-LAST:event_btGravarMouseClicked

    private void btCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCancelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btCancelarMouseClicked

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
            java.util.logging.Logger.getLogger(RotacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RotacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RotacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RotacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RotacaoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btGravar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboSentido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radioEixoX;
    private javax.swing.JRadioButton radioEixoY;
    private javax.swing.JRadioButton radioEixoZ;
    private javax.swing.JTextField txAngulo;
    // End of variables declaration//GEN-END:variables
}
