package domain;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    @Test
    void testDirectedGraph() {
        Graph directedGraph = new Graph(GraphType.DIRECTED);

        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        Vertex v8 = new Vertex("v8");
        Vertex v9 = new Vertex("v9");

        directedGraph.addVertex(v1);
        directedGraph.addVertex(v2);
        directedGraph.addVertex(v3);
        directedGraph.addVertex(v4);
        directedGraph.addVertex(v5);
        directedGraph.addVertex(v6);
        directedGraph.addVertex(v7);
        directedGraph.addVertex(v8);
        directedGraph.addVertex(v9);


        directedGraph.addEdgeBetween(v1, v2);
        directedGraph.addEdgeBetween(v1, v3);
        directedGraph.addEdgeBetween(v1, v4);
        directedGraph.addEdgeBetween(v2, v5);
        directedGraph.addEdgeBetween(v3, v6);
        directedGraph.addEdgeBetween(v3, v7);
        directedGraph.addEdgeBetween(v4, v8);
        directedGraph.addEdgeBetween(v7, v9);

        directedGraph.addEdgeBetween(v4, v7);

        directedGraph.addEdgeBetween(v1, v9);


        assertEquals(0, directedGraph.getPath(v1, v1).size());

        assertEquals(1, directedGraph.getPath(v1, v2).size());

        assertEquals(2, directedGraph.getPath(v1, v5).size());

        assertEquals(1, directedGraph.getPath(v1, v9).size());

        assertEquals(2, directedGraph.getPath(v3, v9).size());

    }

    @Test
    void testUndirectedGraph() {
        Graph undirectedGraph = new Graph(GraphType.UNDIRECTED);


        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        Vertex v8 = new Vertex("v8");
        Vertex v9 = new Vertex("v9");

        undirectedGraph.addVertex(v1);
        undirectedGraph.addVertex(v2);
        undirectedGraph.addVertex(v3);
        undirectedGraph.addVertex(v4);
        undirectedGraph.addVertex(v5);
        undirectedGraph.addVertex(v6);
        undirectedGraph.addVertex(v7);
        undirectedGraph.addVertex(v8);
        undirectedGraph.addVertex(v9);


        undirectedGraph.addEdgeBetween(v1, v2);
        undirectedGraph.addEdgeBetween(v1, v3);
        undirectedGraph.addEdgeBetween(v1, v4);
        undirectedGraph.addEdgeBetween(v2, v5);
        undirectedGraph.addEdgeBetween(v3, v6);
        undirectedGraph.addEdgeBetween(v3, v7);
        undirectedGraph.addEdgeBetween(v4, v8);
        undirectedGraph.addEdgeBetween(v7, v9);

        undirectedGraph.addEdgeBetween(v4, v7);

        undirectedGraph.addEdgeBetween(v1, v9);


        assertEquals(0, undirectedGraph.getPath(v1, v1).size());

        assertEquals(1, undirectedGraph.getPath(v1, v2).size());

        assertEquals(2, undirectedGraph.getPath(v5, v1).size());

        assertEquals(4, undirectedGraph.getPath(v1, v5).size());

        assertEquals(1, undirectedGraph.getPath(v1, v9).size());

        assertEquals(2, undirectedGraph.getPath(v3, v9).size());

    }

    @Test
    void testCycleDirectedGraph() {

        //       1
        //    /  |  \
        //   4-> 5 <-2
        //    \  |  /
        //       3

        Graph directedGraph = new Graph(GraphType.DIRECTED);


        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");


        directedGraph.addVertex(v1);
        directedGraph.addVertex(v2);
        directedGraph.addVertex(v3);
        directedGraph.addVertex(v4);
        directedGraph.addVertex(v5);

        directedGraph.addEdgeBetween(v1, v2);
        directedGraph.addEdgeBetween(v2, v3);
        directedGraph.addEdgeBetween(v2, v5);
        directedGraph.addEdgeBetween(v3, v4);
        directedGraph.addEdgeBetween(v4, v5);
        directedGraph.addEdgeBetween(v1, v5);
        directedGraph.addEdgeBetween(v3, v5);


        assertEquals(0, directedGraph.getPath(v1, v1).size());
        assertEquals(1, directedGraph.getPath(v1, v2).size());
        assertThat(directedGraph.getPath(v1, v4).size(), Matchers.anyOf(Matchers.is(1), Matchers.is(3)));
        assertThat(directedGraph.getPath(v1, v5).size(), Matchers.anyOf(Matchers.is(2), Matchers.is(1)));
        assertThat(directedGraph.getPath(v1, v3).size(), Matchers.anyOf(Matchers.is(2), Matchers.is(4)));
    }

    @Test
    void testCycleUndirectedGraph() {

        //       1
        //    /  |  \
        //   4-  5  -2
        //    \  |  /
        //       3

        Graph undirectedGraph = new Graph(GraphType.UNDIRECTED);


        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");

        undirectedGraph.addVertex(v1);
        undirectedGraph.addVertex(v2);
        undirectedGraph.addVertex(v3);
        undirectedGraph.addVertex(v4);
        undirectedGraph.addVertex(v5);


        undirectedGraph.addEdgeBetween(v1, v2);
        undirectedGraph.addEdgeBetween(v2, v3);
        undirectedGraph.addEdgeBetween(v2, v5);
        undirectedGraph.addEdgeBetween(v3, v4);
        undirectedGraph.addEdgeBetween(v4, v5);
        undirectedGraph.addEdgeBetween(v1, v5);
        undirectedGraph.addEdgeBetween(v3, v5);

        assertThat(undirectedGraph.getPath(v3, v1).size(), Matchers.anyOf(Matchers.is(2), Matchers.is(4)));
        assertThat(undirectedGraph.getPath(v2, v1).size(), Matchers.anyOf(Matchers.is(1)));
    }
}
