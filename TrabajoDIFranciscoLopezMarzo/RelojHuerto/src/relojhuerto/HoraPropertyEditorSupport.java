/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojhuerto;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Fran
 */
public class HoraPropertyEditorSupport extends PropertyEditorSupport {
    private PanelHora panelHora = new PanelHora();

    @Override
    public boolean supportsCustomEditor() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getCustomEditor() {
        return panelHora; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJavaInitializationString() {
        return "new relojhuerto.Hora("+panelHora.getCurrentValue().getHoraInicio()+")";
    }

    @Override
    public Object getValue() {
        return panelHora.getCurrentValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
