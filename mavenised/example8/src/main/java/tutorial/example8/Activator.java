/*
 * Apache Felix OSGi tutorial.
**/

package tutorial.example8;

import org.apache.felix.servicebinder.GenericActivator;

/**
 * This example re-implements the spell checker service of
 * Example 6 using the Service Binder. The Service Binder
 * greatly simplifies creating OSGi applications by
 * essentially eliminating the need to write OSGi-related
 * code; instead of writing OSGi code for your bundle, you
 * create a simple XML file to describe your bundle's service
 * dependencies. This class extends the generic bundle activator;
 * it does not provide any additional functionality. All
 * functionality for service-related tasks, such as look-up and
 * binding, is handled by the generic activator base class using
 * data from the metadata.xml file. All application
 * functionality is defined in the SpellCheckerImpl.java
 * file.
 **/

public class Activator extends GenericActivator
{
}