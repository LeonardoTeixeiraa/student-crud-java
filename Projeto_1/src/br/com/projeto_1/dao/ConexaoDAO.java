/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

import java.sql.*;

/**
 *
 * @author Leonardo
 */
public class ConexaoDAO {
    public static Connection conn = null;
    public static void connectionDB(){
        try{
            String db = "projetoCadastroDeAlunos";
            String user = "postgres";
            String password = "postdba";
            
             DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + db;
            
            conn = DriverManager.getConnection(url, user, password);
            if(conn == null){
                System.out.println("Erro ao se conectar ao banco, conn = null");
                return;
            }
            conn.setAutoCommit(false);

        }catch(SQLException e){
            System.out.println("Erro ao se conectar ao banco: " + e.getMessage());
        }
    }
    
    public static void closeDB(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println("Erro ao finalizar conexão com o banco de dados: " + e.getMessage());
        }
    }
}
