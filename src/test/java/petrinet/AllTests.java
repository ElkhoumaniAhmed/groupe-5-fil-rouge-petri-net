package petrinet;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({TestPlace.class, TestArc.class, TestTransition.class, TestPetriNet.class})
public class AllTests {
}