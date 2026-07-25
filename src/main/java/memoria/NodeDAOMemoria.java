/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memoria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import structures.Node;
import structures.graphs.Graphs;

/**
 *
 * @author stephancedillo
 */
public class NodeDAOMemoria implements NodeDAO {
    private Set<Node<String>> nodos;
    private Graphs<String> graph;
    public NodeDAOMemoria() {
        nodos = new HashSet<>();
        graph = new Graphs<>();
    }

    @Override
    public void crear(Node<String> node) {
        nodos.add(node);
        graph.add(node);
    }

    @Override
    public void borrar(Node<String> node) {
        nodos.remove(node);
        graph.remove(node);
    }

    @Override
    public Set<Node<String>> listar() {
        return nodos;
    }

    @Override
    public void uniConexion(Node<String> node, Node<String> node2) {
        graph.addEdgeUni(node, node2);
    }

    @Override
    public void biConexion(Node<String> node, Node<String> node2) {
        graph.addEdge(node, node2);
    }
}
