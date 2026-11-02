package petrinet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestArc {

    @Test
    void testWeight() {
        Arc arc = new SimpleArc(new Place(0), 2);
        assertEquals(2, arc.getWeight());
        arc.setWeight(5);
        assertEquals(5, arc.getWeight());
    }

    @Test
    void testInvalidWeight() {
        Place p = new Place(0);
        assertThrows(IllegalArgumentException.class, () -> new SimpleArc(p, 0));
        assertThrows(IllegalArgumentException.class, () -> new OutputArc(p, -1));
        Arc arc = new SimpleArc(p, 2);
        assertThrows(IllegalArgumentException.class, () -> arc.setWeight(0));
        assertEquals(2, arc.getWeight());
    }

    @Test
    void testGetPlace() {
        Place p = new Place(0);
        Arc arc = new SimpleArc(p, 1);
        assertSame(p, arc.getPlace());
    }

    @Test
    void testSimpleArc() {
        Place p = new Place(1);
        SimpleArc arc = new SimpleArc(p, 2);
        assertFalse(arc.isActive());
        p.addTokens(1);
        assertTrue(arc.isActive());
        arc.trigger();
        assertEquals(0, p.getTokens());
    }

    @Test
    void testZeroArc() {
        Place p = new Place(0);
        ZeroArc arc = new ZeroArc(p);
        assertTrue(arc.isActive());
        arc.trigger();
        assertEquals(0, p.getTokens());
        p.addTokens(1);
        assertFalse(arc.isActive());
    }

    @Test
    void testEmptyingArc() {
        Place p = new Place(0);
        EmptyingArc arc = new EmptyingArc(p);
        assertFalse(arc.isActive());
        p.addTokens(4);
        assertTrue(arc.isActive());
        arc.trigger();
        assertEquals(0, p.getTokens());
    }

    @Test
    void testOutputArc() {
        Place p = new Place(1);
        OutputArc arc = new OutputArc(p, 3);
        arc.trigger();
        assertEquals(4, p.getTokens());
    }
}