package domain;

import lombok.Data;

@Data
public class Vertex implements IVertex {

    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
