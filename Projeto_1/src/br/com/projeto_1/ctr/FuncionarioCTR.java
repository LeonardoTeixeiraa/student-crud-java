package br.com.projeto_1.ctr;

import java.sql.ResultSet;

import br.com.projeto_1.dao.FuncionarioDAO;
import br.com.projeto_1.dao.ConexaoDAO;
import br.com.projeto_1.dto.FuncionarioDTO;

public class FuncionarioCTR {

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Funcionario cadastrado com Sucesso!!!";
            }
            return "Funcionario NÃO cadastrado!!!";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario NÃO cadastrado";
        }

    }

    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        ResultSet rs = null;

        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);

        return rs;
    }

    public void CloseDB() {
        ConexaoDAO.closeDB();
    }

    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionario alterado com sucesso!!!";
            }
            return "Funcionario NÃO alterado!!!";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario NÃO alterado!!!";
        }
    }

    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionario excluído com sucesso!!!";
            }
            return "Funcionario NÃO excluído!!!";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario NÃO excluído!!!";
        }
    }

    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            String tipo = funcionarioDAO.loginFuncionario(funcionarioDTO);

            return tipo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
