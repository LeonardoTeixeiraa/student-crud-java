/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dto;
import java.util.Date;
/**
 *
 * @author leonardo-teixeira
 */
public class InscricaoDTO {
    private String id_aluno, id_transporte;
    private Date data_inscricao;
    private Boolean status_confirmacao;

    public String getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(String id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getId_transporte() {
        return id_transporte;
    }

    public void setId_transporte(String id_transporte) {
        this.id_transporte = id_transporte;
    }

    public Date getData_inscricao() {
        return data_inscricao;
    }

    public void setData_inscricao(Date data_inscricao) {
        this.data_inscricao = data_inscricao;
    }

    public Boolean getStatus_confirmacao() {
        return status_confirmacao;
    }

    public void setStatus_confirmacao(Boolean status_confirmacao) {
        this.status_confirmacao = status_confirmacao;
    }
}

