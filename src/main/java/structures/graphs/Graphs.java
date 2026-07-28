/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import structures.Node;

/**
 *
 * @author stephancedillo
 */
public class Graphs<T> {

    Map<Node<T>, Set<Node<T>>> nodes;

    
    public Graphs() {
        this.nodes = new HashMap<Node<T>, Set<Node<T>>>();
     
    }
    public void add(Node<T> node) {
        nodes.putIfAbsent(node, new HashSet<>());

    }
    // Agregar una conexion bidereccional
    public void addEdge(Node<T> nV1, Node<T> nV2) {
      
        add(nV1);
        add(nV2);
        nodes.get(nV1).add(nV2);
        nodes.get(nV2).add(nV1);

    }

    public void addEdgeUni(Node<T> nV1, Node<T> nV2) {
        add(nV1);
        add(nV2);
        nodes.get(nV1).add(nV2);
    

    }
    
    public void print() {
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : nodes.entrySet()) {
            System.out.print(entry.getKey() + "-->");
            for (Node<T> node : entry.getValue()) {
                System.out.print(node);
            }
            System.out.println();
        }
    }
    public void removeEdge(Node<T> nV1, Node<T> nV2) {
       
        nodes.get(nV1).remove(nV2);
        nodes.get(nV2).remove(nV1);
       
    }
    public void removeEdgeUni(Node<T> nV1, Node<T> nV2) {
       
       nodes.get(nV1).remove(nV2);
  
      

    }
    public void remove(Node<T> nV1) {
        for (Set<Node<T>> vecinos : nodes.values()) {
            vecinos.remove(nV1);
        }
        nodes.remove(nV1);
    }
  
    public Set<Node<T>> getVecinos(Node<T> nC) {
     
        return nodes.getOrDefault(nC,new HashSet<>());
    }
}
