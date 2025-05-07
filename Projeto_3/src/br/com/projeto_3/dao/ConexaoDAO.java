package br.com.projeto_3.dao;

import java.sql.*;

public class ConexaoDAO {
        public static Connection con = null;

    public static void ConectDB() {
        try {
            // Dados para conectar com o banco de dados Postgres
            String dsn = "Projeto_3"; // nome do banco de dados (igual ao criado no Postgres)
            String user = "postgres"; // nome do usuário utilizado para se conectar
            String senha = "pgserver"; // senha do usuário acima informado  (Antes era postdba, porém como já tenho o pgadmin instalado, é outra senha)
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + dsn;
            
            con = DriverManager.getConnection(url, user, senha);
            
            
            con.setAutoCommit(false);
            if (con == null) {
                System.out.println("erro ao abrir o banco");
            }
        } catch (Exception e) {
            System.out.println("Problema ao abrir a base de dados! " + e.getMessage());
        }
    }
    public static void CloseDB(){
    try{
        con.close();
    }
    
    catch (Exception e){
        System.out.println("Problema ao fechar a base de dados! " + 
                e.getMessage());
    }
}
}
