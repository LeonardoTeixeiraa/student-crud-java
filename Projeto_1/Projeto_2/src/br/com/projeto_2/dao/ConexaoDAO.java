/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_2.dao;
import java.sql.*;

/**
 *
 * @author leonardo
 */
public class ConexaoDAO {
   
    public static Connection con = null;

    public ConexaoDAO(){
        
    }
    
    public static void ConnectDB(){
        try{
            String dsn = "projeto_2";// nome do banco de dados (Igual criado ao postgres)
            String user = "postgres";// nome do tipo de banco, no caso o postgres
            String senha = "postdba";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/"+dsn;
            
            con = DriverManager.getConnection(url, user, senha);
            
            con.setAutoCommit(false);
            if(con == null){
                System.out.println("Erro ao abrir o banco de dados");
            }
        }//fecha o try
        //Caso ocorra problema para abrir o banco de dados é emitido a mensagem no console.
        catch(Exception e){
            System.out.println("Problema ao abrir o banco de dados!"+
                    e.getMessage());
        }//Fecha o catch
    }//Fecha o método ConnectDB
    
    public static void CloseDB(){
        try{
            con.close();
        }//fecha o try
        //caso ocorra problemas para fechar o banco de dados é emitido a menagem no console.
    catch (Exception e){
        System.out.println("Problema ao fechar o banco de dados!" +
                e.getMessage());
    }//fecha o catch
    }//Fecha o método ColseDB
}//fecha a classe conexaoDAO

