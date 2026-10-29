package petrinet;

public class OutputArc extends Arc {

    public OutputArc(Place place, int weight) {
        super(place, weight);
    }

    @Override
    public void trigger() {
        getPlace().addTokens(getWeight());
    }
}