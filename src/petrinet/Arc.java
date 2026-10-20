package petrinet;

public abstract class Arc {

    private Place place;
    private int weight;

    public Arc(Place place, int weight) {
        this.place = place;
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

    public void setWeight(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("The weight must be > 0");
        }
        this.weight = weight;
    }

    public abstract void trigger();
}