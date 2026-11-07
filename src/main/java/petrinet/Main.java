package petrinet;

/**
 * Small demo: builds a net with every arc type and fires it twice.
 */
public class Main {

    public static void main(String[] args) {
        PetriNet net = new PetriNet();

        // Places with their initial tokens
        Place p1 = net.addPlace(3);
        Place p2 = net.addPlace(0);
        Place p3 = net.addPlace(5);
        Place p4 = net.addPlace(0);
        Transition t = net.addTransition();

        // Connect the places to the transition
        net.addSimpleArc(p1, t, 2);   // needs 2 tokens in p1
        net.addZeroArc(p2, t);        // p2 must be empty
        net.addEmptyingArc(p3, t);    // empties p3
        net.addOutputArc(t, p4, 1);   // adds 1 token to p4

        System.out.println("Before: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());

        // First fire succeeds
        System.out.println("Fire 1: " + net.fire(t));
        System.out.println("After: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());

        // Second fire fails: p1 has 1 token and p3 is empty
        System.out.println("Fire 2: " + net.fire(t));
        System.out.println("After: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());
    }
}
