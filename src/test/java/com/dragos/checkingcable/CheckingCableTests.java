package com.dragos.checkingcable;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckingCableTests {

    @Test(expected = RuntimeException.class)
    public void testBadInstructionName() {
        new Command(1, 2, "mak3", 20);
    }

    @Test
    public void testCorrectInstructionName() {
        Command makeCommand = new Command(1, 2, "make", 20);
        assertNotNull(makeCommand);
        Command checkCommand = new Command(1, 2, "check", 20);
        assertNotNull(checkCommand);
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withWrongNumberOfComputers() {
        new CheckingCable(0, 0, Collections.<Command>emptyList());
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withCorrectNumberOfComputers_butWrongNumberOfCommands() {
        new CheckingCable(2, 1000, Collections.singletonList(new Command(1, 2, "check", 20)));
    }

    @Test(expected = RuntimeException.class)
    public void testCheckingCable_withCorrectNumberOfComputers_correctNumberOfCommands_butEmptyCommandList() {
        new CheckingCable(2, 1000, Collections.<Command>emptyList());
    }

    @Test
    public void testCheckingCable_withCorrectInput() {
        CheckingCable checkingCable = new CheckingCable(2, 1, Collections.singletonList(new Command(2, 2, "check", 20)));
        assertNotNull(checkingCable);
    }

    @Test
    public void testBuildingOfVertexMap(){
        CheckingCable checkingCable = new CheckingCable(2, 1, Collections.singletonList(new Command(1, 2, "make", 20)));
        checkingCable.go();
        Map<Integer, Vertex> vertexMap = checkingCable.getVertexMap();
        assertEquals(vertexMap.size(), 2);
        assertNotNull(vertexMap.get(1));
        assertNotNull(vertexMap.get(2));
    }

    @Test
    public void testMakeCommand(){
        CheckingCable checkingCable = new CheckingCable(2, 1, Collections.singletonList(new Command(1, 2, "make", 20)));
        checkingCable.go();
        Map<Integer, Vertex> vertexMap = checkingCable.getVertexMap();
        assertEquals(vertexMap.size(), 2);
        assertNotNull(vertexMap.get(1));
        assertNotNull(vertexMap.get(2));
        assertEquals(vertexMap.get(1).getOutboundEdges().size(), 1);
        assertEquals(vertexMap.get(1).getInboundEdges().size(), 0);
        assertEquals(vertexMap.get(2).getOutboundEdges().size(), 0);
        assertEquals(vertexMap.get(2).getInboundEdges().size(), 1);
    }
}
