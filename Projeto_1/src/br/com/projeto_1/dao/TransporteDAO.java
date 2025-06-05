/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

/**
 *
 * @author leonardo-teixeira
 */

import java.sql.ResultSet;
import java.sql.Statement;
import br.com.projeto_1.dto.TransporteDTO;
import java.util.ArrayList;
import java.util.List;

public class TransporteDAO {

    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirTransporte(TransporteDTO transporteDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "INSERT INTO transporte (placa_transp, tipo_transp, horario_saida, capacidade) VALUES ("
                    + "'" + transporteDTO.getPlaca_transp() + "', "
                    + "'" + transporteDTO.getTipo_transp() + "', "
                    + "'" + transporteDTO.getHorario_saida() + "', "
                    + transporteDTO.getCapacidade() + ")";

            stmt.execute(comando);
            ConexaoDAO.conn.commit();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDB();
        }
    }

    public boolean alterarTransporte(TransporteDTO transporteDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "UPDATE transporte SET "
                    + "placa_transp = '" + transporteDTO.getPlaca_transp() + "', "
                    + "tipo_transp = '" + transporteDTO.getTipo_transp() + "', "
                    + "horario_saida = '" + transporteDTO.getHorario_saida() + "', "
                    + "capacidade = " + transporteDTO.getCapacidade() + " "
                    + "WHERE placa_transp = '" + transporteDTO.getPlaca_transp() + "'";

            stmt.execute(comando);
            ConexaoDAO.conn.commit();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDB();
        }
    }

    public boolean excluirTransporte(TransporteDTO transporteDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "DELETE FROM transporte WHERE placa_transp = '" + transporteDTO.getPlaca_transp() + "'";
            stmt.execute(comando);
            ConexaoDAO.conn.commit();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDB();
        }
    }

    public ResultSet consultarTransporte(TransporteDTO transporteDTO, int opcao) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "";

            switch (opcao) {
                case 1:
                    comando = "SELECT * FROM transporte WHERE placa_transp LIKE '" + transporteDTO.getPlaca_transp() + "%'";
                    break;
                case 2:
                    comando = "SELECT * FROM transporte WHERE placa_transp = '" + transporteDTO.getPlaca_transp() + "'";
                    break;
            }

            rs = stmt.executeQuery(comando);
            return rs;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }

//    public List<Object[]> listarTransportesComPontos() {
//        List<Object[]> lista = new ArrayList<>();
//        try {
//            ConexaoDAO.connectionDB();
//            String sql = "SELECT t.id_transporte, t.placa_transp, t.tipo_transp, "
//                    + "t.horario_saida, t.capacidade, p.descricao_ponto "
//                    + "FROM transporte t "
//                    + "LEFT JOIN ponto_embarque p ON p.id_transporte = t.id_transporte "
//                    + "ORDER BY t.id_transporte";
//
//            Statement stmt = ConexaoDAO.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                lista.add(new Object[]{
//                    rs.getInt("id_transporte"),
//                    rs.getString("placa_transp"),
//                    rs.getString("tipo_transp"),
//                    rs.getString("horario_saida"),
//                    rs.getInt("capacidade"),
//                    rs.getString("descricao_ponto")
//                });
//            }
//        } catch (Exception e) {
//            System.out.println("Erro: " + e.getMessage());
//        } finally {
//            ConexaoDAO.closeDB();
//        }
//        return lista;
//    }

}
