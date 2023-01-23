package org.example.FilmRentService;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$Junit-BEGIN$
        suite.addTestSuite(FilmTest.class);
        suite.addTestSuite(PersonTest.class);
        suite.addTestSuite(FilmRentServiceTest.class);
        //$Junit-END$
        return suite;
    }
}
