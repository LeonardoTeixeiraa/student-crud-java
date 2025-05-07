/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_3.ctr;

import java.sql.ResultSet;
import br.com.projeto_3.dto.FornecedorDTO;
import br.com.projeto_3.dto.ProdutoDTO;
import br.com.projeto_3.dao.ProdutoDAO;
import br.com.projeto_3.dao.ConexaoDAO;

/**
 *
 * @author leonardo-teixeira
 */
public class ProdutoCTR {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    //Método contrutor da classe
    public ProdutoCTR() {
    }

     //InserirProduto
    public String inserirProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO) {
        try {
            // Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (produtoDAO.inserirProduto(produtoDTO, fornecedorDTO)) {
                return "Produto Cadastrado com Sucesso!!!";
            } else {
                return "Produto NÃO Cadastrado!!!";
            }
        } // Caso tenha algum erro no codigo acima é enviado uma mensagem no
        // console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Produto NÃO Cadastrado";
        }
    } // Fecha o método inserirProduto
    
     public String alterarProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO) {
        try {
            // Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (produtoDAO.alterarProduto(produtoDTO, fornecedorDTO)) {
                return "Produto Alterado com Sucesso!!!";
            } else {
                return "Produto NÃO Alterado!!!";
            }
        } // Caso tenha algum erro no codigo acima é enviado uma mensagem no
        // console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Produto NÃO Alterado!!!";
        }
    } // Fecha o método alterarProduto

      
     //Excluir Produto
    public String excluirProduto(ProdutoDTO produtoDTO) {

        try {
            // Chama o metodo que esta na classe DAO aguardando uma resposta ( true ou false )
            if (produtoDAO.excluirProduto(produtoDTO)) {
                return " Produto Excluído com Sucesso !!! ";
            } else {
                return " Produto NÃO Excluído !!! ";
            }
        } // Caso tenha algum erro no codigo acima é enviado uma mensagem no
        // console com o que esta acontecendo .
        catch (Exception e) {
            System.out.println(e.getMessage());
            return " Produto NÃO Excluído !!! ";
        }
    } // Fecha o método excluirProduto

   //Consultar Produto
    public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opcao) {
        // É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;
        // O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = produtoDAO.consultarProduto(produtoDTO, opcao);
        return rs;
    } // Fecha o método consultarproduto

    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}
