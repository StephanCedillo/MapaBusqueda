package structures.graphs.implementss;


import java.util.HashSet;
import java.util.Set;

import structures.graphs.Graphs;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.Node;

public class DFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graphs<T> graph, Node<T> nC, Node<T> nE) {
        Set<T> visited = new HashSet<>();
        Set<T> path = new HashSet<>();

        boolean encontrado = dfs(graph, nC, nE, visited, path);

        if (!encontrado) {
            path.clear();
        }

        return new PathResult<>(visited, path);
    }

    private boolean dfs(Graphs<T> graph,Node<T> nC, Node<T> nE,Set<T> visited, Set<T> path) {

        visited.add(nC.getValue());
        path.add(nC.getValue());
       

        if (nC.equals(nE)) {
            return true;
        }

        for (Node<T> vecino : graph.getVecinos(nC)) {
            if (!visited.contains(vecino.getValue())) {
                boolean encontrado = dfs(graph, vecino, nE, visited, path);

                if (encontrado) {
                    return true;
                }
            }
        }
        path.remove(nC.getValue());
        return false;
    }

}
