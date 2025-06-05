/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.ctr;
import br.com.projeto_1.dao.InscricaoDAO;
import br.com.projeto_1.dto.InscricaoDTO;
/**
 *
 * @author leonardo-teixeira
 */
public class InscricaoCTR {
      InscricaoDAO  inscricaoDAO= new InscricaoDAO();

    public boolean registrarInscricao(InscricaoDTO inscricaoDTO) {
        return inscricaoDAO.registrarInscricao(inscricaoDTO);
    }
}
