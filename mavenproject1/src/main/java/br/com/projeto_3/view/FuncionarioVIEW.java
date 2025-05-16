/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_3.view;

/**
 *
 * @author leonardo-teixeira
 */
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_3.dto.FuncionarioDTO;
import br.com.projeto_3.ctr.FuncionarioCTR;

public class FuncionarioVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form FuncionarioVIEW
     */
    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();

    int gravar_alterar;

    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_funcionario;

    public FuncionarioVIEW() {
        initComponents();
        
        //chama todos os métodosé liberaCampos
        liberaCampos(false);
        //chama o métodoé liberaBotõesõ
        liberaBotoes(true, false, false, false, true);
        
        modelo_jtl_consultar_funcionario = (DefaultTableModel) jtl_consultar_funcionario.getModel();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation(d.width - this.getSize().width / 2, (d.width - this.getSize().height) / 2);
    }

    private void gravar() {
        try {
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.inserirFuncionario(funcionarioDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    } // Fecha método gravar()

    private void alterar() {
        try {
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            if ((checkAlterarSenha.isSelected()) && (senha_fun.getPassword().length != 0)) {
                funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            } else {
                funcionarioDTO.setSenha_fun(null);
            }
            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.alterarFuncionario(funcionarioDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    } // Fecha método alterar()

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Funcionario?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.excluirFuncionario(funcionarioDTO)
            );
        }
    } // Fecha método excluir()

    private void liberaCampos(boolean a) {
        nome_fun.setEnabled(a);
        cpf_fun.setEnabled(a);
        login_fun.setEnabled(a);
        tipo_fun.setEnabled(a);
        senha_fun.setEnabled(a);
        checkAlterarSenha.setEnabled(a);
    } // Fecha método liberaCampos(boolean a)

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    } // Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e)

    private void limpaCampos() {
        nome_fun.setText("");
        cpf_fun.setText("");
        login_fun.setText("");
        senha_fun.setText("");
        checkAlterarSenha.setSelected(false);
    } // Fecha método limpaCampos()

    private void preencheTabela(String nome_fun) {
        try {
            // Limpa todas as linhas
            modelo_jtl_consultar_funcionario.setNumRows(0);
            // Enquanto tiver linhas - faça
            funcionarioDTO.setNome_fun(nome_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1); // 1 é a pesquisa por nome na classe DAO
            while (rs.next()) {
                modelo_jtl_consultar_funcionario.addRow(new Object[]{
                    rs.getString("id_fun"),
                    rs.getString("nome_fun"),});
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela: " + e.getMessage());
        } finally {
            funcionarioCTR.CloseDB();
        }
    }// Fecha método preencheTabela(String nome_fun)

    private void preencheCampos(int id_fun) {
        try {
            funcionarioDTO.setId_fun(id_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 2); // 2 é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();
                nome_fun.setText(rs.getString("nome_fun"));
                cpf_fun.setText(rs.getString("cpf_fun"));
                login_fun.setText(rs.getString("login_fun"));
                tipo_fun.setSelectedItem(rs.getString("tipo_fun"));

                gravar_alterar = 2;
                liberaCampos(true);
                senha_fun.setEnabled(false);
                checkAlterarSenha.setSelected(false);
            } // fecha if (rs.next)
        } // fecha try
        catch (Exception e) {
            System.out.println("Erro preencheCampos: " + e.getMessage());
        } finally {
            funcionarioCTR.CloseDB();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nome_fun = new javax.swing.JTextField();
        cpf_fun = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        login_fun = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        senha_fun = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        checkAlterarSenha = new javax.swing.JCheckBox();
        tipo_fun = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pesquisa_nome_fun = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcionario = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Cantarell Extra Bold", 0, 15)); // NOI18N
        jLabel1.setText("FUNCIONARIO");

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        nome_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_funActionPerformed(evt);
            }
        });

        jLabel4.setText("Login:");

        login_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_funActionPerformed(evt);
            }
        });

        jLabel5.setText("Senha:");

        senha_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senha_funActionPerformed(evt);
            }
        });

        jLabel6.setText("Tipo:");

        checkAlterarSenha.setText("Alterar Senha");
        checkAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAlterarSenhaActionPerformed(evt);
            }
        });

        tipo_fun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Comum", "" }));
        tipo_fun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_funActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cantarell Extra Bold", 0, 18)); // NOI18N
        jLabel7.setText("CONSULTA");

        jLabel8.setText("Nome:");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

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

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jtl_consultar_funcionario.setModel(new javax.swing.table.DefaultTableModel(
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
        jtl_consultar_funcionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcionario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(login_fun, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nome_fun)
                                .addComponent(cpf_fun, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(tipo_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(senha_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkAlterarSenha))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pesquisa_nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(222, 222, 222))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(pesquisa_nome_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cpf_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(login_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(senha_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkAlterarSenha))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(tipo_fun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_funActionPerformed

    private void login_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_login_funActionPerformed

    private void checkAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAlterarSenhaActionPerformed
        // TODO add your handling code here:
        if(checkAlterarSenha.isSelected()){
            senha_fun.setEnabled(true);
        }else{
            senha_fun.setEnabled(false);
            senha_fun.setText(null);
        }
    }//GEN-LAST:event_checkAlterarSenhaActionPerformed

    private void tipo_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_funActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        preencheTabela(pesquisa_nome_fun.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_funcionario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!");
            }
        }
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        checkAlterarSenha.setEnabled(false);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jtl_consultar_funcionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcionarioMouseClicked
        // TODO add your handling code here:
        preencheCampos(Integer.parseInt(String.valueOf
        (jtl_consultar_funcionario.getValueAt
        (jtl_consultar_funcionario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_funcionarioMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void senha_funActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senha_funActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senha_funActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkAlterarSenha;
    private javax.swing.JTextField cpf_fun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcionario;
    private javax.swing.JTextField login_fun;
    private javax.swing.JTextField nome_fun;
    private javax.swing.JTextField pesquisa_nome_fun;
    private javax.swing.JTextField senha_fun;
    private javax.swing.JComboBox<String> tipo_fun;
    // End of variables declaration//GEN-END:variables
}
