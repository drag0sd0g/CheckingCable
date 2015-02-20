package com.dragos.checkingcable;

public enum Instruction {
    CHECK("check"), MAKE("make");

    private final String instructionName;

    Instruction(String instructionName) {
        this.instructionName = instructionName;
    }

    public String getInstructionName() {
        return instructionName;
    }

    public static Instruction fromString(String instruction) {
        for (Instruction value : Instruction.values()) {
            if (value.getInstructionName().equals(instruction)) {
                return value;
            }
        }
        throw new RuntimeException("cannot interpret instruction " + instruction);
    }
}
