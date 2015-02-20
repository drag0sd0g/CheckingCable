package com.dragos.checkingcable;

public class Edge {
    private final Vertex origin;
    private final Vertex target;
    private final double value;

    public Edge(Vertex origin, Vertex target, double value) {
        this.origin = origin;
        this.target = target;
        this.value = value;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getTarget() {
        return target;
    }

    public double getValue() {
        return value;
    }

}
