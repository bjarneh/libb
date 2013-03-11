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
import java.net.URI;
import java.net.URLDecoder;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.UnsupportedEncodingException;


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
 * ArrayList&lt;String&gt; list = new ArrayList&lt;String&gt;();
 * handy.add( list, lines );
 *
 * String s = handy.dynReplace("&lt;b&gt;([^&lt;]+)&lt;\\/b&gt;", "&lt;b&gt;b1&lt;/b&gt; txt &lt;b&gt;b2&lt;/b&gt;");
 * s.equals("b1 txt b2"); // true
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
    public static String join(String sep, String ... elements){

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
     * Replace every occurence of regexp (containing match-group) with
     * match-group (sort of like $1 in some regexp implementations).
     *
     * @param regexpWithMatchGroup a pattern containing a match-group
     * @param str the string we want to replace stuff in
     * @return a string were pattern is replaced with match-group
     */
    public static String dynReplace(String regexpWithMatchGroup, String str){
        return dynReplace( Pattern.compile( regexpWithMatchGroup ), str);
    }

    /**
     * Replace every occurence of pattern (containing match-group) with
     * match-group (sort of like $1 in some regexp implementations).
     *
     * @param patternWithMatchGroup a pattern containing a match-group
     * @param str the string we want to replace stuff in
     * @return a string were pattern is replaced with match-group
     */
    public static String dynReplace(Pattern patternWithMatchGroup, String str){

        String tmp = str;
        Matcher m  = patternWithMatchGroup.matcher(str);

        while(m.find()){
            tmp = m.replaceFirst( m.group(1) );
            m = patternWithMatchGroup.matcher( tmp );
        }

        return tmp;
    }
    

    /**
     * Split URI query into a map.
     *
     * NOTE: this version overwrites entries with the same name,
     * i.e. each key has to be unique for this to work.
     *
     * @param uri an URI with a query
     * @param name of charset for decoding query
     * @return a map with key value pair from query or null on empty query
     */
    public static HashMap<String, String> uriQuery(URI uri, String enc)
        throws UnsupportedEncodingException
    {
        String question = uri.getRawQuery();

        if(question == null){ return null; }

        HashMap<String, String> map = new HashMap<String, String>();
        String[]      keyValuePairs = question.split("&");

        String key, value;
        String[] keyValue;

        for(String kv: keyValuePairs){
            keyValue = kv.split("=");
            if( keyValue.length == 2 ){
                key   = URLDecoder.decode(keyValue[0], enc);
                value = URLDecoder.decode(keyValue[1], enc);
                map.put(key, value);
            }
        }

        return map;
    }

    /**
     * Split URI query into a map with UTF-8 decoding of query.
     *
     * NOTE: this version overwrites entries with the same name,
     * i.e. each key has to be unique for this to work.
     *
     * @param uri an URI with a query
     * @return a map with key value pair from query or null for empty query
     */
    public static HashMap<String, String> uriQuery(URI uri)
        throws UnsupportedEncodingException
    {
        return uriQuery(uri, "UTF-8");
    }


    /**
     * Split URI query into a map.
     *
     * @param uri an URI with a query
     * @param name of charset for decoding query
     * @return a map with key value pairs from query or null on empty query
     */
    public static
        HashMap<String, ArrayList<String>> uriQueryFancy(URI uri, String enc)
        throws UnsupportedEncodingException
    {
        String question = uri.getRawQuery();

        if(question == null){ return null; }

        HashMap<String, ArrayList<String>> map = 
            new HashMap<String, ArrayList<String>>();

        String[]      keyValuePairs = question.split("&");

        String key, value;
        String[] keyValue;
        ArrayList<String> list;

        for(String kv: keyValuePairs){
            keyValue = kv.split("=");
            if( keyValue.length == 2 ){
                key   = URLDecoder.decode(keyValue[0], enc);
                value = URLDecoder.decode(keyValue[1], enc);
                if( map.containsKey(key) ){
                    list = map.get( key );
                    list.add( value );
                }else{
                    list = new ArrayList<String>();
                    list.add( value );
                    map.put(key, list);
                }
            }
        }

        return map;
    }

    /**
     * Split URI query into a map with UTF-8 decoding of query.
     *
     * @param uri an URI with a query
     * @return a map with key value pair from query or null for empty query
     */
    public static HashMap<String, ArrayList<String>> uriQueryFancy(URI uri)
        throws UnsupportedEncodingException
    {
        return uriQueryFancy(uri, "UTF-8");
    }

    /**
     * Utility function to use enumerations in foreach loop.
     *
     * @param en Enumeration of some sort
     * @return a list containing elements from Enumeration
     */
    public <T> ArrayList<T> toList(Enumeration<T> en){
        ArrayList<T> arr = new ArrayList<T>();
        if( en != null ){
            while(en.hasMoreElements()){
                arr.add( en.nextElement() );
            }
        }
        return arr;
    }
}

