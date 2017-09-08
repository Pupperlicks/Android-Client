package edu.gatech.oad.antlab.person;

import java.nio.charset.StandardCharsets;

/**
 *  A simple class for person 4
 *  returns their name and a
 *  modified string
 *
 *  @author Victoria L. Joh
 *  @version 1.2
 */
public class Person4 {
  /** Holds the persons real name */
  private String name;
    /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
  public Person4(String pname) {
    name = pname;
  }
    /**
     * This method should return a string
     * where each character is 1 greater
     * than its previous value.  So
     * given "abc123" it should return
     * "bcd234".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    private String calc(String input) {
        int index = 0;
        for (char c : input.toCharArray()) {
            byte[] charBytes = input.getBytes(StandardCharsets.US_ASCII);
            c = input.charAt(charBytes[index] + 1);
            index++;
            input = input.replaceAll(input, String.valueOf(c));
        }
        return input;
    }

    /**
     * Return a string rep of this object
     * that varies with an input string
     *
     * @param input the varying string
     * @return the string representing the
     *         object
     */
    public String toString(String input) {
      return name + calc(input);
    }

}
