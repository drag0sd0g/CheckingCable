package com.dragos.checkingcable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vertex {

    private final String name;
    private final List<Edge> inboundEdges = new ArrayList<>();
    private final List<Edge> outboundEdges = new ArrayList<>();

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addInboundEdge(Edge edge){
        this.inboundEdges.add(edge);
    }

    public void addOutboundEdge(Edge edge){
        this.outboundEdges.add(edge);
    }

    public List<Edge> getInboundEdges() {
        return Collections.unmodifiableList(inboundEdges);
    }

    public List<Edge> getOutboundEdges() {
        return Collections.unmodifiableList(outboundEdges);
    }
}
