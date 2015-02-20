package com.dragos.checkingcable;

public class Command {

    private final String computerA;
    private final String computerB;
    private final Instruction instruction;
    private final double time;

    public Command(String computerA, String computerB, String instruction, double time) {
        this.computerA = computerA;
        this.computerB = computerB;
        this.instruction = Instruction.fromString(instruction);
        this.time = time;
    }

    public String getComputerA() {
        return computerA;
    }

    public String getComputerB() {
        return computerB;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public double getTime() {
        return time;
    }
}
