package structures.graphs.implementss;

import java.util.LinkedHashSet;
import java.util.Set;

import structures.graphs.Graphs;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.Node;

public class DFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graphs<T> graph, Node<T> nC, Node<T> nE) {
        Set<T> visited = new LinkedHashSet<>();
        Set<T> path = new LinkedHashSet<>();

        boolean encontrado = dfs(graph, nC, nE, visited, path);

        if (!encontrado) {
            path.clear();
        }

        return new PathResult<>(visited, path);
    }

    private boolean dfs(Graphs<T> graph, Node<T> nC, Node<T> nE, Set<T> visited, Set<T> path) {
        T currentVal = nC.getValue();
        
        visited.add(currentVal);
        path.add(currentVal);

        if (currentVal.equals(nE.getValue())) {
            return true;
        }

        for (Node<T> vecino : graph.getVecinos(nC)) {
            if (!path.contains(vecino.getValue())) {
                boolean encontrado = dfs(graph, vecino, nE, visited, path);
                if (encontrado) {
                    return true;
                }
            }
        }

        path.remove(currentVal);
        return false;
    }
}