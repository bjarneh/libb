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

package com.github.bjarneh.handy.io;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Some IO utility that could come in handy more than once.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class Read {

    /**
     * In memory raw file slurp.
     *
     * It should be noted that I'm just guessing that this is the
     * best way to do things based on votes from stackoverflow,
     * which could very well be wrong; but if it is I'm not alone :-)
     *
     * stackoverflow question: 326390
     *
     * @param stream to slurp and return as raw bytes
     * @return a byte array containing the bytes of the stream
     */
    public static byte[] raw(FileInputStream stream) throws IOException {
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb =
                fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            if(bb.hasArray()){
                return bb.array();
            }else{
                byte[] b = new byte[(int)fc.size()];
                bb.get(b);
                return b;
            }
        }
        finally {
            stream.close();
        }
    }

    /**
     * In memory raw file slurp.
     *
     * It should be noted that I'm just guessing that this is the
     * best way to do things based on votes from stackoverflow,
     * which could very well be wrong; but if it is I'm not alone :-)
     *
     * stackoverflow question: 326390
     *
     * @param fname file-name to slurp and return as raw bytes
     * @return a byte array containing the bytes of the file 
     */
    public static byte[] raw(String fname) throws IOException {
        return raw(new File(fname));
    }

    /**
     * In memory raw file slurp.
     *
     * It should be noted that I'm just guessing that this is the
     * best way to do things based on votes from stackoverflow,
     * which could very well be wrong; but if it is I'm not alone :-)
     *
     * stackoverflow question: 326390
     *
     * @param file file object to slurp and return as raw bytes
     * @return a byte array containing the bytes of the file 
     */
    public static byte[] raw(File file) throws IOException {
        return raw(new FileInputStream(file));
    }
}
