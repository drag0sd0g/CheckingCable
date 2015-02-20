package com.dragos.checkingcable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (numberOfCommands != commandList.size()) {
            throw new RuntimeException("size of the command list doesn't match the numberOfCommands parameter");
        }
        this.commandList = Collections.unmodifiableList(commandList);
    }

    public List<String> go() {
        buildVertexMap();
        processCommands();
        return Collections.<String>emptyList();
    }

    private void buildVertexMap(){
        for (int i = 0; i < numberOfComputers; i++) {
            vertexMap.put(i + 1, new Vertex(String.valueOf(i)));
        }
    }

    private void processCommands(){
        for (Command command : commandList) {
            switch (command.getInstruction()) {
                case MAKE: {
                    doMakeCommand(command);
                    break;
                }
                case CHECK: {
                    doCheckCommand(command);
                    break;
                }
                default:
                    throw new RuntimeException("this line should be unreachable");
            }
        }
    }

    private void doMakeCommand(Command command) {
        Vertex vertexOrigin = vertexMap.get(command.getComputerA());
        Vertex vertexTarget = vertexMap.get(command.getComputerB());
        Edge edge = new Edge(vertexOrigin, vertexTarget, command.getTime());
        vertexOrigin.addOutboundEdge(edge);
        vertexTarget.addInboundEdge(edge);
    }

    private void doCheckCommand(Command command) {
        //TODO
    }

    protected Map<Integer, Vertex> getVertexMap() {
        return vertexMap;
    }
}