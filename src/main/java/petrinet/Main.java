package petrinet;

public class Main {

    public static void main(String[] args) {
        Place p = new Place(3);
        System.out.println("Start: " + p.getTokens());

        p.addTokens(2);
        System.out.println("After adding 2: " + p.getTokens());

        p.removeTokens(4);
        System.out.println("After removing 4: " + p.getTokens());

        p.removeTokens(5);

        Place p1 = new Place(3);
        Place p2 = new Place(0);
        Place p3 = new Place(5);

        InputArc simple = new SimpleArc(p1, 2);
        InputArc zero = new ZeroArc(p2);
        InputArc emptying = new EmptyingArc(p3);

        System.out.println("Simple active: " + simple.isActive());
        System.out.println("Zero active: " + zero.isActive());
        System.out.println("Emptying active: " + emptying.isActive());

        simple.trigger();
        zero.trigger();
        emptying.trigger();

        System.out.println("p1: " + p1.getTokens());
        System.out.println("p2: " + p2.getTokens());
        System.out.println("p3: " + p3.getTokens());
    }
}
