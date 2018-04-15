/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jil
 */
public class Node {
    
    private Integer name;
     
    private List<Node> shortestPath = new LinkedList<>();
     
    private Integer distance = Integer.MAX_VALUE;
     
    Map<Node, Integer> adjacentNodes = new HashMap<>();
 
    private int x, y;
    
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
  
    public Node(Integer name) {
        this.name = name;
    }  

    public Node(Integer name,int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    } 
    
    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
    
}

