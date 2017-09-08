import org.junit.Test;

import edu.gatech.oad.antlab.person.Person1;

import static org.junit.Assert.assertEquals;

/**
 * Unit test file for Person1.java
 * Created by warren on 9/8/2017.
 */

public class Person1Test {

    @Test
    public void checkInputIsShiftedTwice() {
        Person1 tester = new Person1("name");

        assertEquals("namecdefab", tester.toString("abcdef"));
    }

}
