/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GraphMethods;
import View.Menu;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class Main {
    public static void main(String[] args){
        
        
        Menu menu = new Menu();
        
        GraphMethods graphMethods = new GraphMethods();
       
        menu.fijarControlador(new ControllerMenu(menu, graphMethods));
        
        menu.fijarModelo(graphMethods);
        
    }
}
