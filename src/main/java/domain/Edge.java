package domain;

import lombok.Data;

@Data
public class Edge {

    public Edge(IVertex source, IVertex target) {
        this.source = source;
        this.target = target;
    }

    IVertex source;
    IVertex target;
}
