package petrinet;

public abstract class InputArc extends Arc{

    public InputArc(Place place, int weight) {
        super(place, weight);
    }
    public abstract boolean isActive ();

}
