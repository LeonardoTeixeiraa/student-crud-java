/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.dao;

import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.dto.VendaDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

/**
 *
 * @author leonardo-teixeira
 */
public class VendaDAO {

    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
//Manipular o banco de dados
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    //Construtor
    public VendaDAO() {
    }

    /**
     * Método utilizado para inserir um objeto VendaDTO no banco de dados
     *
     * @param vendaDTO, que vem da classe da página (VIEW)
     * @param clienteDTO, que vem da classe da página (VIEW)
     * @param produtos, que vem da classe da página (VIEW)
     * @return Um boolean
     */
    public boolean inserirVenda(VendaDTO vendaDTO, ClienteDTO clienteDTO, JTable produtos) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();

            //Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            String comando1 = "Insert into Venda (dat_vend, val_vend, "
                    + "id_cli) values ("
                    + "to_date('" + date.format(vendaDTO.getDat_vend()) + "', 'DD/MM/YYYY'), "
                    + vendaDTO.getVal_vend() + ", "
                    + clienteDTO.getId_cli() + ");";

            //Executa o comando SQL no banco de Dados
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            for (int cont = 0; cont < produtos.getRowCount(); cont++) {
                String comando2 = "Insert into produto_venda (id_vend, id_prod, "
                        + "val_prod, qtd_prod) values ("
                        + rs.getInt("id_vend") + ", "
                        + produtos.getValueAt(cont, 0) + ", "
                        + produtos.getValueAt(cont, 2) + ", "
                        + produtos.getValueAt(cont, 3) + ");";

                stmt1.execute(comando2);
            }
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            stmt1.close();
            rs.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    } //Fecha o método inserirVenda

    
    
    
    
}//fecha classe
