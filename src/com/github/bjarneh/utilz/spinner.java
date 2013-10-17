//  Copyright © 2013 bjarneh
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

// std
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import static java.lang.System.out;
import static java.lang.System.err;

// libb
import com.github.bjarneh.utilz.io;


/**
 * wget with spinner/progress-bar.
 * 
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class spinner {


    // we only have public functions
    private spinner(){}


    // default interval between feedback (millisecond)
    public final static int DEFAULT_INTERVAL = 300;


    /**
     * Write the bytes of an URL to an file with progress.
     * @param urlstr to read from
     * @param filename to write bytes to
     */
    public static void wget(String urlstr, String filename)
        throws FileNotFoundException,
               MalformedURLException,
               IOException, 
               InterruptedException
    {
        wget( new URL(urlstr), new File(filename) );
    }


    /**
     * Write the bytes of an URL to an file with progress.
     * @param url to read from
     * @param filename to write bytes to
     */
    public static void wget(URL url, String filename)
        throws FileNotFoundException,
               MalformedURLException,
               IOException, 
               InterruptedException
    {
        wget(url, new File(filename));
    }

    /**
     * Write the bytes of an URL to an file with progress.
     * @param url to read from
     * @param file to write bytes to
     */
    public static void wget(URL url, File file)
        throws FileNotFoundException,
               MalformedURLException,
               IOException, 
               InterruptedException
    {
        wget(url, file, DEFAULT_INTERVAL);
    }

    /**
     * Write the bytes of an URL to an file with progress.
     * @param url to read from
     * @param file to write bytes to
     * @param interval between feedback
     */
    public static void wget(URL url, File file, long interval)
        throws FileNotFoundException,
               MalformedURLException,
               IOException, 
               InterruptedException
    {

        wget(url, file, interval, io.DEFAULT_BUFFER_SIZE);
    }

    /**
     * Write the bytes of an URL to an file with progress.
     * @param url to read from
     * @param file to write bytes to
     * @param interval between feedback
     * @param buffersize size of buffer when reading
     */
    public static void wget(URL url, File file, long interval, int buffersize)
        throws FileNotFoundException,
               MalformedURLException,
               IOException, 
               InterruptedException
    {

        FileOutputStream fo = new FileOutputStream(file);

        Spinner spinner = new Spinner(url, file, interval);
        spinner.start();

        io.wget(url, fo, buffersize);

        spinner.halt();
        spinner.join();

    }



    // This reports the feedback, not much room for configuration :-)
    static class Spinner extends Thread {

        final int Kb = 1024; 
        final long interval;
        final URL url;
        final File file;
        boolean stopped = false;

        Spinner(final URL url, final File file, long interval){
            this.interval = interval;
            this.file     = file;
            this.url      = url;
        }

        public void halt(){
            stopped = true;
        }

        public void run(){

            try{

                out.printf("  Downloading: %s\n", file.getName());

                URLConnection conn = url.openConnection();
                int length = conn.getContentLength();
                int total  = length/Kb;

                while( file.length() < length && !stopped ){

                    out.printf("  %d/%d Kb\r", file.length()/Kb, total);

                    sleep(interval);
                }

            }catch(Exception e){
                err.println(e);
            }

        }
    }


}
