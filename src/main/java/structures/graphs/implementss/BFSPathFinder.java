package structures.graphs.implementss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import structures.graphs.Graphs;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.Node;

public class BFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graphs<T> graph, Node<T> nC, Node<T> nE) {
        Queue<Node<T>> queue = new LinkedList<>();
        Set<T> visitados = new LinkedHashSet<>();
        Map<Node<T>, Node<T>> parent = new HashMap<>();

        queue.add(nC);
        visitados.add(nC.getValue());
        parent.put(nC, null);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();

            if (current.getValue().equals(nE.getValue())) {
                return new PathResult<>(visitados, buildPath(parent, current));
            }

            for (Node<T> vecino : graph.getVecinos(current)) {
                if (!visitados.contains(vecino.getValue())) {
                    visitados.add(vecino.getValue());
                    parent.put(vecino, current);
                    queue.add(vecino);
                }
            }
        }
        
        return new PathResult<>(visitados, Set.of());
    }

    private Set<T> buildPath(Map<Node<T>, Node<T>> parent, Node<T> end) {
        List<T> path = new ArrayList<>();
        
        for (Node<T> at = end; at != null; at = parent.get(at)) {
            path.add(at.getValue());
        }
        
        Collections.reverse(path);
        
        return new LinkedHashSet<>(path);
    }
}