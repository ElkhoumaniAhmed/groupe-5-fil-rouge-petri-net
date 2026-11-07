package petrinet;

/**
 * Arc going from a place to a transition.
 */
public abstract class InputArc extends Arc{

    public InputArc(Place place, int weight) {
        super(place, weight);
    }

    // True if this arc allows the transition to fire
    public abstract boolean isActive ();

}
