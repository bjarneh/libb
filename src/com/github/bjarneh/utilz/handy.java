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
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


/**
 * Collecting some handy functions that can be useful all over the place.
 *
 * Note to self, try to keep this class as independant as possible,
 * i.e. no dependencies outside the stdlib.
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
     * Get the hex representation of a byte array.
     *
     * NOTE: this is pretty shitty
     *
     * @param  b input bytes
     * @return the hex representation of the input bytes
     */
    public static String toHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++){
            sb.append(String.format("%02x", b[i]));
        }
        return sb.toString();
    }

}

