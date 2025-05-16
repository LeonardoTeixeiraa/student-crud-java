/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.dao;

import java.sql.*;
import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.dto.ClienteDTO;

/**
 *
 * @author leonardo-teixeira
 */
public class ClienteDAO {

    public ClienteDAO() {
    }
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    // método de inserção de dados Cliente
    public boolean inserirCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o método que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Instancia o Statemente que sera responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            //Comando SQL que sera executado no banco de dados
            String comando = "Insert into cliente (nome, logradouro, numero, "
                    + "bairro, cidade, estado, cep, cpf, rg) values ( "
                    + "'" + clienteDTO.getNome_cli() + "', "
                    + "'" + clienteDTO.getLogradouro_cli() + "', "
                    + clienteDTO.getNumero_cli() + ", "
                    + "'" + clienteDTO.getBairro_cli() + "', "
                    + "'" + clienteDTO.getCidade_cli() + "', "
                    + "'" + clienteDTO.getEstado_cli() + "', "
                    + "'" + clienteDTO.getCep_cli() + "', "
                    + "'" + clienteDTO.getCpf_cli() + "', "
                    + "'" + clienteDTO.getRg_cli() + "') ";

//Executa o comando SQL no banco de Dados
            stmt.execute(comando.toUpperCase());
//Da um commit no banco de dados
            ConexaoDAO.con.commit();
//fecha o statement
            stmt.close();
            return true;

        }//Caso tenha algum erro no código acima é enviado uma mensagem no Console com o que está ocorrendo
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }//Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados.
            ConexaoDAO.CloseDB();
        }
    }

    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao) {
        try {
            // Chama o método que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "";

            switch (opcao) {
                case 1:
                    comando = "Select c.* "
                            + "from cliente c "
                            + "where nome like '" + clienteDTO.getNome_cli() + "%' "
                            + "order by c.nome";
                    break;
                case 2:
                    comando = "Select c.* "
                            + "from cliente c "
                            + "where c.id_cli = " + clienteDTO.getId_cli();
                    break;
                case 3:
                    comando = "Select c.id_cli, c.nome "
                            + "from cliente c ";
                    break;
            }
            //Executa o comando SQL no banco de dados
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }//Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que ocorreu
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }//Fecha consultarCliente;

    public boolean alterarCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o método que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsável por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update cliente set "
                    + "nome = '" + clienteDTO.getNome_cli() + "', "
                    + "logradouro = '" + clienteDTO.getLogradouro_cli() + "', "
                    + "numero = " + clienteDTO.getNumero_cli() + ", "
                    + "bairro = '" + clienteDTO.getBairro_cli() + "', "
                    + "cidade = '" + clienteDTO.getCidade_cli() + "', "
                    + "estado = '" + clienteDTO.getEstado_cli() + "', "
                    + "cep = '" + clienteDTO.getCep_cli() + "', "
                    + "cpf = '" + clienteDTO.getCpf_cli() + "', "
                    + "rg = '" + clienteDTO.getRg_cli() + "' "
                    + "where id_cli = " + clienteDTO.getId_cli();

            // Executa o comando SQL no banco de Dados  
            stmt.execute(comando.toUpperCase());
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem
        //no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método alterarCliente

    /**
     * Método utilizado para excluir um objeto clienteDTO no banco de dados
     *
     * @param clienteDTO que vem da classe ClienteCTR
     * @return Um boolean
     */
    public boolean excluirCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Instancia o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "Delete from cliente where id_cli = "
                    + clienteDTO.getId_cli();
            //Executa o comando SQL no banco de Dados
            stmt.execute(comando);
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
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método excluirCliente

}//Fecha classe ClienteDAO

