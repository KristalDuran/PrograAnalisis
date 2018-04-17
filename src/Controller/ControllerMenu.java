/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GraphMethods;
import Model.Restriction;
import View.Event;
import View.Menu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Kris
 */
public class ControllerMenu implements ActionListener {
    private Menu Menu;
    private GraphMethods graphMethods;

    public ControllerMenu(Menu Menu, GraphMethods graphMethods) {
        this.Menu = Menu;
        this.graphMethods = graphMethods;
        Menu.setVisible(true);
        
    }    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        graphMethods.setCant(Integer.parseInt(Menu.CantViaje.getText()));
        graphMethods.setSizePista(Integer.parseInt(Menu.SizePista.getText()));
        graphMethods.setCantStation(Integer.parseInt(Menu.CantStation.getText()));
        graphMethods.setTimeReal(Integer.parseInt(Menu.TimeReal.getText()));
        graphMethods.setTimeProx(Integer.parseInt(Menu.TimeProx.getText()));
        
        graphMethods.setNodes();
        
        if(Menu.jRadioButton4.isSelected()){
            //probabilistico
            graphMethods.controllerAereoProbabilistic();
        }
        
        if(Menu.jRadioButton2.isSelected()){
            //divide y venceras 
            graphMethods.controllerAereoDividAndConquer();
        }
        
        if(Menu.jRadioButton3.isSelected()){
            //backtraking
            graphMethods.controllerAereoBacktracking();
        }
        
        Event event = new Event();
        event.fijarController(new ControllerEvent(event, graphMethods));
    }
    
}
