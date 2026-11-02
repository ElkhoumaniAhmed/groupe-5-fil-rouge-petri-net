package petrinet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPetriNet {

    private PetriNet net;

    @BeforeEach
    void setUp() {
        net = new PetriNet();
    }

    @Test
    void testAddPlaceAndTransition() {
        Place p = net.addPlace(2);
        Transition t = net.addTransition();
        assertEquals(2, p.getTokens());
        assertEquals(1, net.getPlaces().size());
        assertEquals(1, net.getTransitions().size());
        assertTrue(net.getPlaces().contains(p));
        assertTrue(net.getTransitions().contains(t));
    }

    @Test
    void testCycle() {
        Place p0 = net.addPlace(1);
        Place p1 = net.addPlace(0);
        Transition t0 = net.addTransition();
        Transition t1 = net.addTransition();
        net.addSimpleArc(p0, t0, 1);
        net.addOutputArc(t0, p1, 2);
        net.addSimpleArc(p1, t1, 1);
        net.addOutputArc(t1, p0, 2);

        assertTrue(t0.isFireable());
        assertFalse(t1.isFireable());
        assertTrue(net.fire(t0));
        assertEquals(0, p0.getTokens());
        assertEquals(2, p1.getTokens());
        assertFalse(net.fire(t0));
        assertTrue(net.fire(t1));
        assertEquals(2, p0.getTokens());
        assertEquals(1, p1.getTokens());
    }

    @Test
    void testZeroAndEmptyingArcs() {
        Place p1 = net.addPlace(0);
        Place p2 = net.addPlace(4);
        Transition t = net.addTransition();
        net.addZeroArc(p1, t);
        net.addEmptyingArc(p2, t);

        assertTrue(net.fire(t));
        assertEquals(0, p1.getTokens());
        assertEquals(0, p2.getTokens());
    }

    @Test
    void testElementsNotInNet() {
        Place inside = net.addPlace(0);
        Transition t = net.addTransition();
        Place outside = new Place(0);
        Transition other = new Transition();

        assertThrows(IllegalArgumentException.class, () -> net.addSimpleArc(outside, t, 1));
        assertThrows(IllegalArgumentException.class, () -> net.addOutputArc(other, inside, 1));
        assertThrows(IllegalArgumentException.class, () -> net.fire(other));
    }

    @Test
    void testChangeWeightAndTokens() {
        Place p = net.addPlace(1);
        Transition t = net.addTransition();
        SimpleArc arc = net.addSimpleArc(p, t, 1);

        arc.setWeight(3);
        assertFalse(net.fire(t));
        p.addTokens(2);
        assertTrue(net.fire(t));
        assertEquals(0, p.getTokens());
    }

    @Test
    void testRemovePlace() {
        Place p = net.addPlace(0);
        Transition t = net.addTransition();
        net.addSimpleArc(p, t, 1);

        assertFalse(t.isFireable());
        net.removePlace(p);
        assertFalse(net.getPlaces().contains(p));
        assertTrue(t.isFireable());
        assertThrows(IllegalArgumentException.class, () -> net.removePlace(p));
    }

    @Test
    void testRemoveTransition() {
        Transition t = net.addTransition();
        net.removeTransition(t);
        assertFalse(net.getTransitions().contains(t));
        assertThrows(IllegalArgumentException.class, () -> net.fire(t));
        assertThrows(IllegalArgumentException.class, () -> net.removeTransition(t));
    }

    @Test
    void testRemoveArc() {
        Place p = net.addPlace(0);
        Transition t = net.addTransition();
        SimpleArc arc = net.addSimpleArc(p, t, 1);

        net.removeArc(arc);
        assertTrue(t.isFireable());
        assertThrows(IllegalArgumentException.class, () -> net.removeArc(arc));
    }
}