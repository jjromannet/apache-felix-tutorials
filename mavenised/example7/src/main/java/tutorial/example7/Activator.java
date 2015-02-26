/*
 * Apache Felix OSGi tutorial.
**/

package tutorial.example7;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import tutorial.example6.service.SpellChecker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class implements a bundle that uses a spell checker
 * service to check the spelling of a passage. This bundle
 * is essentially identical to Example 5, in that it uses the
 * Service Tracker to monitor the dynamic availability of the
 * spell checker service. When starting this bundle, the thread
 * calling the start() method is used to read passages from
 * standard input. You can stop spell checking passages by
 * entering an empty line, but to start spell checking again
 * you must stop and then restart the bundle.
 */
public class Activator implements BundleActivator {
    // Bundle's context.
    private BundleContext m_context = null;
    // The service tacker object.
    private ServiceTracker m_tracker = null;

    /**
     * Implements BundleActivator.start(). Creates a Service
     * Tracker object to monitor spell checker services. Enters
     * a spell check loop where it reads passages from standard
     * input and checks their spelling using the spell checker service.
     * (NOTE: It is very bad practice to use the calling thread
     * to perform a lengthy process like this; this is only done
     * for the purpose of the tutorial.)
     *
     * @param context the framework context for the bundle.
     */
    public void start(BundleContext context) throws Exception {
        m_context = context;

        // Create a service tracker to monitor dictionary services.
        m_tracker = new ServiceTracker(
                m_context,
                m_context.createFilter(
                        "(objectClass=" + SpellChecker.class.getName() + ")"),
                null);
        m_tracker.open();

        try {
            System.out.println("Enter a blank line to exit.");
            String passage = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            // Loop endlessly.
            while (true) {
                // Ask the user to enter a passage.
                System.out.print("Enter passage: ");
                passage = in.readLine();

                // Get the selected dictionary service, if available.
                SpellChecker checker = (SpellChecker) m_tracker.getService();

                // If the user entered a blank line, then
                // exit the loop.
                if (passage.length() == 0) {
                    break;
                }
                // If there is no spell checker, then say so.
                else if (checker == null) {
                    System.out.println("No spell checker available.");
                }
                // Otherwise check passage and print misspelled words.
                else {
                    String[] errors = checker.check(passage);

                    if (errors == null) {
                        System.out.println("Passage is correct.");
                    } else {
                        System.out.println("Incorrect word(s):");
                        for (int i = 0; i < errors.length; i++) {
                            System.out.println("    " + errors[i]);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Implements BundleActivator.stop(). Does nothing since
     * the framework will automatically unget any used services.
     *
     * @param context the framework context for the bundle.
     */
    public void stop(BundleContext context) {
    }
}