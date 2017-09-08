import org.junit.Test;

import edu.gatech.oad.antlab.person.Person4;
import static org.junit.Assert.assertEquals;


/**
 * Created by vickyj on 9/8/17.
 * Tests Person4 calc() method.
 */


public class Person4Test {

    @Test
    public void checkEachIsOneGreater() {
        Person4 p4 = new Person4("abc");
        assertEquals("abcbcd", p4.toString("abc"));
    }


}
