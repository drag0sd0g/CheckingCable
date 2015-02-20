package com.dragos.checkingcable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private final String name;
    private final List<Edge> outboundEdges = new ArrayList<>();

    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void addOutboundEdge(Edge edge) {
        this.outboundEdges.add(edge);
    }

    public List<Edge> getOutboundEdges() {
        return Collections.unmodifiableList(outboundEdges);
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public int compareTo(Vertex other) {
        return Double.compare(this.minDistance, other.getMinDistance());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}
