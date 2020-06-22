/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

/**
 *
 * @author tarde
 */
public class Parcelas {
    private int id_parcela;
    private boolean activada;
    private int dias_sin_regar;
    private boolean regada;
    private int fase;

    public Parcelas(boolean activada, int dias_sin_regar, boolean regada, int fase) {
        this.activada = activada;
        this.dias_sin_regar = dias_sin_regar;
        this.regada = regada;
        this.fase = fase;
    }

    public int getId_parcela() {
        return id_parcela;
    }

    public void setId_parcela(int id_parcela) {
        this.id_parcela = id_parcela;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public int getDias_sin_regar() {
        return dias_sin_regar;
    }

    public void setDias_sin_regar(int dias_sin_regar) {
        this.dias_sin_regar = dias_sin_regar;
    }

    public boolean isRegada() {
        return regada;
    }

    public void setRegada(boolean regada) {
        this.regada = regada;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }
    
}
