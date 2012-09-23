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

package com.github.bjarneh.handy.sec;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// local
import com.github.bjarneh.handy.io.Read;

/**
 * Some utility functions to fetch security hashes.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class Hash{

    public static byte[] md5(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(Read.raw(fname));
    }

    public static byte[] md5(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(Read.raw(file));
    }

    public static byte[] md5(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "MD5");
    }

    public static byte[] sha1(FileInputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(Read.raw(stream));
    }

    public static byte[] sha1(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(Read.raw(file));
    }

    public static byte[] sha1(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(Read.raw(fname));
    }

    public static byte[] sha1(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "SHA1");
    }

    public static byte[] digest(FileInputStream stream, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(Read.raw(stream), algorithm);
    }

    public static byte[] digest(File file, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(Read.raw(file), algorithm);
    }

    public static byte[] digest(String fname, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(Read.raw(fname), algorithm);
    }

    /**
     * Compute a security hash of an array of bytes.
     *
     * This function is basically just a wrapper for the
     * standard library {@link MessageDigest} methods.
     *
     * @param b bytes to base the hash on
     * @param algorithm which message digest algorithm to use
     */
    public static byte[] digest(byte[] b, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.reset();
        md.update(b);
        return md.digest();
    }

    // NOTE: this is pretty shitty
    public static String toHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++){
            sb.append(String.format("%02x", b[i]));
        }
        return sb.toString();
    }

}
