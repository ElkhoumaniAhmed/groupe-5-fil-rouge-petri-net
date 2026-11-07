package petrinet;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Petri net: stores places and transitions
 * and creates the arcs between them.
 */
public class PetriNet implements IPetriNet {

    private List<Place> places = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    // Create a place and add it to the net
    @Override
    public Place addPlace(int tokens) {
        Place place = new Place(tokens);
        places.add(place);
        return place;
    }

    // Create a transition and add it to the net
    @Override
    public Transition addTransition() {
        Transition transition = new Transition();
        transitions.add(transition);
        return transition;
    }

    // Create a simple input arc (place -> transition)
    @Override
    public SimpleArc addSimpleArc(Place place, Transition transition, int weight) {
        checkElements(place, transition);
        SimpleArc arc = new SimpleArc(place, weight);
        transition.addInputArc(arc);
        return arc;
    }

    // Create a zero input arc (place -> transition)
    @Override
    public ZeroArc addZeroArc(Place place, Transition transition) {
        checkElements(place, transition);
        ZeroArc arc = new ZeroArc(place);
        transition.addInputArc(arc);
        return arc;
    }

    // Create an emptying input arc (place -> transition)
    @Override
    public EmptyingArc addEmptyingArc(Place place, Transition transition) {
        checkElements(place, transition);
        EmptyingArc arc = new EmptyingArc(place);
        transition.addInputArc(arc);
        return arc;
    }

    // Create an output arc (transition -> place)
    @Override
    public OutputArc addOutputArc(Transition transition, Place place, int weight) {
        checkElements(place, transition);
        OutputArc arc = new OutputArc(place, weight);
        transition.addOutputArc(arc);
        return arc;
    }

    // Remove the place, then remove its arcs from every transition
    @Override
    public void removePlace(Place place) {
        if (!places.remove(place)) {
            throw new IllegalArgumentException("This place is not in the Petri net");
        }
        for (Transition transition : transitions) {
            transition.removeArcsOf(place);
        }
    }

    // Remove the transition (its arcs are stored inside it, so they go too)
    @Override
    public void removeTransition(Transition transition) {
        if (!transitions.remove(transition)) {
            throw new IllegalArgumentException("This transition is not in the Petri net");
        }
    }

    // Find the transition that owns the arc and remove it
    @Override
    public void removeArc(Arc arc) {
        for (Transition transition : transitions) {
            if (transition.removeArc(arc)) {
                return;
            }
        }
        throw new IllegalArgumentException("This arc is not in the Petri net");
    }

    // Fire a transition that belongs to this net
    @Override
    public boolean fire(Transition transition) {
        if (!transitions.contains(transition)) {
            throw new IllegalArgumentException("This transition is not in the Petri net");
        }
        return transition.fire();
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    // Check that the place and the transition belong to this net
    private void checkElements(Place place, Transition transition) {
        if (!places.contains(place)) {
            throw new IllegalArgumentException("This place is not in the Petri net");
        }
        if (!transitions.contains(transition)) {
            throw new IllegalArgumentException("This transition is not in the Petri net");
        }
    }
}
