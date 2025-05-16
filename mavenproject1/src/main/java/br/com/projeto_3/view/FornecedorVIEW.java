/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_3.dto.FornecedorDTO;
import br.com.projeto_3.ctr.FornecedorCTR;
import java.text.SimpleDateFormat;

/**
 *
 * @author leonardo-teixeira
 */
public class FornecedorVIEW extends javax.swing.JInternalFrame {

    SimpleDateFormat data_format = new SimpleDateFormat("dd/MM/yyyy");

    FornecedorDTO fornecedorDTO = new FornecedorDTO(); // Cria um objeto fornecedorDTO
    FornecedorCTR fornecedorCTR = new FornecedorCTR(); // cria um objeto fornecedorCTR

    int gravar_alterar; // Variavel usada para saber se esta alterando ou incluindo

    ResultSet rs; // Variavel usada para preenchimeto da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_fornecedor; // Variavel para guardar o modelo da tabela

    /**
     * Método para centralizar o internalFrame.
     */
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    } // Fecha método setPosicao()

    /**
     * Creates new form FornecedorVIEW
     */
    public FornecedorVIEW() {
        initComponents();

        // Chama todos os métodos liberaCampos
        liberaCampos(false);

        // Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);

        modelo_jtl_consultar_fornecedor = (DefaultTableModel) jtl_consultar_fornecedor.getModel();
//setPosicao();
    }

    /**
     * Método utilizado para gravar os dados do fornecedor.
     */
    private void gravar() {
        try {
            fornecedorDTO.setNome_for(nome_for.getText());
            fornecedorDTO.setCnpj_for(cnpj_for.getText());
            fornecedorDTO.setTel_for(tel_for.getText());
            fornecedorDTO.setData_cad_for(data_format.parse(data_cad_for.getText()));

            JOptionPane.showMessageDialog(null,
                    fornecedorCTR.inserirFornecedor(fornecedorDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    } // Fecha método gravar()

    /**
     * Método utilizado para alterar os dados do fornecedor.
     */
    private void alterar() {
        try {
            fornecedorDTO.setNome_for(nome_for.getText());
            fornecedorDTO.setCnpj_for(cnpj_for.getText());
            fornecedorDTO.setTel_for(tel_for.getText());
            fornecedorDTO.setData_cad_for(data_format.parse(data_cad_for.getText()));

            JOptionPane.showMessageDialog(null,
                    fornecedorCTR.alterarFornecedor(fornecedorDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    } // Fecha método alterar()

    /**
     * Método utilizado para excluir os dados do fornecedor.
     */
    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Fornecedor?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    fornecedorCTR.excluirFornecedor(fornecedorDTO)
            );
        }
    } // Fecha método excluir()

    /**
     * Método utilizado para liberar/bloquear os campos da tela.
     *
     * @param a, boolean com true(libera) false(bloqueia).
     */
    private void liberaCampos(boolean a) {
        nome_for.setEnabled(a);
        cnpj_for.setEnabled(a);
        tel_for.setEnabled(a);
        data_cad_for.setEnabled(a);
    } // Fecha método liberaCampos(boolean a)

    /**
     * Método utilizado para liberar os botões da tela.
     *
     * @param a, boolean com true(libera) false(bloqueia) para o btnNovo.
     * @param b, boolean com true(libera) false(bloqueia) para o btnSalvar.
     * @param c, boolean com true(libera) false(bloqueia) para o btnCancelar.
     * @param d, boolean com true(libera) false(bloqueia) para o btnExcluir.
     * @param e, boolean com true(libera) false(bloqueia) para o btnSair.
     */
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    } // Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e)

    /**
     * Método utilizado para limpar os campos da tela.
     */
    private void limpaCampos() {
        nome_for.setText("");
        cnpj_for.setText("");
        tel_for.setText("");
        data_cad_for.setText("");
    } // Fecha método limpaCampos()

    /**
     * Método utilizado para preencher/construir a Jtable.
     *
     * @param nome_for, String com o nome do fornecedor
     */
    private void preencheTabela(String nome_for) {
        try {
            // Limpa todas as linhas
            modelo_jtl_consultar_fornecedor.setNumRows(0);
            // Enquanto tiver linhas - faça
            fornecedorDTO.setNome_for(nome_for);
            rs = fornecedorCTR.consultarFornecedor(fornecedorDTO, 1); // 1 é a pesquisa por nome na classe DAO
            while (rs.next()) {
                modelo_jtl_consultar_fornecedor.addRow(new Object[]{
                    rs.getString("id_for"),
                    rs.getString("nome_for"),});
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela: " + e.getMessage());
        } finally {
            fornecedorCTR.CloseDB();
        }
    }// Fecha método preencheTabela(String nome_for)

    /**
     * Método utilizado para preencher os campos da tela com valores do
     * fornecedor.
     *
     * @param id_for, int com o id do fornecedor.
     */
    private void preencheCampos(int id_for) {
        try {
            fornecedorDTO.setId_for(id_for);
            rs = fornecedorCTR.consultarFornecedor(fornecedorDTO, 2); // 2 é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();
                nome_for.setText(rs.getString("nome_for"));
                cnpj_for.setText(rs.getString("cnpj_for"));
                tel_for.setText(rs.getString("tel_for"));
                data_cad_for.setText(rs.getString("data_cad_for"));
                gravar_alterar = 2;
                liberaCampos(true);
            } // fecha if (rs.next)
        } // fecha try
        catch (Exception e) {
            System.out.println("Erro SQL: " + e);
        } finally {
            fornecedorCTR.CloseDB();
        }
    }// Fecha método preencheCampos(int id_for)

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome_for = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cnpj_for = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tel_for = new javax.swing.JTextField();
        data_cad_for = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pesquisa_nome = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_fornecedor = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Fornecedor");

        jLabel2.setText("Nome:");

        jLabel3.setText("CNPJ:");

        jLabel4.setText("Telefone:");

        jLabel5.setText("Data Cad:");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Consulta");

        jLabel7.setText("Nome:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jtl_consultar_fornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_fornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_fornecedor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nome_for, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cnpj_for, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tel_for, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(72, 72, 72)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(data_cad_for)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nome_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cnpj_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tel_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(data_cad_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnSalvar)
                            .addComponent(btnCancelar)
                            .addComponent(btnExcluir)
                            .addComponent(btnSair)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
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

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(pesquisa_nome.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_fornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_fornecedorMouseClicked
        // Pega o id do fornecedor selecionado e chama preencheCampos
        preencheCampos(Integer.parseInt(String.valueOf(
                jtl_consultar_fornecedor.getValueAt(
                        jtl_consultar_fornecedor.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_fornecedorMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_fornecedor.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_fornecedor.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cnpj_for;
    private javax.swing.JTextField data_cad_for;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_fornecedor;
    private javax.swing.JTextField nome_for;
    private javax.swing.JTextField pesquisa_nome;
    private javax.swing.JTextField tel_for;
    // End of variables declaration//GEN-END:variables
}
