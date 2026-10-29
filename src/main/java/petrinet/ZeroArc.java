package petrinet;

public class ZeroArc extends InputArc {

    public ZeroArc(Place place) {
        super(place, 1);
    }

    @Override
    public boolean isActive() {
        return getPlace().getTokens()==0;
    }

    @Override
    public void trigger() {

    }
}
