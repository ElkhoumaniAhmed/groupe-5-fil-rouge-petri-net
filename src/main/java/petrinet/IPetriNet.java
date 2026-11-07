package petrinet;

/**
 * Operations available on a Petri net.
 */
public interface IPetriNet {

    // Create a place with an initial number of tokens
    Place addPlace(int tokens);

    // Create an empty transition
    Transition addTransition();

    // Arc place -> transition that consumes "weight" tokens
    SimpleArc addSimpleArc(Place place, Transition transition, int weight);

    // Arc place -> transition active only if the place is empty
    ZeroArc addZeroArc(Place place, Transition transition);

    // Arc place -> transition that empties the place
    EmptyingArc addEmptyingArc(Place place, Transition transition);

    // Arc transition -> place that produces "weight" tokens
    OutputArc addOutputArc(Transition transition, Place place, int weight);

    // Remove a place and all its arcs
    void removePlace(Place place);

    // Remove a transition (and its arcs with it)
    void removeTransition(Transition transition);

    // Remove a single arc
    void removeArc(Arc arc);

    // Try to fire a transition; returns true if it fired
    boolean fire(Transition transition);
}
