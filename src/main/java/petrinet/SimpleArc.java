package petrinet;

public class SimpleArc extends InputArc {

    public SimpleArc(Place place, int weight) {
        super(place, weight);
    }

    @Override
    public boolean isActive() {
        return getPlace().getTokens() >= getWeight();
    }

    @Override
    public void trigger() {
        getPlace().removeTokens(getWeight());
    }
}
