//  Copyright © 2012 bjarneh
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.github.bjarneh.utilz;

// stdlib
import java.util.Hashtable;

/**
 * Global key-value pairs accessible through get/set functions.
 *
 * A place to store global values as key value pairs of different
 * types, only Strings' and some basic types are present, perhaps
 * an Object Hashtable would be a good idea as well.
 *
 * <pre>
 *
 * // Typical use:
 *
 * globals.set("number", 1);
 * globals.set("key", "value");
 *
 * // fetch them at some point
 *
 * String key = globals.getStr("key");
 * int number = globals.getInt("number");
 *
 * </pre>
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class globals {

    // One Hashtable for each type, Hashtable is used for thread-safety.

    // global string values
    private static Hashtable<String, String> strs = 
                          new Hashtable<String, String>();

    // global int values
    private static Hashtable<String, Integer> ints = 
                          new Hashtable<String, Integer>();

    // global long values
    private static Hashtable<String, Long> longs = 
                          new Hashtable<String, Long>();

    // global double values
    private static Hashtable<String, Double> doubles = 
                          new Hashtable<String, Double>();

    // global boolean values
    private static Hashtable<String, Boolean> bools = 
                          new Hashtable<String, Boolean>();


    // we only have functions
    private globals(){ }

    /**
     * Set a global string value.
     *
     * @param key name of global string value
     * @param val value of the global string value
     */
    public static void set(String key, String val){ strs.put(key, val); }

    /**
     * Set a global int value.
     *
     * @param key name of global int value
     * @param val value of the global int value
     */
    public static void set(String key, int val){ ints.put(key, val); }

    /**
     * Set a global long value.
     *
     * @param key name of global long value
     * @param val value of the global long value
     */
    public static void set(String key, long val){ longs.put(key, val); }

    /**
     * Set a global double value.
     *
     * @param key name of global double value
     * @param val value of the global double value
     */
    public static void set(String key, double val){ doubles.put(key, val); }

    /**
     * Set a global boolean.
     *
     * @param key name of global boolean
     * @param val value of the global boolean
     */
    public static void set(String key, boolean val){ bools.put(key, val); }

    /**
     * Return global string value
     *
     * @param key name of global string value
     * @return the value of the global variable named key
     */
    public static String getStr(String key){ return strs.get(key); }

    /**
     * Return global int value
     *
     * @param key name of global int value
     * @return the value of the global variable named key
     */
    public static int getInt(String key){ return ints.get(key); }

    /**
     * Return global long value
     *
     * @param key name of global long value
     * @return the value of the global variable named key
     */
    public static long getLong(String key){ return longs.get(key); }

    /**
     * Return global double value
     *
     * @param key name of global double value
     * @return the value of the global variable named key
     */
    public static double getDouble(String key){ return doubles.get(key); }

    /**
     * Return global boolean variable
     *
     * @param key name of global boolean
     * @return the value of the global variable named key
     */
    public static boolean getBoolean(String key){ return bools.get(key); }

}
