package com.dragos.checkingcable;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class CheckingCableTests {

    @Test(expected = RuntimeException.class)
    public void testBadInstructionName() {
        new Command("a", "b", "mak3", 20);
    }

    @Test
    public void testCorrectInstructionName() {
        Command makeCommand = new Command("a", "b", "make", 20);
        assertNotNull(makeCommand);
        Command checkCommand = new Command("a", "b", "check", 20);
        assertNotNull(checkCommand);
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withWrongNumberOfComputers() {
        new CheckingCable(0, 0, Collections.<Command>emptyList());
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withCorrectNumberOfComputers_butWrongNumberOfCommands() {
        new CheckingCable(2, 1000, Collections.<Command>singletonList(new Command("a", "b", "check", 20)));
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withCorrectNumberOfComputers_correctNumberOfCommands_butEmptyCommandList() {
        new CheckingCable(2, 1000, Collections.<Command>emptyList());
    }

    @Test
    public void testCheckingCable_withCorrectInput() {
        CheckingCable checkingCable = new CheckingCable(2, 1, Collections.singletonList(new Command("a", "b", "check", 20)));
        assertNotNull(checkingCable);
    }
}
