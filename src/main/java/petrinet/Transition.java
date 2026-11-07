package petrinet;

import java.util.ArrayList;
import java.util.List;

/**
 * A transition consumes tokens through its input arcs
 * and produces tokens through its output arcs.
 */
public class Transition {

    private List<InputArc> inputArcs = new ArrayList<>();   // arcs place -> transition
    private List<OutputArc> outputArcs = new ArrayList<>(); // arcs transition -> place

    // Add an input arc (only one input arc per place)
    public void addInputArc(InputArc arc) {
        for (InputArc a : inputArcs) {
            if (a.getPlace() == arc.getPlace()) {
                throw new IllegalStateException("An arc from this place already exists");
            }
        }
        inputArcs.add(arc);
    }

    // Add an output arc (only one output arc per place)
    public void addOutputArc(OutputArc arc) {
        for (OutputArc a : outputArcs) {
            if (a.getPlace() == arc.getPlace()) {
                throw new IllegalStateException("An arc to this place already exists");
            }
        }
        outputArcs.add(arc);
    }

    // Remove an arc; returns true if it was found
    public boolean removeArc(Arc arc) {
        if (inputArcs.remove(arc)) {
            return true;
        }
        return outputArcs.remove(arc);
    }

    // Remove all arcs linked to the given place
    public void removeArcsOf(Place place) {
        // loop backwards so removing does not skip elements
        for (int i = inputArcs.size() - 1; i >= 0; i--) {
            if (inputArcs.get(i).getPlace() == place) {
                inputArcs.remove(i);
            }
        }
        for (int i = outputArcs.size() - 1; i >= 0; i--) {
            if (outputArcs.get(i).getPlace() == place) {
                outputArcs.remove(i);
            }
        }
    }

    // Fireable only if every input arc is active
    public boolean isFireable() {
        for (InputArc arc : inputArcs) {
            if (!arc.isActive()) {
                return false;
            }
        }
        return true;
    }

    // Fire the transition: consume inputs then produce outputs.
    // Returns false (and changes nothing) if not fireable.
    public boolean fire() {
        if (!isFireable()) {
            return false;
        }
        for (InputArc arc : inputArcs) {
            arc.trigger();
        }
        for (OutputArc arc : outputArcs) {
            arc.trigger();
        }
        return true;
    }
}
