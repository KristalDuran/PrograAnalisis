/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
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
    int cantTrips;
    int sizePista;
    int cantStation;
    int timeReal;
    int timeProx;
    
    Node node;
    Graph graph;
    
    Node[] nodes;
    
    public GraphMethods() {
    }

    public void setNodes(){
        nodes = new Node[cantStation];
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
    
    public void MakeStation(Integer name, int x, int y){
        
        node = new Node(name, x, y);
        nodes[(name.intValue()-1)] = node;
    }
    
    //definir que vertice se conecta con otro y el tama;o del arco es decir la distancia
    public void MakeGraph(){        
        //elegir aleatoriamente el que no tenga arco, si todos tienen, elegir los mas cercanos 
        mergesort(nodes, 0, nodes.length);
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int subNode = 0; subNode < nodes.length; subNode++) {
            System.out.println("for");
            int cantPista = sizePista - nodes[subNode].getAdjacentNodes().size();               
            
            while ( cantPista > 0) {
                int destineNode = (int) (Math.random()*cantStation);
                if (nodes[subNode].getName().compareTo(nodes[destineNode].getName()) != 0 ) {
                    if (arrayList.size()+1 == cantStation) { 
                        System.out.println("Ya no hay opciones");
                        defineTheClosets(subNode);
                        break;
                    }else{   
                        if (  (nodes[destineNode].adjacentNodes.size() < 1) ){
                            System.out.println("if");
                            addArrayTemp(destineNode, arrayList);
                            addArrayTemp(subNode, arrayList);
                            nodes[subNode].addDestination(nodes[destineNode], defineDistance(nodes[subNode], nodes[destineNode]));                                
                            nodes[destineNode].addDestination(nodes[subNode], defineDistance(nodes[destineNode], nodes[subNode]));
                            cantPista--;                            
                        }    
                    }  
                }
            }            
        }
        System.out.println("le "+ arrayList.size());
        addGraph();
        
    }
    
    public void addArrayTemp(int node, ArrayList<Integer> arrayList){
         if (!arrayList.contains(node)) {
            arrayList.add(node);
        }
    }
    
    public int defineDistance(Node origen, Node destin){        
        return (int)Math.abs((origen.getX() + origen.getY()) - (destin.getX() + destin.getY()));
    }
    
    public void addGraph(){
        graph = new Graph();
        for (int i = 0; i < nodes.length; i++) {
            graph.addNode(nodes[i]);
        }
        
        for(Node tmp : graph.getNodes()){
            System.out.println("Nodo:" + tmp.getName());
            for(Node j : tmp.getShortestPath()){
                System.out.println("from:" + tmp.getName() + " to " + j.getName() + " distance = " + j.getDistance());
            }
        }
        graph = calculateShortestPathFromSource(graph, nodes[0]);
        
        System.out.println("from 1:");
        for(Node tmp : graph.getNodes()){
            System.out.println("to:" + tmp.getName() + " distance: " + tmp.getDistance());
        }
    }
    
    public int defineBestNode(int currentNode){
        int nodeNext = (currentNode+1);
        int nodePast = (currentNode-1);
        int distanceNext = Integer.MAX_VALUE;
        int distancePast = Integer.MAX_VALUE;
        
        if (nodeNext > nodes.length-1) {
            nodeNext--;
        }
        
        if (nodePast < 0) {
            nodePast++;
        }
        
        if (currentNode != nodeNext) {
            while (true) {            
                if (nodes[nodeNext].adjacentNodes.containsKey(nodes[(currentNode)])) {
                    if (nodeNext != nodes.length-1)                       
                        nodeNext++;
                    else{
                        distanceNext = Integer.MAX_VALUE;
                        break;
                    }
                }else{
                    distanceNext = defineDistance(nodes[currentNode], nodes[nodeNext]);
                    break;
                }
            }
        }
        
        if(currentNode != nodePast){
            while (true) {            
                if (nodes[nodePast].adjacentNodes.containsKey(nodes[(currentNode)])) {
                    if (nodePast != 0) {
                        nodePast--;
                    }else{
                        distancePast =Integer.MAX_VALUE;
                        break;
                    }
                }else{
                    distancePast = defineDistance(nodes[currentNode], nodes[nodePast]);
                    break;
                }
            }
        }
        
        if (distanceNext < distancePast) {
            return nodeNext;
        }else{
            return nodePast; 
        }
    }
    
    public void defineTheClosets(int currentNode){
        int destineNode = defineBestNode(currentNode);
        int distancia = defineDistance(nodes[currentNode], nodes[destineNode]);
        nodes[currentNode].addDestination(nodes[destineNode], distancia);
        
    }
        
    public static void mergesort(Node[ ] matrix, int init, int n)
        {
        int n1;
        int n2;
        if (n > 1)
        {
            n1 = n / 2;
            n2 = n - n1;
            mergesort(matrix, init, n1);
            mergesort(matrix, init + n1, n2);
            merge(matrix, init, n1, n2);
        }
    }
    
    private static void merge(Node[] matrix, int init, int n1, int n2) {
        
        Node[ ] buffer = new Node[n1+n2];
        int temp = 0;
        int temp1 = 0;
        int temp2 = 0;
        int i;
        while ((temp1 < n1) && (temp2 < n2))
        {
            int a = matrix[init + temp1].getX() + matrix[init + temp1].getY();
            int b = matrix[init + n1 + temp2].getX() + matrix[init + n1 + temp2].getY();
            if (a < b)
                buffer[temp++] = matrix[init + (temp1++)];
            else
                buffer[temp++] = matrix[init + n1 + (temp2++)];
        }
        while (temp1 < n1)
            buffer[temp++] = matrix[init + (temp1++)];
        while (temp2 < n2)
            buffer[temp++] = matrix[init + n1 + (temp2++)];
        for (i = 0; i < n1+n2; i++)
            matrix[init + i] = buffer[i];
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
        return cantTrips;
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
        this.cantTrips = cant;
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
