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

// local
import com.github.bjarneh.utilz.io;

/**
 * Wrapper functions for Java's MessageDigest library.
 *
 * <b>note</b>: There is a lot of comments here since this is
 * meant to be a library with some documentation, i.e. make 
 * sure you turn on your comment folding before you start 
 * reading this file.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class message{

    // we only have functions
    private message(){}

    ////////////////////////////
    // checksum utility funcs //
    ////////////////////////////

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

    /**
     * Alias for handy.digest(io.raw(stream), "MD5").
     * @param stream to read bytes from
     * @return md5-digest for input bytes
     */
    public static byte[] md5(InputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(io.raw(stream));
    }

    /**
     * Alias for handy.digest(io.raw(stream), "MD5").
     * @param stream to read bytes from
     * @return md5-digest for input bytes
     */
    public static byte[] md5(FileInputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(io.raw(stream));
    }

    /**
     * Alias for handy.digest(io.raw(file), "MD5").
     * @param file object of input file
     * @return md5-digest for input file
     */
    public static byte[] md5(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(io.raw(file));
    }
    /**
     * Alias for handy.digest(io.raw(fname), "MD5").
     * @param fname path name of input file
     * @return md5-digest for input file
     */
    public static byte[] md5(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(io.raw(fname));
    }

    /**
     * Alias for handy.digest(b, "MD5").
     * @param b input bytes
     * @return md5-digest for input bytes
     */
    public static byte[] md5(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "MD5");
    }

    /**
     * Alias for handy.digest(io.raw(stream), "SHA1").
     * @param stream to read bytes from
     * @return sha1-digest for input bytes
     */
    public static byte[] sha1(InputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(io.raw(stream));
    }

    /**
     * Alias for handy.digest(io.raw(stream), "SHA1").
     * @param stream to read bytes from
     * @return sha1-digest for input bytes
     */
    public static byte[] sha1(FileInputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(io.raw(stream));
    }

    /**
     * Alias for handy.digest(io.raw(file), "SHA1").
     * @param file object of input file
     * @return sha1-digest for input file
     */
    public static byte[] sha1(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(io.raw(file));
    }

    /**
     * Alias for handy.digest(io.raw(fname), "SHA1").
     * @param fname path name of input file
     * @return sha1-digest for input file
     */
    public static byte[] sha1(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(io.raw(fname));
    }

    /**
     * Alias for handy.digest(b, "SHA1").
     * @param b input bytes
     * @return sha1-digest for input bytes
     */
    public static byte[] sha1(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "SHA1");
    }

    /**
     * Wrapper for the Java message digest library.
     *
     * @param stream of bytes we base our message digest on
     * @param algorithm to use when creating the digest sum/hash
     * @return message digest for byte in input stream using algorithm
     */
    public static byte[] digest(InputStream stream, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(io.raw(stream), algorithm);
    }

    /**
     * Wrapper for the Java message digest library.
     *
     * @param stream of bytes we base our message digest on
     * @param algorithm to use when creating the digest sum/hash
     * @return message digest for byte in input stream using algorithm
     */
    public static byte[] digest(FileInputStream stream, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(io.raw(stream), algorithm);
    }

    /**
     * Wrapper for the Java message digest library.
     *
     * @param file of bytes we base our message digest on
     * @param algorithm to use when creating the digest sum/hash
     * @return message digest for byte in input stream using algorithm
     */
    public static byte[] digest(File file, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(io.raw(file), algorithm);
    }

    /**
     * Wrapper for the Java message digest library.
     *
     * @param fname bytes are used for message digest
     * @param algorithm to use when creating the digest sum/hash
     * @return message digest for byte in input stream using algorithm
     */
    public static byte[] digest(String fname, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(io.raw(fname), algorithm);
    }

}
