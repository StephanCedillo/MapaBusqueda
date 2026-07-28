/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memoria;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
        nodos = new LinkedHashSet<>();
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

    @Override
    public void crearPredefinidos() {


        crear(new Node<>("1", 475, 10));
        crear(new Node<>("2", 549, 23));
        crear(new Node<>("3", 615, 47));
        crear(new Node<>("4", 688, 63));
        crear(new Node<>("5", 756, 81));
        crear(new Node<>("6", 185, 13));
        crear(new Node<>("7", 258, 36));
        crear(new Node<>("8", 332, 57));
        crear(new Node<>("9", 389, 81));
        crear(new Node<>("10", 463, 102));
        crear(new Node<>("11", 532, 105));
        crear(new Node<>("12", 605, 139));
        crear(new Node<>("13", 673, 159));
        crear(new Node<>("14", 746, 173));
        crear(new Node<>("15", 22, 48));
        crear(new Node<>("16", 97, 71));
        crear(new Node<>("17", 168, 102));
        crear(new Node<>("18", 236, 135));
        crear(new Node<>("19", 308, 151));
        crear(new Node<>("20", 375, 175));
        crear(new Node<>("21", 448, 190));
        crear(new Node<>("22", 520, 206));
        crear(new Node<>("23", 590, 233));
        crear(new Node<>("24", 660, 248));
        crear(new Node<>("25", 734, 271));
        crear(new Node<>("26", 13, 145));
        crear(new Node<>("27", 82, 168));
        crear(new Node<>("28", 162, 198));
        crear(new Node<>("29", 223, 222));
        crear(new Node<>("30", 302, 258));
        crear(new Node<>("31", 369, 262));
        crear(new Node<>("32", 433, 283));
        crear(new Node<>("33", 502, 309));
        crear(new Node<>("34", 582, 334));
        crear(new Node<>("35", 647, 360));
        crear(new Node<>("36", 718, 380));
        crear(new Node<>("37", 69, 261));
        crear(new Node<>("38", 144, 295));
        crear(new Node<>("39", 213, 314));
        crear(new Node<>("40", 286, 332));
        crear(new Node<>("41", 350, 360));
        crear(new Node<>("42", 424, 382));
        crear(new Node<>("43", 491, 413));
        crear(new Node<>("44", 563, 433));
        crear(new Node<>("45", 633, 456));
        crear(new Node<>("46", 715, 481));
        crear(new Node<>("47", 54, 363));
        crear(new Node<>("48", 135, 380));
        crear(new Node<>("49", 200, 405));
        crear(new Node<>("50", 278, 428));
        crear(new Node<>("51", 333, 458));
        crear(new Node<>("52", 413, 473));
        crear(new Node<>("53", 475, 503));
        crear(new Node<>("54", 552, 528));
        crear(new Node<>("55", 623, 552));
        crear(new Node<>("56", 693, 570));
        crear(new Node<>("57", 45, 443));
        crear(new Node<>("58", 114, 476));
        crear(new Node<>("59", 185, 498));
        crear(new Node<>("60", 256, 521));
        crear(new Node<>("61", 325, 550));
        crear(new Node<>("62", 392, 567));
        crear(new Node<>("63", 464, 587));
        crear(new Node<>("64", 535, 615));
        crear(new Node<>("65", 607, 637));
        crear(new Node<>("66", 686, 658));
        crear(new Node<>("67", 28, 543));
        crear(new Node<>("68", 100, 567));
        crear(new Node<>("69", 176, 592));
        crear(new Node<>("70", 245, 617));
        crear(new Node<>("71", 300, 639));
        crear(new Node<>("72", 373, 676));
        
    }

    @Override
    public Graphs<String> obtenerGrafo() {
        return graph;
    }

    @Override
    public void borrarTodo() {
        nodos = new LinkedHashSet<>();
        graph = new Graphs<>(); // Vaciamos el grafo en memoria
    }

    
}
