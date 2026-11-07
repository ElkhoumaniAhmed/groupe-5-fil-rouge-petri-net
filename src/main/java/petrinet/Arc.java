package petrinet;

/**
 * Base class for every arc: links a place to a transition with a weight.
 */
public abstract class Arc {

    private Place place;   // place connected to this arc
    private int weight;    // number of tokens moved by this arc

    public Arc(Place place, int weight) {
        this.place = place;
        // weight must be strictly positive
        if (weight <= 0) {
            throw new IllegalArgumentException("The weight must be > 0");
        }
        this.weight = weight;
    }

    public Place getPlace() {
        return place;
    }

    public int getWeight() {
        return weight;
    }

    // Change the weight (still must be > 0)
    public void setWeight(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("The weight must be > 0");
        }
        this.weight = weight;
    }

    // Apply the effect of the arc on its place when the transition fires
    public abstract void trigger();
}
