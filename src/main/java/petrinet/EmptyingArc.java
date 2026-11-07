package petrinet;

/**
 * Emptying arc: removes all tokens from the place.
 */
public class EmptyingArc extends InputArc{

    public EmptyingArc(Place place) {
        super(place,1); // weight is not used by this arc
    }

    // Active if the place has at least one token
    @Override
    public boolean isActive() {
        return getPlace().getTokens()>=1;
    }

    // Remove every token of the place
    @Override
    public void trigger() {
        getPlace().removeTokens(getPlace().getTokens());
    }
}
