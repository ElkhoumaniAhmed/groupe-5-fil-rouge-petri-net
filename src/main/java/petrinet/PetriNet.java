package petrinet;

import java.util.ArrayList;
import java.util.List;

public class PetriNet implements IPetriNet {

    private List<Place> places = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    @Override
    public Place addPlace(int tokens) {
        Place place = new Place(tokens);
        places.add(place);
        return place;
    }

    @Override
    public Transition addTransition() {
        Transition transition = new Transition();
        transitions.add(transition);
        return transition;
    }

    @Override
    public SimpleArc addSimpleArc(Place place, Transition transition, int weight) {
        checkElements(place, transition);
        SimpleArc arc = new SimpleArc(place, weight);
        transition.addInputArc(arc);
        return arc;
    }

    @Override
    public ZeroArc addZeroArc(Place place, Transition transition) {
        checkElements(place, transition);
        ZeroArc arc = new ZeroArc(place);
        transition.addInputArc(arc);
        return arc;
    }

    @Override
    public EmptyingArc addEmptyingArc(Place place, Transition transition) {
        checkElements(place, transition);
        EmptyingArc arc = new EmptyingArc(place);
        transition.addInputArc(arc);
        return arc;
    }

    @Override
    public OutputArc addOutputArc(Transition transition, Place place, int weight) {
        checkElements(place, transition);
        OutputArc arc = new OutputArc(place, weight);
        transition.addOutputArc(arc);
        return arc;
    }

    @Override
    public void removePlace(Place place) {
        if (!places.remove(place)) {
            throw new IllegalArgumentException("This place is not in the Petri net");
        }
        for (Transition transition : transitions) {
            transition.removeArcsOf(place);
        }
    }

    @Override
    public void removeTransition(Transition transition) {
        if (!transitions.remove(transition)) {
            throw new IllegalArgumentException("This transition is not in the Petri net");
        }
    }

    @Override
    public void removeArc(Arc arc) {
        for (Transition transition : transitions) {
            if (transition.removeArc(arc)) {
                return;
            }
        }
        throw new IllegalArgumentException("This arc is not in the Petri net");
    }

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

    private void checkElements(Place place, Transition transition) {
        if (!places.contains(place)) {
            throw new IllegalArgumentException("This place is not in the Petri net");
        }
        if (!transitions.contains(transition)) {
            throw new IllegalArgumentException("This transition is not in the Petri net");
        }
    }
}