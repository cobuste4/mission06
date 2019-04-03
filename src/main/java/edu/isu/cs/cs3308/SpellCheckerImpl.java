package edu.isu.cs.cs3308;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class SpellCheckerImpl implements SpellChecker {

    private HashSet<String> dict = new HashSet<>();
    private char[] alphArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private List<String> toReturn = new ArrayList<>();

    // Default constructor will try to read the dictionary file into a HashSet
    public SpellCheckerImpl() {
        try {
            FileReader fr = new FileReader("data/en-US.dic");
            BufferedReader br = new BufferedReader(fr);
            for (String line; (line = br.readLine()) != null; ) {
                dict.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Checks the spelling of the given string.
     *
     * @param s The string to check the spelling of
     * @return A list of alternatives, if the list is length 1 containing the same value as s, then the provided word was correctly spelled. Else it was not.
     */
    @Override
    public List<String> check(String s) {
        toReturn.clear();
        toReturn.add(s);

        if (dict.contains(s)) return toReturn;

        swapAdjacentChars(s);
        insertChar(s);
        deleteChar(s);
        replaceChar(s);

        if (toReturn.size() == 1) toReturn.add("No suggestions");

        // Clears duplicates from the list of suggestions
        for (int outer = 0; outer < toReturn.size(); outer++) {
            for (int inner = outer + 1; inner < toReturn.size(); inner++) {
                if (toReturn.get(outer).equals(toReturn.get(inner))) {
                    toReturn.remove(inner);
                }
            }
        }
        return toReturn;
    }

    /**
     * Swaps adjacent chars in a word and checks to see if the resulting word is in the dictionary
     *
     * @param s The string containing the word to check
     */
    private void swapAdjacentChars(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            StringBuilder str = new StringBuilder(s);
            str.setCharAt(i, str.charAt(i + 1));
            str.setCharAt(i + 1, str.charAt(i));
            if (dict.contains(str.toString())) toReturn.add(str.toString());
        }
    }

    /**
     * Inserts a char at each position in a word and checks to see if the resulting word is in the dictionary
     *
     * @param s The string containing the word to check
     */
    private void insertChar(String s) {
        for (int i = 1; i < s.length(); i++) {
            for (char c : alphArray) {
                StringBuilder str = new StringBuilder(s);
                str.insert(i, c);
                if (dict.contains(str.toString())) toReturn.add(str.toString());
            }
        }
    }

    /**
     * Deletes a char from each position in the word and checks to see if the resulting word is in the dictionary
     *
     * @param s The string containing the word to check
     */
    private void deleteChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            StringBuilder str = new StringBuilder(s);
            str.deleteCharAt(i);
            if (dict.contains(str.toString())) toReturn.add(str.toString());
        }
    }

    /**
     * Replaces a char from each position in the word and checks to see if the resulting word is in the dictionary
     *
     * @param s The string containing the word to check
     */
    private void replaceChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (char c : alphArray) {
                StringBuilder str = new StringBuilder(s);
                str.setCharAt(i, c);
                if (dict.contains(str.toString())) toReturn.add(str.toString());
            }
        }
    }
}
