/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

import br.com.projeto_1.dto.PontoEmbarqueDTO;
import br.com.projeto_1.dao.ConexaoDAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PontoEmbarqueDAO {

    public List<PontoEmbarqueDTO> listarPontosPorTransporte(int idTransporte) {
        List<PontoEmbarqueDTO> lista = new ArrayList<>();
        try {
            ConexaoDAO.connectionDB();
            String sql = "SELECT * FROM ponto_embarque WHERE id_transporte = " + idTransporte;
            Statement stmt = ConexaoDAO.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PontoEmbarqueDTO p = new PontoEmbarqueDTO();
                p.setId_ponto(rs.getInt("id_ponto"));
                p.setId_transporte(rs.getInt("id_transporte"));
                p.setDescricao_ponto(rs.getString("descricao_ponto"));
                p.setCidade(rs.getString("cidade"));
                lista.add(p);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar pontos por transporte: " + e.getMessage());
        } finally {
            ConexaoDAO.closeDB();
        }
        return lista;
    }

    public List<Object[]> listarTransportesComPontos() {
        List<Object[]> lista = new ArrayList<>();
        try {
            ConexaoDAO.connectionDB();
            String sql = "SELECT t.id_transporte, t.placa_transp, t.tipo_transp, t.horario_saida, t.capacidade, p.descricao_ponto "
                    + "FROM transporte t "
                    + "LEFT JOIN ponto_embarque p ON p.id_transporte = t.id_transporte "
                    + "ORDER BY t.id_transporte";

            Statement stmt = ConexaoDAO.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_transporte"),
                    rs.getString("placa_transp"),
                    rs.getString("tipo_transp"),
                    rs.getString("horario_saida"),
                    rs.getInt("capacidade"),
                    rs.getString("descricao_ponto")
                });
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar transportes com pontos: " + e.getMessage());
        } finally {
            ConexaoDAO.closeDB();
        }

        return lista;
    }
}
