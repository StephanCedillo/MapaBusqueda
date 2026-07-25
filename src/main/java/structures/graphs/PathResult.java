/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.graphs;

import java.util.Set;

/**
 *
 * @author stephancedillo
 */

public class PathResult<T> {
    private final Set<T> visitados;
    private final Set<T> path;
    public PathResult(Set<T> visitados, Set<T> path) {
        this.visitados = visitados;
        this.path = path;
    }
    public Set<T> getVisitados() {
        return visitados;
    }
    public Set<T> getPath() {
        return path;
    }
    @Override
    public String toString() {
        return "\n ======== PATHRESULT ======= " + 
        "\n visitados= " + visitados + ""+
        "\n " + ( path.isEmpty() ? "No se encontro camino a los nodos" : "path= " + path);
    }
    
}
