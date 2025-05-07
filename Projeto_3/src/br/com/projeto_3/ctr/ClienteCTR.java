/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.ctr;

import java.sql.ResultSet;
import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.dao.ClienteDAO;
import br.com.projeto_3.dao.ConexaoDAO;

/**
 *
 * @author leonardo-teixeira
 */
public class ClienteCTR {

    ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteCTR() {
    }

    //Método para inserir cliente
    public String inserirCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (clienteDAO.inserirCliente(clienteDTO)) {
                return "Cliente Cadastrado com Sucesso!!!";
            } else {
                return "Cliente NÃO Cadastrado!!!";
            }
        }//Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que ocorreu
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Cadastrado";
        }

    }//fecha inserirCliente

    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao) {
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;

        //o atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = clienteDAO.consultarCliente(clienteDTO, opcao);

        return rs;
    }//Fecha ResultSet...

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }//Fecha método CloseDB

    public String alterarCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (clienteDAO.alterarCliente(clienteDTO)) {
                return "Cliente Alterado com Sucesso!!!";
            } else {
                return "Cliente NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
        //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Alterado!!!";
        }
    }//Fecha o método alterarCliente
    
    
    public String excluirCliente (ClienteDTO clienteDTO) {
    try {
        //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
        if (clienteDAO.excluirCliente(clienteDTO)) {
            return "Cliente Excluído com Sucesso!!!";
        } else {
            return "Cliente NÃO Excluído!!!";
        }
    } //Caso tenha algum erro no codigo acima é enviado uma mensagem no
    //console com o que esta acontecendo.
    catch (Exception e) {
        System.out.println(e.getMessage());
        return "Cliente NÃO Excluído!!!";
    }
}//Fecha o método excluirCliente

}//fecha classe 
