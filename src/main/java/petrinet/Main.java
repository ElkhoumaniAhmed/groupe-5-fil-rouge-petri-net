package petrinet;

public class Main {

    public static void main(String[] args) {
        PetriNet net = new PetriNet();

        Place p1 = net.addPlace(3);
        Place p2 = net.addPlace(0);
        Place p3 = net.addPlace(5);
        Place p4 = net.addPlace(0);
        Transition t = net.addTransition();

        net.addSimpleArc(p1, t, 2);
        net.addZeroArc(p2, t);
        net.addEmptyingArc(p3, t);
        net.addOutputArc(t, p4, 1);

        System.out.println("Before: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());

        System.out.println("Fire 1: " + net.fire(t));
        System.out.println("After: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());

        System.out.println("Fire 2: " + net.fire(t));
        System.out.println("After: p1=" + p1.getTokens() + " p2=" + p2.getTokens()
                + " p3=" + p3.getTokens() + " p4=" + p4.getTokens());
    }
}