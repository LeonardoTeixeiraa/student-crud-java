/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;
import br.com.projeto_1.dto.PontoEmbarqueDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author leonardo-teixeira
 */
public class PontoEmbarqueDAO {
    public List<PontoEmbarqueDTO> listarPontosPorTransporte(int idTransporte) {
        List<PontoEmbarqueDTO> lista = new ArrayList<>();
        try {
            ConexaoDAO.connectionDB();
            String sql = "SELECT * FROM Ponto_Embarque WHERE id_transporte = " + idTransporte;
            Statement stmt = ConexaoDAO.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PontoEmbarqueDTO p = new PontoEmbarqueDTO();
                p.setId_ponto(rs.getInt("id_ponto"));
                p.setId_transporte(rs.getInt("id_transporte"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            ConexaoDAO.closeDB();
        }
        return lista;
    }
}
