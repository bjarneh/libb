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
import java.util.Set;
import java.util.List;
import java.util.Iterator;

/**
 * Collecting some handy functions that can be useful all over the place.
 *
 * <pre>
 *
 * // Typical use:
 *
 * String[] lines = getLines(); // assume this does something
 * String content = handy.join("\n", lines);
 *
 * System.out.printf("%s\n", content);
 *
 * </pre>
 *
 * <b>note</b>: There is a lot of comments here since this is
 * meant to be a library with some documentation, i.e. make 
 * sure you turn on your comment folding before you start 
 * reading this file.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class handy {

    // we only have functions
    private handy(){}

    /**
     * Join elements of Iterable&lt;? extends Object&gt; with
     * separator using {@link Object#toString}.
     *
     * @param sep the separator to use
     * @param elements to join into a String
     * @return all the elements joined by 'sep'
     */
    public static String joinAny(String sep,
                                 Iterable<? extends Object> elements){

        StringBuilder sb = new StringBuilder("");
        Iterator<? extends Object> it = elements.iterator();

        while( it.hasNext() ){
            sb.append(it.next().toString());
            if( it.hasNext() ){ sb.append(sep); }
        }

        return sb.toString();
    }

    /**
     * Join elements of Iterable&lt;String&gt; with separator to a String.
     *
     * @param sep the separator to use
     * @param elements to join into a String
     * @return all the elements joined by 'sep'
     */
    public static String join(String sep, Iterable<String> elements){

        StringBuilder sb = new StringBuilder("");
        Iterator<String> it = elements.iterator();

        while( it.hasNext() ){
            sb.append(it.next());
            if( it.hasNext() ){ sb.append(sep); }
        }

        return sb.toString();
    }

    /**
     * Join elements of String array with separator to a String.
     *
     * @param sep the separator to use
     * @param elements to join into a String
     * @return all the elements joined by 'sep'
     */
    public static String join(String sep, String[] elements){

        StringBuilder sb = new StringBuilder("");

        for(int i = 0; i < elements.length; i++){
            sb.append(elements[i]);
            if(i+1 < elements.length){
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    /**
     * Add array elements to a List of same type.
     * @param list that we would like to add array elements to
     * @param array that we would like to add to list
     */
    public static <T> void add(List<T> list, T[] array){
        for(T t: array){ list.add(t); }
    }

    /**
     * Add array elements to a Set of same type.
     * @param set that we would like to add array elements to
     * @param array that we would like to add to set
     */
    public static <T> void add(Set<T> set, T[] array){
        for(T t: array){ set.add(t); }
    }
}

