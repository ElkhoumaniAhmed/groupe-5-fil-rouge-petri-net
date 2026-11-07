package petrinet;

/**
 * Arc going from a transition to a place: adds tokens when fired.
 */
public class OutputArc extends Arc {

    public OutputArc(Place place, int weight) {
        super(place, weight);
    }

    // Add "weight" tokens to the place
    @Override
    public void trigger() {
        getPlace().addTokens(getWeight());
    }
}
