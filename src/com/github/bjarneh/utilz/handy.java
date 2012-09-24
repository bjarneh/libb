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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.LinkedList;

/**
 * An attempt at collecting common functions in one library.
 *
 * At present these things are included:
 *
 * <ol>
 * <li> path-walk </li> 
 * <li> file slurp </li> 
 * <li> security hashing </li> 
 * </ol>
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class handy {

    // this class contains functions not methods
    private handy(){}

    private static final String OS_SEP = File.separator;

    // filter that does zero filtering says yes to anything
    private static final FileFilter yesFilter = new FileFilter() {
        @Override public boolean accept(File file){
            return true;
        }
    };

    // filter that ignores common vcs directories (.bzr/.git/.hg/.cvs/.svn)
    private static final FileFilter vcsFilter = new FileFilter() {
        @Override public boolean accept(File file){
            if( file.isDirectory() ){
                String fname = file.getName();
                if(fname.equals(".git")||
                   fname.equals(".svn")||
                   fname.equals(".hg") ||
                   fname.equals(".bzr")||
                   fname.equals(".cvs"))
                {
                    return false;
                }
            }
            return true;
        }
    };

    ///////////////
    // path walk //
    ///////////////

    public static String[] pathWalk(String root, FileFilter filter){

        File fileRoot = new File(root);
        if( ! fileRoot.isDirectory() ){ return new String[0]; }

        LinkedList<String> list = new LinkedList<String>();
        pathWalk(pathClean(root), fileRoot, list, filter);

        String[] arr = new String[list.size()];
        list.toArray(arr);

        return arr;
    }

    public static String[] pathWalk(String root){
        return pathWalk(pathClean(root), yesFilter);
    }

    public static void pathWalk(String root,
                                File dir,
                                List<String> save,
                                FileFilter filter)
    {
        File[] ls = dir.listFiles(filter);

        for(File sub : ls){
            if(sub.isFile()){
                save.add(root + OS_SEP + sub.getName());
            }else if(sub.isDirectory()){
                pathWalk(root + OS_SEP + sub.getName(),
                         new File(dir, sub.getName()), save, filter);
            }
        }
    }

    //TODO remove duplicate slash etc perhaps..
    public static String pathClean(String path){
        while(path.length() > 1 && path.endsWith(OS_SEP)){
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static String fromSlash(String path){
        return path.replaceAll("/", OS_SEP);
    }


    ////////////////////
    // raw file slurp //
    ////////////////////

    /**
     * In memory raw file slurp.
     *
     * It should be noted that I'm just guessing that this is the
     * best way to do things based on votes from stackoverflow,
     * which could very well be wrong; but if it is I'm not alone :-)
     *
     * stackoverflow: 326390
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
     * 
     * Just an alias for handy.raw(new File(fname)).
     *
     * @param fname file-name to slurp and return as raw bytes
     * @return a byte array containing the bytes of the file 
     */
    public static byte[] raw(String fname) throws IOException {
        return raw(new File(fname));
    }

    /**
     * Just an alias for handy.raw(new FileInputStream(file)).
     *
     * @param file file object to slurp and return as raw bytes
     * @return a byte array containing the bytes of the file 
     */
    public static byte[] raw(File file) throws IOException {
        return raw(new FileInputStream(file));
    }



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

    public static byte[] md5(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(raw(fname));
    }

    public static byte[] md5(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return md5(raw(file));
    }

    public static byte[] md5(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "MD5");
    }

    public static byte[] sha1(FileInputStream stream)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(raw(stream));
    }

    public static byte[] sha1(File file)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(raw(file));
    }

    public static byte[] sha1(String fname)
        throws IOException, NoSuchAlgorithmException
    {
        return sha1(raw(fname));
    }

    public static byte[] sha1(byte[] b)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(b, "SHA1");
    }

    public static byte[] digest(FileInputStream stream, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(raw(stream), algorithm);
    }

    public static byte[] digest(File file, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(raw(file), algorithm);
    }

    public static byte[] digest(String fname, String algorithm)
        throws IOException, NoSuchAlgorithmException
    {
        return digest(raw(fname), algorithm);
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

