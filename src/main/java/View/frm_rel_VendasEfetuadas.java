/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerVenda;
import Model.*;
import Model.ItemVendido;
import Model.Venda;
import Model.VendaItem;
import javax.swing.JFrame;
/**
 *
 * @author Christian
 */
public class frm_rel_VendasEfetuadas extends javax.swing.JFrame {

    private boolean relVendas = true;
    private boolean relClientePFMaisComprou = false;
    private boolean relClientePJMenosComprou = false;
    private boolean relProdutos = false;

    public void inicializaControles() {
        jTextRel.setEditable(false);
        if (relVendas) {
            carregaVendas();
        }
        if (relProdutos) {
            carregaProdutos();
        }
        if (relClientePFMaisComprou) {
            carregaClientePFMaisComprou();
        }
        if (relClientePJMenosComprou) {
            carregaClientePJMenosComprou();
        }
    }

    public void setClientePFMaisComprou() {
        relVendas = false;
        relClientePFMaisComprou = true;
        relClientePJMenosComprou = false;
        relProdutos = false;
        jLabelTituloRel.setText("Cliente PF que mais efetuou compras");
    }

    public void setClientePJMenosComprou() {
        relVendas = false;
        relClientePFMaisComprou = false;
        relClientePJMenosComprou = true;
        relProdutos = false;
        jLabelTituloRel.setText("Cliente PJ que efetuou menos compras");
    }

    public void setRelacaoProdutos() {
        relVendas = false;
        relClientePFMaisComprou = false;
        relClientePJMenosComprou = false;
        relProdutos = true;
        jLabelTituloRel.setText("Relação de Produtos (Itens) vendidos");
    }

    /**
     * Creates new form frm_rel_comprasEfetuadas
     */
    public frm_rel_VendasEfetuadas() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void carregaVendas() {
        jTextRel.removeAll();
        ControllerVenda srv = new ControllerVenda();
        String str = "";

        for (Venda c : srv.getVendas()) {
            str += "============================================\n";
            str += c.toString();
            str += "\nITENS: \n";
            for (VendaItem i : c.getItens()) {
                str += i.toString() + "\n";
            }
        }
        jTextRel.setText(str);
    }

    private void carregaProdutos() {
        jTextRel.removeAll();
        ControllerVenda srv = new ControllerVenda();
        String str = "";

        for (ItemVendido c : srv.getProdutosVendidos()) {
            str += c.toString()+"\n";
        }
        jTextRel.setText(str);
    }

    private void carregaClientePFMaisComprou() {
        jTextRel.removeAll();
        ControllerVenda srv = new ControllerVenda();
        String str = "";

        for (ClienteCompra c : srv.getClientePFMaisComprou()) {
            str += c.toString();
        }
        jTextRel.setText(str);
    }

    private void carregaClientePJMenosComprou() {
        jTextRel.removeAll();
        ControllerVenda srv = new ControllerVenda();
        String str = "";

        for (ClienteCompra c : srv.getClientePJMenosComprou()) {
            str += c.toString();
        }
        jTextRel.setText(str);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButtonFechar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelTituloRel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextRel = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatório de Vendas");

        jButtonFechar.setText("Fechar");
        jButtonFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFecharMouseClicked(evt);
            }
        });
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFechar)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFechar)
                .addContainerGap())
        );

        jLabelTituloRel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabelTituloRel.setText("Vendas Efetuadas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabelTituloRel)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTituloRel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextRel.setColumns(20);
        jTextRel.setRows(5);
        jScrollPane1.setViewportView(jTextRel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFecharMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jButtonFecharMouseClicked

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
            java.util.logging.Logger.getLogger(frm_rel_VendasEfetuadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_rel_VendasEfetuadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_rel_VendasEfetuadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_rel_VendasEfetuadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_rel_VendasEfetuadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JLabel jLabelTituloRel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextRel;
    // End of variables declaration//GEN-END:variables
}
