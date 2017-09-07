import org.junit.Test;
import org.junit.Assert.*;

import edu.gatech.oad.antlab.person.Person3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test file for Person3.java
 * Created by blane on 9/7/2017.
 */

public class Person3Test {

    @Test
    public void checkInputIsReversed() {
        Person3 tester = new Person3("abcdefg");

        assertEquals("abcdefg7654321", tester.toString("1234567"));
    }

}
