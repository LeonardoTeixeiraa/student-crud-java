package br.com.projeto_3.dao;

import java.sql.ResultSet;
import java.sql.Statement;

import br.com.projeto_3.dto.FornecedorDTO;
import br.com.projeto_3.dto.FuncionarioDTO;

public class FuncionarioDAO {

    public FuncionarioDAO() {
    }

    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Instancia o Statement que sera responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados

            String comando = "Insert into funcionario (nome_fun, cpf_fun"
                    + "login_fun,senha_fun, tipo_fun) values ( "
                    + "'" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "'" + funcionarioDTO.getCpf_fun() + "', "
                    + "'" + funcionarioDTO.getLogin_fun() + "', "
                    + "crypt('" + funcionarioDTO.getSenha_fun() + "',gen_salt('bf' , 8)) , "
                    + "'" + funcionarioDTO.getTipo_fun().toUpperCase() + "') ";

//Executa o comando SQL no banco de Dados
            stmt.execute(comando.toUpperCase());
//Da um commit no banco de dados
            ConexaoDAO.con.commit();
//Fecha o statement
            stmt.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
        //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }// Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAo para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }

    public boolean alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "Update funcionario set "
                    + "nome_fun = '" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "cpf_fun = '" + funcionarioDTO.getCpf_fun() + "', "
                    + "login_fun = '" + funcionarioDTO.getLogin_fun() + "', ";

            if (funcionarioDTO.getSenha_fun() != null) {
                comando += "senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "',gen_salt('bf', 8)), ";
            }

            comando += "tipo_fun = " + funcionarioDTO.getTipo_fun().toUpperCase() + "' "
                    + "where id_fun = " + funcionarioDTO.getId_fun();

            // Executa o comando SQL no banco de dados
            stmt.execute(comando.toUpperCase());

            // Da um commit no banco de dados
            ConexaoDAO.con.commit();

            // Fecha o statement
            stmt.close();

            return true;

        } // Caso tenha algum erro no código acima é enviado uma mensagem no
        // console com o que está acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } // Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            // Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }// Fecha o método alterarFornecedor
    public boolean excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Instancia o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "Delete from funcionario where id_fun = "
                    + funcionarioDTO.getId_fun();

            // Executa o comando SQL no banco de Dados
            stmt.execute(comando);

            // Da um commit no banco de dados
            ConexaoDAO.con.commit();

            // Fecha o statement
            stmt.close();

            return true;

        } // Caso tenha algum erro no código acima é enviado uma mensagem no
        // console com o que está acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } // Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            // Chama o método da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }// Fecha o método excluirFornecedor

    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "";

            switch (opcao) {
                case 1:
                    comando = " Select f.* "
                            + " from funcionario f "
                            + " where nome_fun ilike '" + funcionarioDTO.getNome_fun() + "%' "
                            + " order by f.nome_fun";
                    break;
                case 2:
                    comando = " Select f.* "
                            + " from fornecedor f "
                            + " where f.id_fun = " + funcionarioDTO.getId_fun();
                    break;
            }
            // Executa o comando SQL no banco de Dados
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;

        } // Caso tenha algum erro no codigo acima é enviado uma mensagem no
        // console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs; // Nota: Retornar rs aqui pode ser problemático se ele não foi inicializado ou se a conexão falhou antes.
        }
    } // Fecha o método consultarFornecedor

    public String logarFuncionario(FuncionarioDTO funcionarioDTO){
        try {
            ConexaoDAO.ConectDB();
            stmt =  ConexaoDAO.con.createStatement();

            String  comando = " Select f.tipo_fun " +
                    "from Funcionario f " +
                    "where f.login_fun = " + funcionarioDTO.getLogin_fun() + "'" +
                    " and f.senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "', senha_fun)";
            rs = null;
            rs = stmt.executeQuery(comando);
            if (rs.next()){
                return rs.getString("tipo_fun");
            }
            else {
                return "";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }

}

