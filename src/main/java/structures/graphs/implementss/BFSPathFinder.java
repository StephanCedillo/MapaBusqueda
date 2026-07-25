package structures.graphs.implementss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
    public PathResult<T> find(Graphs<T> graph,  Node<T> nC, Node<T> nE) {
        Queue<T> queue = new LinkedList<>();
        Set<T> visitados = new HashSet<>();
        Map<Node<T>, Node<T>> parent = new HashMap<>();
        Set<T> visited = new LinkedHashSet<>();

        queue.add(nC.getValue());
        visitados.add(nC.getValue());
        parent.put(nC, null);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            visited.add(current);
            if (current.equals(nE.getValue())) {
                return new PathResult<>(visitados, buildPath(parent, nE.getValue()));
            }
            for(Node<T> vecino:graph.getVecinos(nC)){
                if (!visitados.contains(vecino.getValue())) {
                    visitados.add(current);
                    parent.put(vecino, new Node<T>(current));
                    queue.add(vecino.getValue());
                }
            }
        }
        return new PathResult<>(visited,Set.of());

    }
//NODE<T>
//
    private Set<T> buildPath(Map<Node<T>, Node<T>> parent, T end) {
        List<Node<T>> path = new ArrayList<>();
        for(Node<T> at = new Node<T>(end) ; at!= null;at = parent.get(at)){
            path.add(at);
        }
        Collections.reverse(path);
        Set<T> pathSet = new LinkedHashSet<>();
        for (Node<T> node : path) {
            pathSet.add(node.getValue());
        }
        return pathSet;

    }
}
