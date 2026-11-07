package petrinet;

/**
 * Classic input arc: needs at least "weight" tokens and removes them.
 */
public class SimpleArc extends InputArc {

    public SimpleArc(Place place, int weight) {
        super(place, weight);
    }

    // Active if the place has enough tokens
    @Override
    public boolean isActive() {
        return getPlace().getTokens() >= getWeight();
    }

    // Remove "weight" tokens from the place
    @Override
    public void trigger() {
        getPlace().removeTokens(getWeight());
    }
}
