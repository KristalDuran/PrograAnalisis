/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Kris
 */
public class ControllerMenu {
    private Menu Menu;

    public ControllerMenu(Menu Menu) {
        this.Menu = Menu;
    }
    
    
   // @Override
    public void actionPerformed(ActionEvent ae) {
        int cant = Integer.parseInt(Menu.CantViaje.getText());
        int sizePista = Integer.parseInt(Menu.SizePista.getText());
        int cantStation = Integer.parseInt(Menu.CantStation.getText());
        int timeReal = Integer.parseInt(Menu.TimeReal.getText());
        int timeProx = Integer.parseInt(Menu.TimeProx.getText());
        
    }
}
