/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package br.com.projeto_2.view;
import java.awt.Dimension;
import  javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_2.dto.FornecedorDTO;
import br.com.projeto_2.ctr.FornecedorCTR;
import br.com.projeto_2.dto.ProdutoDTO;
import br.com.projeto_2.ctr.ProdutoCTR;
/**
 *
 * @author leonardo-teixeira
 */
public class ProdutoVIEW extends javax.swing.JInternalFrame {
    
    FornecedorDTO fornecedorDTO =  new FornecedorDTO();
    FornecedorCTR fornecedorCTR = new FornecedorCTR();
    ProdutoDTO produtoDTO = new ProdutoDTO();
    ProdutoCTR produtoCTR =  new ProdutoCTR();
    
    int gravar_alterar; //variavel usada para saber se esta alterando ou não
    
    
    ResultSet rs; //Variavel usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_produto; //Varaivel para guardar o modelo da tabela
    DefaultTableModel modelo_jtl_consultar_fornecedor; // Variavel para guardar o modelo dda tabela
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation(d.width -  this.getSize().width / 2, (d.height - this.getSize().height / 2));
    }
    
    private void gravar(){
        try {
            produtoDTO.setNome_prod(nome_prod.getText());
            produtoDTO.setDesc_prod(desc_prod.getText());
            produtoDTO.setCod_bar_prod(cod_bar_prod.getText());
            produtoDTO.setP_custo_prod(Double.parseDouble(p_custo_prod.getText()));
            produtoDTO.setP_venda_prod(Double.parseDouble(p_venda_prod.getText()));
            fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                    jtl_consultar_fornecedor.getValueAt(
                            jtl_consultar_fornecedor.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null,
                    produtoCTR.inserirProduto(produtoDTO, fornecedorDTO));
            
        } catch (Exception e) {
            System.out.println("Erro ao Gravar " + e.getMessage());
        }
    }
    
    private void alterar(){
        try {
            produtoDTO.setNome_prod(nome_prod.getText());
            produtoDTO.setDesc_prod(desc_prod.getText());
            produtoDTO.setCod_bar_prod(cod_bar_prod.getText());
            produtoDTO.setP_custo_prod(Double.parseDouble(p_custo_prod.getText()));
            produtoDTO.setP_venda_prod(Double.parseDouble(p_venda_prod.getText()));
            fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                    jtl_consultar_fornecedor.getValueAt(
                            jtl_consultar_fornecedor.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null,
                    produtoCTR.inserirProduto(produtoDTO, fornecedorDTO));
            
        } catch (Exception e) {
            System.out.println("Erro ao alterar " + e.getMessage());
        }
    }
    
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluiro produto?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,
                        produtoCTR.excluirProduto(produtoDTO));
        }
    }
    
    private void liberaCampos(boolean a){
        nome_prod.setEnabled(a);
        desc_prod.setEnabled(a);
        cod_bar_prod.setEnabled(a);
        p_custo_prod.setEnabled(a);
        p_venda_prod.setEnabled(a);
        pesquisa_nome_fornecedor.setEnabled(a);
        btnPesquisarFornecedor.setEnabled(a);
        p_venda_prod.setEnabled(a);
    }
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    
    private void limpaCampos(){
          nome_prod.setText("");
         desc_prod.setText("");
        cod_bar_prod.setText("");
        p_custo_prod.setText("");
        p_venda_prod.setText("");
        pesquisa_nome_fornecedor.setText("");
        modelo_jtl_consultar_fornecedor.setNumRows(0);
    }
    
    private void preencheTabelaProdutos(String nome_prod){
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_fornecedor.setNumRows(0);
            //Enquanto tiver linhas  - faça
            produtoDTO.setNome_prod(nome_prod);
            rs = produtoCTR.consultarFornecedor(produtoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_produto.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod"),
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabelaProduto: " + e.getMessage());
        }
        finally{
            produtoCTR.CloseDB();
        }
    }
    
private void preencheTabelaFornecedor(String nome_for) {
    try {
        // Limpa todas as linhas
        modelo_jtl_consultar_fornecedor.setNumRows(0);

        // Enquanto tiver linhas – faça
        fornecedorDTO.setNome_for(nome_for);
        rs = fornecedorCTR.consultarFornecedor(fornecedorDTO, 1); // 1 = é a pesquisa por nome na classe DAO

        while (rs.next()) {
            modelo_jtl_consultar_fornecedor.addRow(new Object[] {
                rs.getString("id_for"),
                rs.getString("nome_for"),
            });
        }
    } catch (Exception e) {
        System.out.println("Erro preencheTabelaFornecedor: " + e.getMessage());
    } finally {
        produtoCTR.CloseDB();
    }
}

private void preencheCamposProduto(int id_prod) {
    try {
        produtoDTO.setId_prod(id_prod);
        rs = produtoCTR.consultarProduto(produtoDTO, 2); // 2 = é a pesquisa no id na classe DAO
        if (rs.next()) {
            limpaCampos();

            nome_prod.setText(rs.getString("nome_prod"));
            desc_prod.setText(rs.getString("desc_prod"));
            cod_bar_prod.setText(rs.getString("cod_bar_prod"));
            p_custo_prod.setText(rs.getString("p_custo_prod"));
            p_venda_prod.setText(rs.getString("p_venda_prod"));

            ///// Colocando os dados do fornecedor
            modelo_jtl_consultar_fornecedor.setNumRows(0);
            modelo_jtl_consultar_fornecedor.addRow(new Object[] {
                rs.getInt("id_for"), rs.getString("nome_for")
            });
            jtl_consultar_fornecedor.setRowSelectionInterval(0, 0);
        }
    } catch (Exception e) {
        System.out.println("Erro ao preencher campos do produto: " + e.getMessage());
    }
}


public ProdutoVIEW() {
    initComponents();

    // Chama todos os métodos liberaCampos
    liberaCampos(false);

    // Chama o método liberaBotoes
    liberaBotoes(true, false, false, false, true);

    modelo_jtl_consultar_produto = (DefaultTableModel) jtl_consultar_produto.getModel();
    modelo_jtl_consultar_fornecedor = (DefaultTableModel) jtl_consultar_fornecedor.getModel();
}

// Fecha método preencheTabelaFornecedor(String nome_for)

    /**
     * Creates new form ProdutoVIEW
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        nome_prod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        desc_prod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cod_bar_prod = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        p_custo_prod = new javax.swing.JTextField();
        p_venda_prod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_nome_fornecedor = new javax.swing.JTextField();
        btnPesquisarFornecedor = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pesquisa_nome_produto = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_produto = new javax.swing.JTable();
        btnPesquisarProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_fornecedor = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome:");
        desktopPane.add(jLabel1);
        jLabel1.setBounds(40, 40, 44, 19);

        nome_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_prodActionPerformed(evt);
            }
        });
        desktopPane.add(nome_prod);
        nome_prod.setBounds(100, 30, 460, 30);

        jLabel2.setFont(new java.awt.Font("Cantarell Extra Bold", 0, 18)); // NOI18N
        jLabel2.setText("Produto");
        desktopPane.add(jLabel2);
        jLabel2.setBounds(330, 0, 90, 20);

        jLabel3.setText("Descrição:");
        desktopPane.add(jLabel3);
        jLabel3.setBounds(20, 80, 100, 20);
        desktopPane.add(desc_prod);
        desc_prod.setBounds(100, 70, 460, 30);

        jLabel4.setText("Código:");
        desktopPane.add(jLabel4);
        jLabel4.setBounds(40, 110, 50, 20);

        cod_bar_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod_bar_prodActionPerformed(evt);
            }
        });
        desktopPane.add(cod_bar_prod);
        cod_bar_prod.setBounds(100, 110, 150, 30);

        jLabel5.setText("P. custo:");
        desktopPane.add(jLabel5);
        jLabel5.setBounds(30, 160, 60, 19);
        desktopPane.add(p_custo_prod);
        p_custo_prod.setBounds(100, 150, 150, 30);

        p_venda_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_venda_prodActionPerformed(evt);
            }
        });
        desktopPane.add(p_venda_prod);
        p_venda_prod.setBounds(340, 150, 140, 29);

        jLabel6.setText("P.venda:");
        desktopPane.add(jLabel6);
        jLabel6.setBounds(280, 160, 60, 19);

        jLabel7.setText("Fornecedor:");
        desktopPane.add(jLabel7);
        jLabel7.setBounds(20, 240, 80, 19);

        pesquisa_nome_fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_fornecedorActionPerformed(evt);
            }
        });
        desktopPane.add(pesquisa_nome_fornecedor);
        pesquisa_nome_fornecedor.setBounds(100, 230, 460, 30);

        btnPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarFornecedorActionPerformed(evt);
            }
        });
        desktopPane.add(btnPesquisarFornecedor);
        btnPesquisarFornecedor.setBounds(570, 220, 60, 37);

        jLabel8.setFont(new java.awt.Font("Cantarell Extra Bold", 0, 18)); // NOI18N
        jLabel8.setText("Consulta");
        desktopPane.add(jLabel8);
        jLabel8.setBounds(750, 80, 90, 22);

        jLabel9.setText("Nome:");
        desktopPane.add(jLabel9);
        jLabel9.setBounds(640, 140, 44, 19);

        pesquisa_nome_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_produtoActionPerformed(evt);
            }
        });
        desktopPane.add(pesquisa_nome_produto);
        pesquisa_nome_produto.setBounds(690, 130, 210, 29);
        desktopPane.add(jTabbedPane1);
        jTabbedPane1.setBounds(700, 130, 100, 100);
        desktopPane.add(jTabbedPane2);
        jTabbedPane2.setBounds(740, 120, 100, 100);

        jtl_consultar_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_produtoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_produto);

        desktopPane.add(jScrollPane1);
        jScrollPane1.setBounds(640, 160, 330, 240);

        btnPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProdutoActionPerformed(evt);
            }
        });
        desktopPane.add(btnPesquisarProduto);
        btnPesquisarProduto.setBounds(900, 120, 60, 37);

        jtl_consultar_fornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jScrollPane2.setViewportView(jtl_consultar_fornecedor);

        desktopPane.add(jScrollPane2);
        jScrollPane2.setBounds(20, 260, 610, 140);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        desktopPane.add(btnNovo);
        btnNovo.setBounds(70, 420, 100, 36);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        desktopPane.add(btnSalvar);
        btnSalvar.setBounds(180, 420, 105, 36);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        desktopPane.add(btnCancelar);
        btnCancelar.setBounds(290, 420, 122, 37);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        desktopPane.add(btnExcluir);
        btnExcluir.setBounds(420, 420, 109, 37);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        desktopPane.add(btnSair);
        btnSair.setBounds(540, 420, 90, 36);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void nome_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_prodActionPerformed

    private void cod_bar_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod_bar_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cod_bar_prodActionPerformed

    private void p_venda_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_venda_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_venda_prodActionPerformed

    private void pesquisa_nome_fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_fornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_fornecedorActionPerformed

    private void pesquisa_nome_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_produtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_produtoActionPerformed

    private void btnPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdutoActionPerformed
        // TODO add your handling code here:
        preencheTabelaProdutos(pesquisa_nome_produto.getText());
    }//GEN-LAST:event_btnPesquisarProdutoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
            // TODO add your handling code here:
            liberaCampos(true);
            liberaBotoes(false, true, true, false, true);
            gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarFornecedorActionPerformed
        // TODO add your handling code here:
        preencheTabelaFornecedor(pesquisa_nome_fornecedor.getText());
    }//GEN-LAST:event_btnPesquisarFornecedorActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
    if (gravar_alterar == 1) {
        gravar();
        gravar_alterar = 0;
    } else {
        if (gravar_alterar == 2) {
            alterar();
            gravar_alterar = 0;
        } else {
            JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
        }
    }

    limpaCampos();
    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_produtoMouseClicked
        // TODO add your handling code here:
        preencheCamposProduto(Integer.parseInt(String.valueOf(
            jtl_consultar_produto.getValueAt(
            jtl_consultar_produto.getSelectedRow(), 0))));
        liberaBotoes( false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_produtoMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_produto.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_produto.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

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
            java.util.logging.Logger.getLogger(ProdutoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutoVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarFornecedor;
    private javax.swing.JButton btnPesquisarProduto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cod_bar_prod;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JTextField desc_prod;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jtl_consultar_fornecedor;
    private javax.swing.JTable jtl_consultar_produto;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField nome_prod;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTextField p_custo_prod;
    private javax.swing.JTextField p_venda_prod;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JTextField pesquisa_nome_fornecedor;
    private javax.swing.JTextField pesquisa_nome_produto;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
