package tutorial.example6;

import tutorial.example2.service.DictionaryService;
import tutorial.example6.service.SpellChecker;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A private inner class that implements a spell checker service;
 * see SpellChecker for details of the service.
 * Created by romanj on 26/02/2015.
 */
public class SpellCheckerImpl implements SpellChecker {

    private final Activator activator;

    public SpellCheckerImpl(Activator activator) {
        this.activator = activator;
    }

    /**
     * Implements SpellChecker.check(). Checks the
     * given passage for misspelled words.
     *
     * @param passage the passage to spell check.
     * @return An array of misspelled words or null if no
     * words are misspelled.
     */
    public String[] check(String passage) {
        // No misspelled words for an empty string.
        if ((passage == null) || (passage.length() == 0)) {
            return null;
        }

        ArrayList errorList = new ArrayList();

        // Tokenize the passage using spaces and punctionation.
        StringTokenizer st = new StringTokenizer(passage, " ,.!?;:");

        // Lock the service list.
        synchronized (activator.getM_refList()) {
            // Loop through each word in the passage.
            while (st.hasMoreTokens()) {
                String word = st.nextToken();

                boolean correct = false;

                // Check each available dictionary for the current word.
                for (int i = 0; (!correct) && (i < activator.getM_refList().size()); i++) {
                    DictionaryService dictionary =
                            (DictionaryService) activator.getM_refToObjMap().get(activator.getM_refList().get(i));

                    if (dictionary.checkWord(word)) {
                        correct = true;
                    }
                }

                // If the word is not correct, then add it
                // to the incorrect word list.
                if (!correct) {
                    errorList.add(word);
                }
            }
        }

        // Return null if no words are incorrect.
        if (errorList.size() == 0) {
            return null;
        }

        // Return the array of incorrect words.
        return (String[]) errorList.toArray(new String[errorList.size()]);
    }
}