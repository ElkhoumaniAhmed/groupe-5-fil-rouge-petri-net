package petrinet;

/**
 * Zero (inhibitor) arc: only active when the place is empty.
 */
public class ZeroArc extends InputArc {

    public ZeroArc(Place place) {
        super(place, 1); // weight is not used by this arc
    }

    // Active only if the place has no tokens
    @Override
    public boolean isActive() {
        return getPlace().getTokens()==0;
    }

    // Does nothing: the place stays empty
    @Override
    public void trigger() {

    }
}
