package domain;

import exception.NoSuchVertexInGraphException;
import lombok.Data;

import java.util.*;

@SuppressWarnings("UnusedReturnValue")
@Data
public class Graph {

    public Graph(GraphType graphType) {
        this.graphType = graphType;
    }

    GraphType graphType = GraphType.DIRECTED;

    Set<IVertex> vertices = new HashSet<>();
    Set<Edge> edges = new HashSet<>();

    Map<IVertex, Set<Edge>> vertexToEdges = new HashMap<>();

    public boolean addVertex(IVertex vertex) {

        if (vertices.contains(vertex)) {
            return false;
        }

        vertices.add(vertex);
        return true;
    }

    public Edge addEdgeBetween(IVertex sourceVertex, IVertex targetVertex) throws NoSuchVertexInGraphException {
        if (vertices.contains(sourceVertex) && vertices.contains(targetVertex)) {

            Edge newEdge = new Edge(sourceVertex, targetVertex);
            if (!edges.contains(newEdge)) {
                edges.add(newEdge);

                if (!vertexToEdges.containsKey(sourceVertex)) {
                    vertexToEdges.put(sourceVertex, new HashSet<>());
                }
                vertexToEdges.get(sourceVertex).add(newEdge);

                if(graphType.equals(GraphType.UNDIRECTED)){
                    Edge reverseEdge = new Edge(targetVertex, sourceVertex);
                    edges.add(reverseEdge);

                    if (!vertexToEdges.containsKey(targetVertex)) {
                        vertexToEdges.put(targetVertex, new HashSet<>());
                    }
                    vertexToEdges.get(targetVertex).add(reverseEdge);

                }
            }

            return newEdge;
        }
        throw new NoSuchVertexInGraphException();
    }

    public List<Edge> getPath(IVertex from, IVertex to) {

        List<Edge> completePath = new ArrayList<>();

        Deque<List<Edge>> incompletePaths = new LinkedList<>();

        if (from.equals(to)) {
            return completePath;
        }

        for (Edge edge : vertexToEdges.get(from)) {
            if (to.equals(edge.getTarget())) {
                completePath.add(edge);
            }
            List<Edge> path = Collections.singletonList(edge);
            incompletePaths.add(path);
        }

        if (completePath.size() > 0) {
            return completePath;
        }


        for (List<Edge> incompletePath; (incompletePath = incompletePaths.poll()) != null; ) {

            int pathLength = incompletePath.size();

            Edge leafEdge = incompletePath.get(pathLength - 1);
            IVertex leafVertex = leafEdge.getTarget();

            if (vertexToEdges.containsKey(leafVertex)) {
                for (Edge outEdge : vertexToEdges.get(leafVertex)) {

                    List<Edge> newPath = new ArrayList<>(incompletePath);
                    newPath.add(outEdge);

                    if (to.equals(outEdge.getTarget())) {
                        return newPath;
                    }
                    incompletePaths.addFirst(newPath);
                }
            }
        }


        return null;
    }


}
