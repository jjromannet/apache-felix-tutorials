/*
 * Apache Felix OSGi tutorial.
**/

package tutorial.example8;

import tutorial.example2.service.DictionaryService;
import tutorial.example6.service.SpellChecker;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class re-implements the spell checker service of Example 6.
 * This service implementation behaves exactly like the one in
 * Example 6, specifically, it aggregates all available dictionary
 * services, monitors their dynamic availability, and only offers
 * the spell checker service if there are dictionary services
 * available. The service implementation is greatly simplified,
 * though, by using the Service Binder. Notice that there is no OSGi
 * references in the application code; instead, the metadata.xml
 * file describes the service dependencies, which is read by the
 * Service Binder to automatically manage the dependencies and
 * register the spell checker service as appropriate.
 **/
public class SpellCheckerImpl implements SpellChecker
{
    // List of service objects.
    private ArrayList m_svcObjList = new ArrayList();

    /**
     * This method is used by the Service Binder to add
     * new dictionaries to the spell checker service.
     * @param dictionary the dictionary to add to the spell
     *                   checker service.
     **/
    public void addDictionary(DictionaryService dictionary)
    {
        // Lock list and add service object.
        synchronized (m_svcObjList)
        {
            m_svcObjList.add(dictionary);
        }
    }

    /**
     * This method is used by the Service Binder to remove
     * dictionaries from the spell checker service.
     * @param dictionary the dictionary to remove from the spell
     *                   checker service.
     **/
    public void removeDictionary(DictionaryService dictionary)
    {
        // Lock list and remove service object.
        synchronized (m_svcObjList)
        {
            m_svcObjList.remove(dictionary);
        }
    }

    /**
     * Checks a given passage for spelling errors. A passage is any
     * number of words separated by a space and any of the following
     * punctuation marks: comma (,), period (.), exclamation mark (!),
     * question mark (?), semi-colon (;), and colon(:).
     * @param passage the passage to spell check.
     * @return An array of misspelled words or null if no
     *         words are misspelled.
     **/
    public String[] check(String passage)
    {
        // No misspelled words for an empty string.
        if ((passage == null) || (passage.length() == 0))
        {
            return null;
        }

        ArrayList errorList = new ArrayList();

        // Tokenize the passage using spaces and punctionation.
        StringTokenizer st = new StringTokenizer(passage, " ,.!?;:");

        // Lock the service list.
        synchronized (m_svcObjList)
        {
            // Loop through each word in the passage.
            while (st.hasMoreTokens())
            {
                String word = st.nextToken();

                boolean correct = false;

                // Check each available dictionary for the current word.
                for (int i = 0; (!correct) && (i < m_svcObjList.size()); i++)
                {
                    DictionaryService dictionary =
                            (DictionaryService) m_svcObjList.get(i);

                    if (dictionary.checkWord(word))
                    {
                        correct = true;
                    }
                }

                // If the word is not correct, then add it
                // to the incorrect word list.
                if (!correct)
                {
                    errorList.add(word);
                }
            }
        }

        // Return null if no words are incorrect.
        if (errorList.size() == 0)
        {
            return null;
        }

        // Return the array of incorrect words.
        return (String[]) errorList.toArray(new String[errorList.size()]);
    }
}