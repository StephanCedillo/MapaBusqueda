/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.graphs;

/**
 *
 * @author stephancedillo
 */
public interface PathFinder<T> {

    PathResult<T> find(
        Graphs<T> graph,
        T start,
        T end
    );
}
