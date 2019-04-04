package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.SetImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/** Takes in a string and checks the spelling on each word.
 * @author Steve Coburn
 */
public class SpellCheckerImpl implements SpellChecker {
    private SetImpl<String> dict = new SetImpl<>();
    private char[] alphArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private List<String> toReturn = new ArrayList<>();

    public SpellCheckerImpl() {
        try {
            FileReader fr = new FileReader("data/en-US.dic");
            BufferedReader br = new BufferedReader(fr);
            for (String line; (line = br.readLine()) != null; ) {
                dict.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

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
            char temp = str.charAt(i);
            str.setCharAt(i, str.charAt(i + 1));
            str.setCharAt(i + 1, temp);
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
