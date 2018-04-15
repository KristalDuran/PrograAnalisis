/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GraphMethods;
import View.Menu;

/**
 *
 * @author Kris
 */
public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
        GraphMethods graphMethods = new GraphMethods();
        
        //ControllerMenu controller = new ControllerMenu(menu, graphMethods);
        
        menu.fijarControlador(new ControllerMenu(menu, graphMethods));
        
        menu.fijarModelo(graphMethods);
        
    }
}
