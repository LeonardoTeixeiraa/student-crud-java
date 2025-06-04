/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.ctr;

import br.com.projeto_1.dao.AlunoDAO;
import br.com.projeto_1.dao.ConexaoDAO;
import br.com.projeto_1.dto.AlunoDTO;
import java.sql.ResultSet;

/**
 *
 * @author Leonardo
 */
public class AlunoCTR {

    AlunoDAO alunoDAO = new AlunoDAO();

    public String inserirAluno(AlunoDTO aluno) {
        String message = "Não foi possível inserir o cliente!";

        try {
            if (alunoDAO.inserirAluno(aluno)) {
                message = "Aluno inserido com sucesso!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = e.getMessage();
        }

        return message;

    }

    public ResultSet consultarAluno(AlunoDTO aluno, int opcao) {
        ResultSet rs = null;

        rs = alunoDAO.consultarAluno(aluno, opcao);

        return rs;
    }

    public String atualizarAluno(AlunoDTO aluno) {
        String message = "Não foi possível atualizar o aluno!";

        try {
            if (alunoDAO.atualizarAluno(aluno)) {
                message = "Aluno atualizado com sucesso!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = e.getMessage();
        }

        return message;
    }

    public String excluir(AlunoDTO alunoDTO) {
        try {
            if (alunoDAO.excluirAluno(alunoDTO)) {
                return "Aluno Excluído com Sucesso!!!";
            } else {
                return "Aluno NÃO Excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Aluno NÃO Excluído!!!";
        }
    }

    public boolean logarAluno(AlunoDTO alunoDTO) {
    try {
        return alunoDAO.loginAluno(alunoDTO);
    } catch (Exception e) {
        System.out.println("Erro no login do aluno: " + e.getMessage());
        return false;
    }
}


    public void closeDB() {
        ConexaoDAO.closeDB();
    }
;

}
