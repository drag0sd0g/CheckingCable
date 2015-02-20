package com.dragos.checkingcable;

import java.util.Collections;
import java.util.List;

public class CheckingCable {

    private final double numberOfComputers;
    private final double numberOfCommands;
    private final List<Command> commandList;

    public CheckingCable(double numberOfComputers, double numberOfCommands, List<Command> commandList) {
        if(numberOfComputers < 2 || numberOfComputers > 20){
            throw new RuntimeException("number of computers has to be N(2≦N≦20)");
        }
        this.numberOfComputers = numberOfComputers;

        if(numberOfCommands < 1 || numberOfCommands > 500){
            throw new RuntimeException("number of commands has to be Q(1≦Q≦500)");
        }
        this.numberOfCommands = numberOfCommands;

        if(numberOfCommands != commandList.size()){
            throw new RuntimeException("size of the command list doesn't match the numberOfCommands parameter");
        }
        this.commandList = Collections.unmodifiableList(commandList);
    }

    public List<String> performCommands(){
        return Collections.<String>emptyList();
    }
}
