/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Kris
 */
public class Restriction {
    int from;
    int to;
    
    public Restriction(int from, int to){
        this.from = from;
        this.to = to;
    }
    
    public boolean isRestriction(int toEvaluate){
        if(from < toEvaluate && toEvaluate < to){
            return true;
        }
        else{
            return false;
        }
    }
}
