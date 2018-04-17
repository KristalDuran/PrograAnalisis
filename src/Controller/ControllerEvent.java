/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GraphMethods;
import Model.Restriction;
import View.Event;
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
public class ControllerEvent implements ActionListener{

    Event event;
    GraphMethods graphMethods;

    public ControllerEvent(Event event, GraphMethods graphMethods) {
        this.event = event;
        this.graphMethods = graphMethods;
        event.setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == event.jBCreate) {
            
        } else if (e.getSource() == event.jBBack) {
            
        } else if (e.getSource() == event.jBStarError) {
            
        }
        
    }
    
    
   
}
