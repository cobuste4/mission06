package edu.isu.cs.cs3308;

import java.util.ArrayList;
import java.util.List;

public class SpellCheckerImpl implements SpellChecker {
    /**
     * Checks the spelling of the given string.
     *
     * @param s The string to check the spelling of
     * @return A list of alternatives, if the list is length 1 containing the same value as s, then the provided word was correctly spelled. Else it was not.
     */
    @Override
    public List<String> check(String s) {
        List<String> temp = new ArrayList<>();
        temp.add("one");
        temp.add("two");
        temp.add("three");
        return temp;
    }

    // Add the dict file to a Set.
    // s will be a single word. Compare a word with the dictionary. Return s if spelled correctly.
    // If not correct, run functions such as swapping chars and adding and removing chars, testing against the dictionary.
    // Common ways to misspell:
    // -- Swapping adjacent characters in a word
    // -- Inserting a single character in between two adjacent characters in a word
    // -- Deleting a single character from a word
    // -- Replacing a character in a word with another character
    // Add each dictionary word to a List and return that List at the end.

}
