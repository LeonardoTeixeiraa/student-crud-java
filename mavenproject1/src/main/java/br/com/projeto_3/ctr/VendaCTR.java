/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.ctr;

import br.com.projeto_3.dao.ConexaoDAO;
import br.com.projeto_3.dao.VendaDAO;
import br.com.projeto_3.dto.VendaDTO;
import br.com.projeto_3.dto.ClienteDTO;
import javax.swing.JTable;

/**
 *
 * @author leonardo-teixeira
 */
public class VendaCTR {

    VendaDAO vendaDAO = new VendaDAO();

    //Construtor
    public VendaCTR() {

    }

    /**
     * Método utilizado para controlar o acesso ao método inserirVenda da classe
     * VendaDAO
     *
     * @param vendaDTO, que vem da classe da página (VIEW)
     * @param clienteDTO, que vem da classe da página (VIEW)
     * @param produtos, que vem da classe da página (VIEW)
     * @return String contendo a mensagem
     */
    public String inserirVenda(VendaDTO vendaDTO, ClienteDTO clienteDTO, JTable produtos) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (vendaDAO.inserirVenda(vendaDTO, clienteDTO, produtos)) {
                return "Venda Cadastrada com Sucesso!!!";
            } else {
                return "Venda NÃO Cadastrada!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem
        //no console com o que aconteceu.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Venda NÃO Cadastrada";
        }
    } //Fecha o método inserirVenda
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}//fecha classe
