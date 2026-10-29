package petrinet;

import java.util.ArrayList;
import java.util.List;

public class Transition {

    private List<InputArc> inputArcs = new ArrayList<>();
    private List<OutputArc> outputArcs = new ArrayList<>();

    public void addInputArc(InputArc arc) {
        for (InputArc a : inputArcs) {
            if (a.getPlace() == arc.getPlace()) {
                throw new IllegalStateException("An arc from this place already exists");
            }
        }
        inputArcs.add(arc);
    }

    public void addOutputArc(OutputArc arc) {
        for (OutputArc a : outputArcs) {
            if (a.getPlace() == arc.getPlace()) {
                throw new IllegalStateException("An arc to this place already exists");
            }
        }
        outputArcs.add(arc);
    }

    public boolean isFireable() {
        for (InputArc arc : inputArcs) {
            if (!arc.isActive()) {
                return false;
            }
        }
        return true;
    }

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