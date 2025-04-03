/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_2.dao;
import br.com.projeto_2.dto.FornecedorDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author leonardo
 */
public class FornecedorDAO {
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    //Atibuto do tipo ResultSet utilizado para realizar consultas;
    //Atributo do tipo resultset utilizando para realizar consultas
    private ResultSet rs = null;
    //manipular o banco de dados
    private Statement stmt = null;

    public boolean inserirFornecedor(FornecedorDTO fornecedorDTO){
        //chama o metodo que esta na classe conexaoDAO
        try{
        ConexaoDAO.ConnectDB();
        
        stmt = ConexaoDAO.con.createStatement();
        
        String comando = "Insert into fornecedor (nome_for, cnpj_for, tel_for, "
                +"data_cad_for) values ( "
                +"'"+fornecedorDTO.getNome_for()+"', "
                +"'"+fornecedorDTO.getCnpj_for()+"', "
                +"'"+fornecedorDTO.getTel_for()+"', "
                +"to_date('" + data_format.format(fornecedorDTO.getData_cad_for())+"','dd/mm/yyyy')) ";
        
        //Executa o comando no banco de dados
        stmt.execute(comando.toUpperCase());
            
            //Da um commit (Registrar) no bando de dados
            ConexaoDAO.con.commit();
            //fecha o statement
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    } 
    
    public ResultSet consultarFornecedor(FornecedorDTO fornecedorDTO, int opcao){

    try{
        //chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
        ConexaoDAO.ConnectDB();
        //Cria o Statement que responsavel por executar alguma coisa no banco de dados
        stmt = ConexaoDAO.con.createStatement();
        //Comando SQL
        String comando = "";
        switch(opcao){
            case 1:
                comando = "Select f.id_for, f.nome_for "
                        + "from fornecedor f "
                        + "where f.nome_for like '"+fornecedorDTO.getNome_for()+"%' "
                        + "order by f.nome_for";
            break;
            case 2:
                comando = "Select f.nome_for, f.cnpj_for, f.tel_for, "
                        + "to_char(f.data_cad_for, 'dd/mm/yyyy') as data_cad_for "
                        + "from fornecedor f "
                        + "where f.id_for = "+fornecedorDTO.getId_for();
            break;
            }
        //execute o comando SQL no banco de dados;
        rs = stmt.executeQuery(comando.toUpperCase());
        return rs;
    }
    catch (Exception e){
        System.out.println(e.getMessage());
        return rs;
    }
    }
     
    public boolean alterarFornecedor(FornecedorDTO fornecedorDTO){
        //chama o metodo que esta na classe conexaoDAO
        try{
        ConexaoDAO.ConnectDB();
        
         stmt = ConexaoDAO.con.createStatement();
        
        String comando = "Update fornecedor set "
                + "nome_for = '"+fornecedorDTO.getNome_for()+"', "
                + "cnpj_for = '"+fornecedorDTO.getCnpj_for()+"', "
                + "tel_for = '"+fornecedorDTO.getTel_for()+"', "
                + "data_cad_for = to_date('"+data_format.format(fornecedorDTO.getData_cad_for())+"','dd/mm/yyyy') "
                + "where id_for = "+fornecedorDTO.getId_for();
        
        //Executa o comando no banco de dados
        stmt.execute(comando.toUpperCase());
            
            //Da um commit (Registrar) no bando de dados
            ConexaoDAO.con.commit();
            //fecha o statement
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirFornecedor(FornecedorDTO fornecedorDTO){
        //chama o metodo que esta na classe conexaoDAO
        try{
        ConexaoDAO.ConnectDB();
        
        stmt = ConexaoDAO.con.createStatement();
        
        String comando = "Delete from fornecedor where id_for = "+fornecedorDTO.getId_for();
        
        //Executa o comando no banco de dados
        stmt.execute(comando);
            
            //Da um commit (Registrar) no bando de dados
            ConexaoDAO.con.commit();
            //fecha o statement
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
}
