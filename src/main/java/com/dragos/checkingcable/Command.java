package com.dragos.checkingcable;

public class Command {

    private final int computerA;
    private final int computerB;
    private final Instruction instruction;
    private final double time;

    public Command(int computerA, int computerB, String instruction, double time) {
        this.computerA = computerA;
        this.computerB = computerB;
        this.instruction = Instruction.fromString(instruction);
        this.time = time;
    }

    public int getComputerA() {
        return computerA;
    }

    public int getComputerB() {
        return computerB;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public double getTime() {
        return time;
    }
}
