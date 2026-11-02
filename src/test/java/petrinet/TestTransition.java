package petrinet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestTransition {

    @Test
    void testTransitionToEmptyPlace() {
        Transition t = new Transition();
        Place p = new Place(0);
        t.addOutputArc(new OutputArc(p, 1));

        assertTrue(t.isFireable());
        assertTrue(t.fire());
        assertEquals(1, p.getTokens());
        assertTrue(t.fire());
        assertEquals(2, p.getTokens());
    }

    @Test
    void testEmptyPlaceToTransition() {
        Place p = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p, 1));

        assertFalse(t.isFireable());
        assertFalse(t.fire());
        p.addTokens(1);
        assertTrue(t.fire());
        assertEquals(0, p.getTokens());
        assertFalse(t.isFireable());
    }

    @Test
    void testTwoPlacesToTransition() {
        Place p1 = new Place(1);
        Place p2 = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p1, 1));
        t.addInputArc(new SimpleArc(p2, 1));

        assertFalse(t.fire());
        assertEquals(1, p1.getTokens());
        p2.addTokens(1);
        assertTrue(t.fire());
        assertEquals(0, p1.getTokens());
        assertEquals(0, p2.getTokens());
    }

    @Test
    void testAllArcTypes() {
        Place p1 = new Place(3);
        Place p2 = new Place(0);
        Place p3 = new Place(5);
        Place p4 = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p1, 2));
        t.addInputArc(new ZeroArc(p2));
        t.addInputArc(new EmptyingArc(p3));
        t.addOutputArc(new OutputArc(p4, 1));

        assertTrue(t.fire());
        assertEquals(1, p1.getTokens());
        assertEquals(0, p2.getTokens());
        assertEquals(0, p3.getTokens());
        assertEquals(1, p4.getTokens());
        assertFalse(t.fire());
    }

    @Test
    void testNotFireableChangesNothing() {
        Place p1 = new Place(3);
        Place p2 = new Place(1);
        Place p3 = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p1, 2));
        t.addInputArc(new ZeroArc(p2));
        t.addOutputArc(new OutputArc(p3, 1));

        assertFalse(t.fire());
        assertEquals(3, p1.getTokens());
        assertEquals(1, p2.getTokens());
        assertEquals(0, p3.getTokens());
    }

    @Test
    void testOnlyOneArcInEachDirection() {
        Place p = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p, 1));
        assertThrows(IllegalStateException.class, () -> t.addInputArc(new ZeroArc(p)));
        t.addOutputArc(new OutputArc(p, 1));
        assertThrows(IllegalStateException.class, () -> t.addOutputArc(new OutputArc(p, 2)));
    }

    @Test
    void testRemoveArc() {
        Place p = new Place(0);
        Transition t = new Transition();
        SimpleArc arc = new SimpleArc(p, 1);
        t.addInputArc(arc);

        assertFalse(t.isFireable());
        assertTrue(t.removeArc(arc));
        assertTrue(t.isFireable());
        assertFalse(t.removeArc(arc));
    }

    @Test
    void testRemoveArcsOfPlace() {
        Place p1 = new Place(0);
        Place p2 = new Place(0);
        Transition t = new Transition();
        t.addInputArc(new SimpleArc(p1, 1));
        t.addOutputArc(new OutputArc(p1, 1));
        t.addOutputArc(new OutputArc(p2, 1));

        t.removeArcsOf(p1);
        assertTrue(t.fire());
        assertEquals(0, p1.getTokens());
        assertEquals(1, p2.getTokens());
    }
}