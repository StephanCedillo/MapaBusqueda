/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package memoria;

import java.util.List;
import java.util.Set;

import structures.Node;

/**
 *
 * @author stephancedillo
 */
public interface NodeDAO {
    void crear(Node<String> node);
    void borrar(Node<String> node);
    Set<Node<String>> listar();
}
