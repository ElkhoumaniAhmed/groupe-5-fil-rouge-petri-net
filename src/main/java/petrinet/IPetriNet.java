package petrinet;

public interface IPetriNet {

    Place addPlace(int tokens);

    Transition addTransition();

    SimpleArc addSimpleArc(Place place, Transition transition, int weight);

    ZeroArc addZeroArc(Place place, Transition transition);

    EmptyingArc addEmptyingArc(Place place, Transition transition);

    OutputArc addOutputArc(Transition transition, Place place, int weight);

    void removePlace(Place place);

    void removeTransition(Transition transition);

    void removeArc(Arc arc);

    boolean fire(Transition transition);
}