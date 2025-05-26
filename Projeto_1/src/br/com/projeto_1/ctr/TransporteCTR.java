/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.ctr;

/**
 *
 * @author leonardo-teixeira
 */
import br.com.projeto_1.dao.TransporteDAO;
import br.com.projeto_1.dto.TransporteDTO;
import java.sql.ResultSet;
import br.com.projeto_1.dao.ConexaoDAO;

public class TransporteCTR {

    TransporteDAO transporteDAO = new TransporteDAO();

    public String inserirTransporte(TransporteDTO transporteDTO) {
        try {
            if (transporteDAO.inserirTransporte(transporteDTO)) {
                return "Transporte cadastrado com sucesso!!!";
            }
            return "Transporte NÃO cadastrado!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Transporte NÃO cadastrado!!!";
        }
    }

    public String alterarTransporte(TransporteDTO transporteDTO) {
        try {
            if (transporteDAO.alterarTransporte(transporteDTO)) {
                return "Transporte alterado com sucesso!!!";
            }
            return "Transporte NÃO alterado!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Transporte NÃO alterado!!!";
        }
    }

    public String excluirTransporte(TransporteDTO transporteDTO) {
        try {
            if (transporteDAO.excluirTransporte(transporteDTO)) {
                return "Transporte excluído com sucesso!!!";
            }
            return "Transporte NÃO excluído!!!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Transporte NÃO excluído!!!";
        }
    }

    public ResultSet consultarTransporte(TransporteDTO transporteDTO, int opcao) {
        ResultSet rs = null;

        rs = transporteDAO.consultarTransporte(transporteDTO, opcao);

        return rs;
    }

    public void CloseDB() {
        ConexaoDAO.closeDB();
    }
}

