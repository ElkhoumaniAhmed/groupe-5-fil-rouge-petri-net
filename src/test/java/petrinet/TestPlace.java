package petrinet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlace {

    private Place place;

    @BeforeEach
    void setUp() {
        place = new Place(3);
    }

    @Test
    void testConstructor() {
        assertEquals(3, place.getTokens());
        assertEquals(0, new Place(0).getTokens());
    }

    @Test
    void testConstructorNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Place(-1));
    }

    @Test
    void testAddTokens() {
        place.addTokens(2);
        assertEquals(5, place.getTokens());
        place.addTokens(0);
        assertEquals(5, place.getTokens());
    }

    @Test
    void testAddNegativeTokens() {
        assertThrows(IllegalArgumentException.class, () -> place.addTokens(-1));
        assertEquals(3, place.getTokens());
    }

    @Test
    void testRemoveTokens() {
        place.removeTokens(1);
        assertEquals(2, place.getTokens());
        place.removeTokens(2);
        assertEquals(0, place.getTokens());
    }

    @Test
    void testRemoveTooManyTokens() {
        assertThrows(IllegalArgumentException.class, () -> place.removeTokens(4));
        assertEquals(3, place.getTokens());
    }

    @Test
    void testRemoveNegativeTokens() {
        assertThrows(IllegalArgumentException.class, () -> place.removeTokens(-1));
        assertEquals(3, place.getTokens());
    }
}