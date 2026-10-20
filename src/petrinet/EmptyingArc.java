package petrinet;

public class EmptyingArc extends InputArc{

    public EmptyingArc(Place place) {
        super(place,1);
    }

    @Override
    public boolean isActive() {
        return getPlace().getTokens()>=1;
    }

    @Override
    public void trigger() {
        getPlace().removeTokens(getPlace().getTokens());
    }
}
