/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_1.view;

import br.com.projeto_1.ctr.AlunoCTR;
import br.com.projeto_1.dto.AlunoDTO;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leonardo
 */
public class AlunoVIEW extends javax.swing.JInternalFrame {

    AlunoDTO aluno = new AlunoDTO();
    AlunoCTR alunoCTR = new AlunoCTR();

    int SALVANDO = 0;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_aluno;

    /**
     * Creates new form AlunoView
     */
    public AlunoVIEW() {
        initComponents();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_aluno = (DefaultTableModel) jtl_consultar_aluno.getModel();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();

        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void preencheTabela(String nome) {
        modelo_jtl_consultar_aluno.setNumRows(0);

        aluno.setNome(nome);
        rs = alunoCTR.consultarAluno(aluno, 1);

        try {
            while (rs.next()) {
                modelo_jtl_consultar_aluno.addRow(new Object[]{
                    rs.getString("prontuario"),
                    rs.getString("nome"),});
            }
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex);
        } finally {
            alunoCTR.closeDB();
        }
    }

 private void preencheCampos(int id_aluno) {
    try {
        rs = alunoCTR.consultarAluno(aluno, 2);
        if (rs.next()) {
            nome.setText(rs.getString("nome"));
            prontuario.setText(rs.getString("prontuario"));
            cidadeOrigem.setText(rs.getString("cidadeOrigem"));
            rg.setText(rs.getString("rg"));
            telefone.setText(rs.getString("telefone"));
            email.setText(rs.getString("email"));
            horarioIda.setText(rs.getString("horarioIda"));
            horarioVolta.setText(rs.getString("horarioVolta"));
            curso.setText(rs.getString("curso"));
            periodo.setText(rs.getString("periodo"));
            cep.setText(rs.getString("cep"));
            System.out.println("ID do aluno selecionado: " + prontuario);
        }
    } catch (SQLException e) {
        System.out.println("Erro SQL: " + e);
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar ResultSet: " + e);
        }
        alunoCTR.closeDB();
    }
}

    private void liberaCampos(boolean visibility) {
        nome.setEnabled(visibility);

        cidadeOrigem.setEnabled(visibility);

        rg.setEnabled(visibility);

    }

    public void limpaCampos() {
        nome.setText("");
        sobrenome.setText("");
        telefone.setText("");
        email.setText("");
        cidadeOrigem.setText("");
        cep.setText("");
        prontuario.setText("");
        rg.setText("");
        horarioIda.setText("");
        horarioVolta.setText("");
        telefone.setText("");
        curso.setText("");
        periodo.setText("");
    }

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }

    public void gravar() {
        try {
            aluno.setNome(nome.getText());
            aluno.setSobrenome(title);
            aluno.setCidadeOrigem(sobrenome.getText());
            aluno.setProntuario(prontuario.getText());
            aluno.setCep(cep.getText());
            aluno.setTelefone(telefone.getText());
            aluno.setRg(rg.getText());
            aluno.setEmail(rg.getText());
            aluno.setHorarioIda(rg.getText());
            aluno.setHorarioVolta(rg.getText());
            aluno.setCurso(rg.getText());
            aluno.setPeriodo(rg.getText());

            JOptionPane.showMessageDialog(null, alunoCTR.inserirAluno(aluno));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   public void atualizar() {
    try {
        aluno.setNome(nome.getText());
        aluno.setSobrenome(sobrenome.getText());  
        aluno.setCidadeOrigem(cidadeOrigem.getText());
        aluno.setProntuario(prontuario.getText());  
        aluno.setCep(cep.getText());
        aluno.setTelefone(telefone.getText());
        aluno.setRg(rg.getText());
        aluno.setEmail(email.getText());  
        aluno.setHorarioIda(horarioIda.getText()); 
        aluno.setHorarioVolta(horarioVolta.getText());  
        aluno.setCurso(curso.getText());  
        aluno.setPeriodo(periodo.getText());  

        JOptionPane.showMessageDialog(null, alunoCTR.atualizarAluno(aluno));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erro: O prontuário deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();  
        JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

   private void excluir() {
    if (JOptionPane.showConfirmDialog(null, "Deseja excluir o Aluno?",
            "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        try {
            // Obter o prontuário do aluno selecionado na tabela
            aluno.setProntuario((
                    String.valueOf(jtl_consultar_aluno.getValueAt(
                            jtl_consultar_aluno.getSelectedRow(), 0))));
            
            // Chamar o método para excluir e mostrar a mensagem retornada
            String mensagem = alunoCTR.excluir(aluno);
            JOptionPane.showMessageDialog(null, mensagem);
            
            // Limpar campos e tabela
            limpaCampos();
            modelo_jtl_consultar_aluno.setNumRows(0);
            
            // Recarregar a tabela após a exclusão
            preencheTabela("");
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
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

        clienteLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        sobrenome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        telefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cidadeOrigem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cep = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        prontuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rg = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_aluno = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        pesquisa_nome = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        curso = new javax.swing.JTextField();
        periodo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        horarioIda = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        horarioVolta = new javax.swing.JTextField();

        clienteLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        clienteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clienteLabel.setText("Cadastro Aluno");

        jLabel1.setText("Nome:");

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Sobrenome");

        sobrenome.setNextFocusableComponent(telefone);

        jLabel3.setText("Telefone");

        jLabel4.setText("email:");

        email.setNextFocusableComponent(cidadeOrigem);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel5.setText("Cidade:");

        cidadeOrigem.setNextFocusableComponent(cep);

        jLabel7.setText("CEP:");

        jLabel8.setText("Prontuario");

        prontuario.setNextFocusableComponent(rg);

        jLabel9.setText("RG:");

        rg.setNextFocusableComponent(btnSalvar);

        btnNovo.setText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jtl_consultar_aluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_alunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_aluno);

        jLabel10.setText("Nome:");

        btnPesquisar.setText("OK");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cursoActionPerformed(evt);
            }
        });

        periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodoActionPerformed(evt);
            }
        });

        jLabel6.setText("Curso:");

        jLabel11.setText("Periodo:");

        jLabel12.setText("Horário de Ida:");

        horarioIda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioIdaActionPerformed(evt);
            }
        });

        jLabel13.setText("Horário de volta:");

        horarioVolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horarioVoltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(827, 827, 827)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(clienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sobrenome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cidadeOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(horarioIda, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13)
                                                .addGap(12, 12, 12)
                                                .addComponent(horarioVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(prontuario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(214, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addGap(83, 83, 83)
                        .addComponent(btnSalvar)
                        .addGap(94, 94, 94)
                        .addComponent(btnCancelar)
                        .addGap(94, 94, 94)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair)))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(clienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisa_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnSalvar)
                            .addComponent(btnExcluir)
                            .addComponent(btnCancelar)
                            .addComponent(btnSair))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cidadeOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prontuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(horarioIda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(horarioVolta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, false);
        this.SALVANDO = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (SALVANDO == 1) {
            this.gravar();
            SALVANDO = 0;
        }

        if (SALVANDO == 2) {
            this.atualizar();
            SALVANDO = 0;
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, true, true);
        this.btnPesquisarActionPerformed(evt);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_aluno.setNumRows(0);
        liberaBotoes(true, false, false, true, true);
        gravar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.preencheTabela(pesquisa_nome.getText());   
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_alunoMouseClicked
            String prontuario = String.valueOf(
            jtl_consultar_aluno.getValueAt(
                    jtl_consultar_aluno.getSelectedRow(), 0));
        
         try {
        int id = Integer.parseInt(prontuario);
        preencheCampos(id);
    } catch (NumberFormatException e) {
        System.out.println("Erro ao converter ID: " + prontuario);
    }

        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_alunoMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_aluno.setNumRows(0);
        preencheTabela("");
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cursoActionPerformed

    private void periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodoActionPerformed

    private void horarioVoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horarioVoltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horarioVoltaActionPerformed

    private void horarioIdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horarioIdaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horarioIdaActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cep;
    private javax.swing.JTextField cidadeOrigem;
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JTextField curso;
    private javax.swing.JTextField email;
    private javax.swing.JTextField horarioIda;
    private javax.swing.JTextField horarioVolta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_aluno;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField periodo;
    private javax.swing.JTextField pesquisa_nome;
    private javax.swing.JTextField prontuario;
    private javax.swing.JTextField rg;
    private javax.swing.JTextField sobrenome;
    private javax.swing.JTextField telefone;
    // End of variables declaration//GEN-END:variables
}
