/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kris
 */
public class GraphMethods {
    
    int idTrip = 0;
    int cant;
    int sizePista;
    int cantStation;
    int timeReal;
    int timeProx;
    
    public GraphMethods() {
    }

    public GraphMethods(int cant, int sizePista, int cantStation, int timeReal, int timeProx) {
        this.cant = cant;
        this.sizePista = sizePista;
        this.cantStation = cantStation;
        this.timeReal = timeReal;
        this.timeProx = timeProx;
    }
    
    
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry < Node, Integer> adjacencyPair:currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    
    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    private static void CalculateMinimumDistance(Node evaluationNode,
        Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
    
    public void MakeStation(int cant){
        //validar los putnos x y en distancias razonables, y el id un contandor para esto llamar metodos de estacion
    }
    
    //definir que vertice se conecta con otro y el tama;o del arco es decir la distancia
    public void MakeGraph(int cantArc){
        
    }
    
    //definir los puntos orinen y destino de cada viaje
    public void MakeWays(int cantTravel){
        int origen = (int)(Math.random()*cantTravel)+1;
        int destino;
        while (true) {
            destino = (int)(Math.random()*cantTravel)+1;
            if (origen != destino) {
                break;
            }
        }
        System.out.println(origen + " " + destino);
        idTrip++;
//        
//        if (ExistWay(origen, destino)) {
//            LookBestWay(origen, destino);
//            
//        }else{
//            MakeWays(cantTravel);
//        }

    }
    
    public void controllerAereoProbabilistic(){
        
    }
    
    public void controllerAereoDividAndConquer(){
        
    }
    
    public void controllerAereoBacktracking(){
        
    }
    
    public boolean ExistWay(int origen, int destino){
        return false;
    }
    //camino mas corto 
    public void LookBestWay(int origen, int destino){
        //Guarda en hashtable
    }   

    public int getIdTrip() {
        return idTrip;
    }

    public int getCant() {
        return cant;
    }

    public int getSizePista() {
        return sizePista;
    }

    public int getCantStation() {
        return cantStation;
    }

    public int getTimeReal() {
        return timeReal;
    }

    public int getTimeProx() {
        return timeProx;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setSizePista(int sizePista) {
        this.sizePista = sizePista;
    }

    public void setCantStation(int cantStation) {
        this.cantStation = cantStation;
    }

    public void setTimeReal(int timeReal) {
        this.timeReal = timeReal;
    }

    public void setTimeProx(int timeProx) {
        this.timeProx = timeProx;
    }
    
    
    
}
