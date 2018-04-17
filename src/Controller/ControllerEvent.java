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
        defineLocation(graphMethods.getCantStation());
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == event.jBStart) {
            graphMethods.MakeGraph();
        } else if (e.getSource() == event.jBBack) {
            
        } else if (e.getSource() == event.jBStarError) {
            
        }
        
    }
    
    public void defineLocation(int cantidad){
        //int cantidad = Integer.parseInt(jTextField1.getText());
       
        Dimension dimension = event.planoCartesiano.getSize();
        
        int width = dimension.width-40;
        int height = dimension.height-40;
        
        Graphics grafico = event.planoCartesiano.getGraphics();
        
        ArrayList<Restriction> restrictionsX = new ArrayList();
        ArrayList<Restriction> restrictionsY = new ArrayList();
        
        Random getRandom = new Random();
        
        int x = getRandom.nextInt(width);
        int y = getRandom.nextInt(height);
        
        for(int nodesToDraw = 0; nodesToDraw < cantidad; nodesToDraw++){
            
            while(!isAllowToPlace(restrictionsX,x) && !isAllowToPlace(restrictionsY,y)){
                x = getRandom.nextInt(width)+20;
                y = getRandom.nextInt(height)+20;
            }
            
            restrictionsX.add(new Restriction(x-30,x+30));
            restrictionsY.add(new Restriction(y-30,y+30));
            
            graphMethods.MakeStation(nodesToDraw+1, x, y);
            //nodesToDraw+1 para no iniciar en 0
            grafico.drawOval(x,y,20,20);
            String numberOfNode = "" + (nodesToDraw+1);
            grafico.drawChars(numberOfNode.toCharArray(),0, numberOfNode.toCharArray().length, x+4, y+15);
            
            //guardar en un array los puntos x+10,y+10///////////////////////////////////////////////
        }
        
        
    }
    
    private boolean isAllowToPlace(ArrayList<Restriction> restrictions, int toEvaluate){
        
        for(Restriction restriction: restrictions){
            if(restriction.isRestriction(toEvaluate)){
                return false;
            }
        }
        return true;
    }
   
}
