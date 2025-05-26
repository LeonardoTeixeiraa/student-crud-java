/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dto;

/**
 *
 * @author leonardo-teixeira
 */
public class TransporteDTO {
    private String placa_transp, tipo_transp;
    private String ponto_embarque, horario_saida;
    private String id_motorista;
    private int capacidade;

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getPlaca_transp() {
        return placa_transp;
    }

    public void setPlaca_transp(String placa_transp) {
        this.placa_transp = placa_transp;
    }

    public String getTipo_transp() {
        return tipo_transp;
    }

    public void setTipo_transp(String tipo_transp) {
        this.tipo_transp = tipo_transp;
    }

    public String getPonto_embarque() {
        return ponto_embarque;
    }

    public void setPonto_embarque(String ponto_embarque) {
        this.ponto_embarque = ponto_embarque;
    }

    public String getHorario_saida() {
        return horario_saida;
    }

    public void setHorario_saida(String horario_saida) {
        this.horario_saida = horario_saida;
    }

    public String getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(String id_motorista) {
        this.id_motorista = id_motorista;
    }
}
