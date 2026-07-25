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

    private int direcciones;
    public Graphs() {
        this.nodes = new HashMap<Node<T>, Set<Node<T>>>();
        direcciones = 0;
    }
    public void add(T value) {
        Node<T> node = new Node<>(value);
        nodes.putIfAbsent(node, new HashSet<>());

    }
    // Agregar una conexion bidereccional
    public void addEdge(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        add(v1);
        add(v2);
        nodes.get(nV1).add(nV2);
        nodes.get(nV2).add(nV1);
        direcciones+=2;
    }
    public void addEdgeUni(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        add(v1);
        add(v2);
        nodes.get(nV1).add(nV2);
        direcciones+=1;
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
    public void removeEdge(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        nodes.get(nV1).remove(nV2);
        nodes.get(nV2).remove(nV1);
        direcciones-=2;
    }
    public void removeEdgeUni(T v1, T v2) {
        Node<T> nV1 = new Node<>(v1);
        Node<T> nV2 = new Node<>(v2);
        nodes.get(nV1).remove(nV2);
        direcciones-=1;

    }
    public void remove(T v1){
        Node<T> nV1 = new Node<>(v1);
        
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : nodes.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
               Set<Node<T>> setLista = entry.getValue();
                 setLista.remove(nV1);
            }
            
        }
        nodes.remove(nV1);
         
    }
    public int contarDirecciones(T v1)
    {
       int direcciones = 0;
        for (Map.Entry<Node<T>, Set<Node<T>>> entry : nodes.entrySet()) {
            direcciones+=entry.getValue().size();
        }

        return direcciones;
    }
    public int contarConexiones(T v1){
       
        int conexiones = nodes.size();
    
        return conexiones;
    }

    public Set<Node<T>> getVecinos(T currente) {
        Node<T> nC = new Node<T>(currente);
        return nodes.getOrDefault(nC,new HashSet<>());
    }
}
