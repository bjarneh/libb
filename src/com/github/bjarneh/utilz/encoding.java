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
import java.io.UnsupportedEncodingException;

/**
 * Convert between different character encodings.
 *
 * Utility functions, only a stub for now.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class encoding {

    // we only have functions
    private encoding(){}

    /**
     * Convert a byte array from one encoding to another.
     *
     * @param b bytes array in encoding fromCh
     * @param fromCh the encoding of the bytes in b
     * @param toCh the desired encoding of the returned array
     * @return a byte array in desired encoding
     */
    public static byte[] fromTo(byte[] b, String fromCh, String toCh)
        throws UnsupportedEncodingException
    {
        return new String(b, fromCh).getBytes(toCh);
    }
}
