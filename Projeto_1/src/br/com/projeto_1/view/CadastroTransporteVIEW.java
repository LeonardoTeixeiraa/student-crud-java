/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_1.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import br.com.projeto_1.dto.TransporteDTO;
import br.com.projeto_1.ctr.TransporteCTR;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leonardo-teixeira
 */
public class CadastroTransporteVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroTransporte
     */
    TransporteCTR transporteCTR = new TransporteCTR();
    TransporteDTO transporteDTO = new TransporteDTO();

    int gravar_alterar; // Variável usada para saber se esta alterando ou incluindo.
    ResultSet rs; // Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_transporte; // Variável para guardar o modelo da tabela

    public CadastroTransporteVIEW() {
        initComponents();

        // Chama todos os métodos liberaCampos
        liberaCampos(false);
        // Chama todos os métodos limpaCampos
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_transporte = (DefaultTableModel) jtl_consultar_transp.getModel();

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();

        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void liberaCampos(boolean a) {
        TipoVeiculo.setEnabled(a);
        Placa.setEnabled(a);
        Capacidade.setEnabled(a);
        HorarioSaida.setEnabled(a);
    }

    private void limpaCampos() {
        TipoVeiculo.setText("");
        Placa.setText("");
        HorarioSaida.setText("");
        Capacidade.setText("");
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
            transporteDTO.setTipo_transp(TipoVeiculo.getText());
            transporteDTO.setPlaca_transp(Placa.getText());
            String capacidadeTexto = Capacidade.getText();
            if (capacidadeTexto == null || capacidadeTexto.trim().isEmpty()) {
                transporteDTO.setCapacidade(0); // ou algum valor padrão, ou lançar uma exceção customizada
            } else {
                transporteDTO.setCapacidade(Integer.parseInt(capacidadeTexto));
            }

            transporteDTO.setHorario_saida(HorarioSaida.getText());

            JOptionPane.showMessageDialog(null,
                    transporteCTR.inserirTransporte(transporteDTO));
        } catch (Exception e) {
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }// Fecha o método gravar

    private void preencheTabela(String placa_transp) {
        try {
            modelo_jtl_consultar_transporte.setNumRows(0);
            transporteDTO.setPlaca_transp(placa_transp);
            rs = transporteCTR.consultarTransporte(transporteDTO, 1); // 1 = pesquisa por tipo
            while (rs.next()) {
                modelo_jtl_consultar_transporte.addRow(new Object[]{
                    rs.getString("placa_transp"),
                    rs.getString("tipo_transp")
                });
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        } finally {
            transporteCTR.CloseDB();
        }
    }

    private void preencheCampos(String Placa) {
        try {
            transporteDTO.setPlaca_transp(Placa);
            rs = transporteCTR.consultarTransporte(transporteDTO, 2); // 2 = pesquisa pela placa
            if (rs.next()) {
                limpaCampos();
                TipoVeiculo.setText(rs.getString("tipo_transp"));
                this.Placa.setText(rs.getString("placa_transp"));
                HorarioSaida.setText(rs.getString("horario_saida"));
                Capacidade.setText(rs.getString("capacidade"));
                liberaCampos(true);
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        } finally {
            transporteCTR.CloseDB();
        }
    }

    private void alterar() {
        try {
            transporteDTO.setTipo_transp(TipoVeiculo.getText());
            transporteDTO.setPlaca_transp(Placa.getText());
            transporteDTO.setHorario_saida(HorarioSaida.getText());
            String capacidadeTexto = Capacidade.getText();
            if (capacidadeTexto == null || capacidadeTexto.trim().isEmpty()) {
                transporteDTO.setCapacidade(0);
            } else {
                transporteDTO.setCapacidade(Integer.parseInt(capacidadeTexto));
            }

            JOptionPane.showMessageDialog(null,
                    transporteCTR.alterarTransporte(transporteDTO));
        } catch (Exception e) {
            System.out.println("Erro ao Alterar Transporte: " + e.getMessage());
        }
    }

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Transporte?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    transporteCTR.excluirTransporte(transporteDTO));
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TipoVeiculo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Placa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        HorarioSaida = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Capacidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_transp = new javax.swing.JTable();
        pesquisa_nome_transp = new javax.swing.JTextField();
        btnPesquisarTransporte = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        jLabel2.setText("Cadastro de Transportes");

        jLabel1.setText("Tipo do Veículo:");

        TipoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoVeiculoActionPerformed(evt);
            }
        });

        jLabel3.setText("Placa:");

        jLabel4.setText("Horário de saída:");

        jLabel5.setText("Capacidade:");

        Capacidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapacidadeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel7.setText("Lista de Transportes");

        jLabel6.setText("Placa:");

        jtl_consultar_transp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tipo"
            }
        ));
        jtl_consultar_transp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_transpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_transp);

        btnPesquisarTransporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/3844432_magnifier_search_zoom_icon(2).png"))); // NOI18N
        btnPesquisarTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarTransporteActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/1564491_add_create_new_plus_icon(1).png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(0, 153, 0));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/2639912_save_icon(1).png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/9004772_cross_delete_cancel_remove_icon.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(255, 0, 0));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/8664938_trash_can_delete_remove_icon.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/9104334_sign out_logout_exit_out_icon(1).png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNovo)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(18, 18, 18)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Placa, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Capacidade, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(HorarioSaida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(pesquisa_nome_transp, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(83, 83, 83))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(293, 293, 293))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HorarioSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Capacidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(pesquisa_nome_transp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnPesquisarTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair))
                .addGap(82, 82, 82))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CapacidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapacidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapacidadeActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void TipoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoVeiculoActionPerformed

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

    private void btnPesquisarTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarTransporteActionPerformed
        // TODO add your handling code here:
        preencheTabela(pesquisa_nome_transp.getText());
    }//GEN-LAST:event_btnPesquisarTransporteActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_transporte.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose(); // Fecha a janela interna
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_transporte.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jtl_consultar_transpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_transpMouseClicked
        // TODO add your handling code here:
        String placaSelecionada = String.valueOf(
                jtl_consultar_transp.getValueAt(
                        jtl_consultar_transp.getSelectedRow(), 0));

        preencheCampos(placaSelecionada);

        liberaBotoes(false, true, true, true, true);

        liberaCampos(true);

        gravar_alterar = 2;
    }//GEN-LAST:event_jtl_consultar_transpMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Capacidade;
    private javax.swing.JTextField HorarioSaida;
    private javax.swing.JTextField Placa;
    private javax.swing.JTextField TipoVeiculo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarTransporte;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_transp;
    private javax.swing.JTextField pesquisa_nome_transp;
    // End of variables declaration//GEN-END:variables
}
