package structures.graphs.implementss;


import java.util.HashSet;
import java.util.Set;

import structures.graphs.Graphs;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.Node;

public class DFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graphs<T> graph, T start, T end) {
        Set<T> visited = new HashSet<>();
        Set<T> path = new HashSet<>();

        boolean encontrado = dfs(graph, start, end, visited, path);

        if (!encontrado) {
            path.clear();
        }

        return new PathResult<>(visited, path);
    }

    private boolean dfs(Graphs<T> graph,T currente, T end,Set<T> visited, Set<T> path) {

        visited.add(currente);
        path.add(currente);
        Node<T> nC = new Node<>(currente);
        Node<T> nE = new Node<>(end);

        if (nC.equals(nE)) {
            return true;
        }

        for (Node<T> vecino : graph.getVecinos(currente)) {
            if (!visited.contains(vecino.getValue())) {
                boolean encontrado = dfs(graph, vecino.getValue(), end, visited, path);

                if (encontrado) {
                    return true;
                }
            }
        }
        path.remove(currente);
        return false;
    }

}
