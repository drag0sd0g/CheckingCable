package com.dragos.checkingcable;

import java.util.*;

public class CheckingCable {

    private static final int MINIMUM_NUMBER_OF_COMPUTERS = 2;
    private static final int MAXIMUM_NUMBER_OF_COMPUTERS = 20;
    private static final int MINIMUM_NUMBER_OF_COMMANDS = 1;
    private static final int MAXIMUM_NUMBER_OF_COMMANDS = 500;

    private final int numberOfComputers;
    private final int numberOfCommands;
    private final List<Command> commandList;
    private final Map<Integer, Vertex> vertexMap = new HashMap<>();

    public CheckingCable(int numberOfComputers, int numberOfCommands, List<Command> commandList) { //implement fail fast in case of wrong params
        if (numberOfComputers < MINIMUM_NUMBER_OF_COMPUTERS || numberOfComputers > MAXIMUM_NUMBER_OF_COMPUTERS) {
            throw new RuntimeException("number of computers has to be N(2≦N≦20)");
        }
        this.numberOfComputers = numberOfComputers;

        if (numberOfCommands < MINIMUM_NUMBER_OF_COMMANDS || numberOfCommands > MAXIMUM_NUMBER_OF_COMMANDS) {
            throw new RuntimeException("number of commands has to be Q(1≦Q≦500)");
        }
        this.numberOfCommands = numberOfCommands;

        if (this.numberOfCommands != commandList.size()) {
            throw new RuntimeException("size of the command list doesn't match the numberOfCommands parameter");
        }
        this.commandList = Collections.unmodifiableList(commandList);
    }

    public List<String> go() {
        buildVertexMap();
        return processCommands();
    }

    private void buildVertexMap() {
        for (int i = 0; i < numberOfComputers; i++) {
            vertexMap.put(i + 1, new Vertex(String.valueOf(i + 1)));
        }
    }

    private void computePaths(Vertex vertexOrigin) { //Dijkstra
        vertexOrigin.setMinDistance(0.);
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(vertexOrigin); //add source node
        while (!vertexQueue.isEmpty()) {
            Vertex currentVertex = vertexQueue.poll();
            for (Edge edge : currentVertex.getOutboundEdges()) {
                Vertex currentTargetVertex = edge.getTarget();
                double distanceThroughCurrentVertex = currentVertex.getMinDistance() + edge.getWeight();
                if (distanceThroughCurrentVertex < currentTargetVertex.getMinDistance()) {
                    vertexQueue.remove(currentTargetVertex);
                    currentTargetVertex.setMinDistance(distanceThroughCurrentVertex);
                    currentTargetVertex.setPrevious(currentVertex);
                    vertexQueue.add(currentTargetVertex);
                }
            }
        }
    }

    private List<String> processCommands() {
        List<String> outputCollector = new ArrayList<>();
        for (Command command : commandList) {
            switch (command.getInstruction()) {
                case MAKE: {
                    doMakeCommand(command);
                    break;
                }
                case CHECK: {
                    doCheckCommand(command, outputCollector);
                    break;
                }
                default:
                    throw new RuntimeException("this line should be unreachable");
            }
        }
        return outputCollector;
    }

    private void doMakeCommand(Command command) {
        Vertex vertexOrigin = vertexMap.get(command.getComputerA());
        Vertex vertexTarget = vertexMap.get(command.getComputerB());
        Edge edge = new Edge(vertexTarget, command.getTime());
        vertexOrigin.addOutboundEdge(edge);
        computePaths(vertexOrigin);
    }

    private void doCheckCommand(Command command, List<String> outputCollector) {
        Vertex vertexOrigin = vertexMap.get(command.getComputerA());
        Vertex vertexTarget = vertexMap.get(command.getComputerB());
        computePaths(vertexOrigin);
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = vertexTarget; vertex != null; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }

        outputCollector.add((path.contains(vertexTarget) && path.contains(vertexOrigin) && vertexTarget.getMinDistance() <= command.getTime()) ? "YES" : "NO");
    }

    protected Map<Integer, Vertex> getVertexMap() {
        return vertexMap;
    }
}