/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojhuerto;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Fran
 */
public class RelojHuerto extends JLabel implements Serializable {
    private Hora hora; // HORA DE INICIO DEL RELOJ.
    private RelojListener relojListener;
    
    public RelojHuerto() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(hora != null){
                    cambiarHora();
                    setText(hora.getHoraInicio()+"");
                    if(hora.getHoraInicio() == 1) {
                        if(relojListener != null) relojListener.cambioDia();
                    }
                } else {
                    setText("0");
                }
            }
        }, 0, 1000);
    }
    
    public void cambiarHora() {
        if(hora != null) {
            if(hora.getHoraInicio() != 24)
                hora.setHoraInicio(hora.getHoraInicio()+1);
            else
                hora.setHoraInicio(1);
        }
    }
    
    public void addRelojListener(RelojListener relojListener) {
        this.relojListener = relojListener;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }
    
}
