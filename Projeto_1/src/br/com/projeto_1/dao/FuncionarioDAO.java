package br.com.projeto_1.dao;

import java.sql.*;

import br.com.projeto_1.dto.FuncionarioDTO;
import br.com.projeto_1.dto.MotoristaDTO;

public class FuncionarioDAO {

    private ResultSet rs = null;
    private Statement stmt = null;

   public boolean inserirFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        ConexaoDAO.connectionDB();
        stmt = ConexaoDAO.conn.createStatement();

        // 1. Inserir na tabela funcionario
        String comandoFuncionario = "INSERT INTO funcionario (nome_fun, cpf_fun, login_fun, senha_fun, cargo_fun) VALUES ("
                + "'" + funcionarioDTO.getNome_fun() + "', "
                + "'" + funcionarioDTO.getCpf_fun() + "', "
                + "'" + funcionarioDTO.getLogin_fun() + "', "
                + "crypt('" + funcionarioDTO.getSenha_fun() + "', gen_salt('bf', 8)), "
                + "'" + funcionarioDTO.getCargo_fun() + "') RETURNING id_fun";

        // Executa e obtém o id do funcionário inserido
        ResultSet rs = stmt.executeQuery(comandoFuncionario);
        int idFuncionario = -1;
        if (rs.next()) {
            idFuncionario = rs.getInt("id_fun");
        }

        // 2. Se o DTO for um motorista, insere na tabela motorista
        if (funcionarioDTO instanceof MotoristaDTO ) {
            MotoristaDTO motoristaDTO = (MotoristaDTO) funcionarioDTO;
            String comandoMotorista = "INSERT INTO motorista (id_fun, cnh_fun) VALUES ("
                    + idFuncionario + ", "
                    + "'" + motoristaDTO.getCnh()+ "')";
            stmt.execute(comandoMotorista);
        }

        ConexaoDAO.conn.commit();
        stmt.close();
        return true;

    } catch (Exception e) {
        System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        return false;
    } finally {
        ConexaoDAO.closeDB();
    }
}

public boolean alterarFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        ConexaoDAO.connectionDB();
        stmt = ConexaoDAO.conn.createStatement();

        String comando;

        if (funcionarioDTO.getSenha_fun() == null || funcionarioDTO.getSenha_fun().isEmpty()) {
            comando = "UPDATE funcionario SET "
                    + "nome_fun = '" + funcionarioDTO.getNome_fun() + "', "
                    + "cpf_fun = '" + funcionarioDTO.getCpf_fun() + "', "
                    + "login_fun = '" + funcionarioDTO.getLogin_fun() + "', "
                    + "cargo_fun = '" + funcionarioDTO.getCargo_fun() + "'";
        } else {
            comando = "UPDATE funcionario SET "
                    + "nome_fun = '" + funcionarioDTO.getNome_fun() + "', "
                    + "cpf_fun = '" + funcionarioDTO.getCpf_fun() + "', "
                    + "login_fun = '" + funcionarioDTO.getLogin_fun() + "', "
                    + "senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "', gen_salt('bf', 8)), "
                    + "cargo_fun = '" + funcionarioDTO.getCargo_fun() + "'";
        }

        comando += " WHERE id_fun = " + funcionarioDTO.getId_fun();
        stmt.execute(comando);

        // Atualiza CNH apenas se for motorista
        if (funcionarioDTO instanceof MotoristaDTO) {
            MotoristaDTO motoristaDTO = (MotoristaDTO) funcionarioDTO;
            String comandoMotorista = "UPDATE motorista SET cnh= '" + motoristaDTO.getCnh() + "' "
                                    + "WHERE id_fun= " + funcionarioDTO.getId_fun();
            stmt.execute(comandoMotorista);
        }

        ConexaoDAO.conn.commit();
        stmt.close();

        return true;

    } catch (Exception e) {
        System.out.println("Erro ao alterar funcionário: " + e.getMessage());
        return false;
    } finally {
        ConexaoDAO.closeDB();
    }
}

    public boolean excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "DELETE FROM funcionario WHERE id_fun = " + funcionarioDTO.getId_fun();

            stmt.execute(comando);
            ConexaoDAO.conn.commit();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDB();
        }
    }

    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "";

            switch (opcao) {
                case 1:
                    comando = "SELECT f.id_fun, f.nome_fun FROM funcionario f "
                            + "WHERE f.nome_fun ILIKE '" + funcionarioDTO.getNome_fun() + "%' "
                            + "ORDER BY f.nome_fun";
                    break;
                case 2:
                    comando = "SELECT * FROM funcionario WHERE id_fun = " + funcionarioDTO.getId_fun();
                    break;
            }

            rs = stmt.executeQuery(comando);
            return rs;

        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionário: " + e.getMessage());
            return rs;
        }
    }

    public String loginFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "SELECT f.id_fun, f.nome_fun, f.cargo_fun FROM funcionario f "
                    + "WHERE f.login_fun = '" + funcionarioDTO.getLogin_fun() + "' "
                    + "AND f.senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "', f.senha_fun)";

            rs = stmt.executeQuery(comando);

            if (rs.next()) {
                return rs.getString("cargo_fun"); 
            }
            return null;

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
            return null;
        } finally {
            ConexaoDAO.closeDB();
        }
    }
}
