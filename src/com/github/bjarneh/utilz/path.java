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
import java.util.LinkedList;

// local
import com.github.bjarneh.utilz.handy;

/**
 * Some common path utility functions.
 *
 * <pre>
 *
 *  // Typical use:
 *
 *  String validPathOnYourOs;
 *
 *  validPathOnYourOs = path.fromSlash("/some/file/name");
 *  validPathOnYourOs = path.join(new String[]{"some","dir","name"});
 *
 *  String[] files    = path.walk(validPathOnYourOs);
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

public class path {

    // we only have functions
    private path(){}

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


    /**
     * Fetch the root relative path names of all/some files under root.
     *
     * @param root of the file tree you would like to fetch
     * @param filter out parts of your file tree using a FileFilter
     * @return a list of path names under root
     */
    public static String[] walk(String root, FileFilter filter){

        File fileRoot = new File(root);
        if( ! fileRoot.isDirectory() ){ return new String[0]; }

        LinkedList<String> list = new LinkedList<String>();
        walk(clean(root), fileRoot, list, filter);

        String[] arr = new String[list.size()];
        list.toArray(arr);

        return arr;
    }

    /**
     * Fetch the root relative path names of all files under root.
     *
     * @param root of the file tree you would like to fetch
     * @return a list of path names under root
     */
    public static String[] walk(String root){
        return walk(clean(root), yesFilter);
    }

    /**
     * Fetch the root relative path names of all files under root,
     * and ignore VCS dirs (.bzr/.git/.hg/.svn/.cvs).
     *
     * @param root of the file tree you would like to fetch
     * @return a list of path names under root, ignoring VCS dirs
     */
    public static String[] walkVCS(String root){
        return walk(clean(root), vcsFilter);
    }


    private static void walk(String root,
                             File dir,
                             LinkedList<String> save,
                             FileFilter filter)
    {
        File[] ls = dir.listFiles(filter);

        for(File sub : ls){
            if(sub.isFile()){
                save.add(root + OS_SEP + sub.getName());
            }else if(sub.isDirectory()){
                walk(root + OS_SEP + sub.getName(),
                     new File(dir, sub.getName()), save, filter);
            }
        }
    }

    /**
     * This function cleans up a path name, by removing
     * duplicates and ending File.separator.
     *
     * @param p a path name
     * @return a path with no ending or duplicate File.separator
     */
    public static String clean(String p){

        while(p.length() > 1 && p.endsWith(OS_SEP)){
            p = p.substring(0, p.length() - 1);
        }

        StringBuilder sb = new StringBuilder();
        char[] pChars = p.toCharArray();
        char prev = ' ';
        char sep  = File.separatorChar;

        for(int i = 0; i < pChars.length; i++){
            if(pChars[i] == sep && prev == sep){
                continue;
            }
            sb.append(pChars[i]);
            prev = pChars[i];
        }

        return sb.toString();
    }

    /**
     * Replace forward slash with File.separator.
     *
     * @param p name you would like to OS-ify
     * @return a path name that fits your OS
     */
    public static String fromSlash(String p){
        return p.replaceAll("/", OS_SEP);
    }

    /**
     * Join with File.separator as separator.
     * @param elements to join into a String
     * @return a File.separator joined String
     */
    public static String join(Iterable<String> elements){
        return handy.join(OS_SEP, elements);
    }

    /**
     * Join with File.separator as separator.
     * @param elements to join into a String
     * @return a File.separator joined String
     */
    public static String join(String[] elements){
        return handy.join(OS_SEP, elements);
    }

}
