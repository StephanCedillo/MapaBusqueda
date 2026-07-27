/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package memoria;

import java.util.List;
import java.util.Set;

import structures.Node;
import structures.graphs.Graphs;

/**
 *
 * @author stephancedillo
 */
public interface NodeDAO {
    void crear(Node<String> node);
    void borrar(Node<String> node);
    Set<Node<String>> listar();
    void uniConexion(Node<String> node,Node<String> node2);
    void biConexion(Node<String> node,Node<String> node2);
    void crearPredefinidos();
    Graphs<String> obtenerGrafo();
}
