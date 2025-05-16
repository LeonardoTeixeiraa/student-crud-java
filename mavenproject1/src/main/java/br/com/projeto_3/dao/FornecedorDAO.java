/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.dao;

import java.sql.*;
import br.com.projeto_3.dto.FornecedorDTO;
import java.text.SimpleDateFormat;

/**
 *
 * @author leonardo-teixeira
 */
public class FornecedorDAO {

    public FornecedorDAO() {
    }

    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    // Atributo do tipo ResultSet para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;

    //Método para inserir o objeto fornecedorDTO no banco de dados
    public boolean inserirFornecedor(FornecedorDTO fornecedorDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            // Comando SQL que será executado no banco de dados

            String comando = "insert into fornecedor (nome_for, cnpj_for, "
                    + "tel_for, data_cad_for ) values ( "
                    + "'" + fornecedorDTO.getNome_for() + "', "
                    + "'" + fornecedorDTO.getCnpj_for() + "', "
                    + "'" + fornecedorDTO.getTel_for() + "', "
                    + "to_date('" + data_format.format(fornecedorDTO.getData_cad_for()) + "','dd/mm/yyyy')) ";

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
        }//Independente de dar erro ou não ele vai fechar o BD
        finally {
            //Chama o método da classe ConexaoDAO para fechar o BD
            ConexaoDAO.CloseDB();
        }
    }//Fecha inserirFornecedor;

    /**
     * Método utilizado para alterar um objeto fornecedorDTO no banco de dados
     *
     * @param fornecedorDTO, que vem da classe FornecedorCTR
     * @return Um boolean
     */
    public boolean alterarFornecedor(FornecedorDTO fornecedorDTO) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "Update fornecedor set "
                    + "nome_for = '" + fornecedorDTO.getNome_for() + "', "
                    + "cnpj_for = '" + fornecedorDTO.getCnpj_for() + "', "
                    + "tel_for = '" + fornecedorDTO.getTel_for() + "', "
                    + "data_cad_for = to_date('" + data_format.format(fornecedorDTO.getData_cad_for()) + "','dd/mm/yyyy') "
                    + "where id_for = " + fornecedorDTO.getId_for();

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

    /**
     * Método utilizado para excluir um objeto fornecedorDTO no banco de dados
     *
     * @param fornecedorDTO que vem da classe FornecedorCTR
     * @return Um boolean
     */
    public boolean excluirFornecedor(FornecedorDTO fornecedorDTO) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Instancia o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "Delete from fornecedor where id_for = "
                    + fornecedorDTO.getId_for();

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

    /**
     * Método utilizado para consultar um objeto fornecedorDTO no banco de dados
     *
     * @param fornecedorDTO, que vem da classe FornecedorCTR
     * @param opcao, que vem da classe FornecedorCTR
     * @return Um ResultSet com os dados do fornecedor
     */
    public ResultSet consultarFornecedor(FornecedorDTO fornecedorDTO, int opcao) {
        try {
            // Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            // Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();

            // Comando SQL que sera executado no banco de dados
            String comando = "";

            switch (opcao) {
                case 1:
                    comando = " Select f.id_for, f.nome_for "
                            + " from fornecedor f "
                            + " where f.nome_for ilike '" + fornecedorDTO.getNome_for() + "%' "
                            + " order by f.nome_for";
                    break;
                case 2:
                    comando = " Select f.nome_for, f.cnpj_for, f.tel_for, "
                            + " to_char(f.data_cad_for, 'dd/mm/YYYY') as data_cad_for "
                            + " from fornecedor f "
                            + " where f.id_for = " + fornecedorDTO.getId_for();
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

}
