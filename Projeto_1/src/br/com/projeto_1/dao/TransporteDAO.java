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
public class TransporteDAO {

    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirTransporte(TransporteDTO transporteDTO) {
        try {
            ConexaoDAO.connectionDB();
            stmt = ConexaoDAO.conn.createStatement();

            String comando = "INSERT INTO transporte (placa_transp, tipo_transp, ponto_embarque, horario_saida, id_motorista, capacidade) VALUES ("
                    + "'" + transporteDTO.getPlaca_transp() + "', "
                    + "'" + transporteDTO.getTipo_transp() + "', "
                    + "'" + transporteDTO.getPonto_embarque() + "', "
                    + "'" + transporteDTO.getHorario_saida() + "', "
                    + "'" + transporteDTO.getId_motorista() + "', "
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
                    + "ponto_embarque = '" + transporteDTO.getPonto_embarque() + "', "
                    + "horario_saida = '" + transporteDTO.getHorario_saida() + "', "
                    + "id_motorista = '" + transporteDTO.getId_motorista() + "', "
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
}
