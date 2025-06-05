/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;
import br.com.projeto_1.dto.InscricaoDTO;
import java.sql.Statement;
/**
 *
 * @author leonardo-teixeira
 */
public class InscricaoDAO {
    public boolean registrarInscricao(InscricaoDTO inscricaoDTO) {
    try {
        ConexaoDAO.connectionDB();
        Statement stmt = ConexaoDAO.conn.createStatement();

        String sql = "INSERT INTO inscricao (prontuario, id_transporte) VALUES ('"
                + inscricaoDTO.getProntuario() + "', " + inscricaoDTO.getId_transporte() + ")";

        stmt.executeUpdate(sql);
        ConexaoDAO.conn.commit();
        stmt.close();

        return true;
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
        return false;
    } finally {
        ConexaoDAO.closeDB();
    }
}
}
