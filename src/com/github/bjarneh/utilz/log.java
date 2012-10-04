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
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import static java.lang.String.format;

/**
 * Simple logger, that logs to {@link System#err} by default,
 * but can log to any {@link PrintStream}, or multiple streams.
 *
 * <pre>
 *  // Typical usage:
 *
 *  log.init(new PrintStream(filename), log.DEBUG);
 *  log.error("something is wrong");
 *  log.debug("just debuggin...");
 *  log.info("formatting a number to log: %d\n", 10);
 *
 * </pre>
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class log {

    public static final int DEBUG   = 0;
    public static final int INFO    = 1;
    public static final int WARNING = 2;
    public static final int WARN    = 2;
    public static final int ERROR   = 3;
    public static final int FATAL   = 4;

    private static final String[] levelStr = {"DEBUG",
                                              "INFO",
                                              "WARNING",
                                              "ERROR",
                                              "FATAL"};
    private static int   level = ERROR;
    private static final ArrayList<PrintStream> output;

    static{
        output = new ArrayList<PrintStream>();
        output.add(System.err);
    }

    // we only have functions
    private log(){}

    /**
     * Use another stream than {@link System#err} as output.
     * @param stream where the log entries end up
     */
    public static synchronized void init(PrintStream stream){
        output.clear();
        output.add(stream);
    }

    /**
     * Use another stream than {@link System#err} as output,
     * and set log-level.
     * @param stream where the log entries end up
     * @param logLevel set the log-level for the logger
     */
    public static synchronized void init(PrintStream stream, int logLevel){
        init(stream);
        setLevel(logLevel);
    }

    /**
     * Use streams as output destination.
     * @param streams where the log entries end up
     */
    public static synchronized void init(Collection<PrintStream> streams){
        output.clear();
        output.addAll(streams);
    }

    /**
     * Use streams as output destination, and set log-level.
     * @param streams where the log entries end up
     * @param logLevel set the log-level for the logger
     */
    public static synchronized void init(Collection<PrintStream> streams, int logLevel){
        init(streams);
        setLevel(logLevel);
    }

    /**
     * Set the log-level for this logger.
     *
     * Value must be one of {@link log#DEBUG}, {@link log#INFO},
     * {@link log#WARNING}, {@link log#ERROR}, {@link log#FATAL}.
     *
     * @param logLevel set the log-level for the logger
     */
    public static synchronized void setLevel(int logLevel){
        if(logLevel > FATAL || logLevel < DEBUG){
            throw new RuntimeException("log.setLevel got illegal value\n");
        }
        level = logLevel;
    }

    /**
     * Add destination stream without removing current streams.
     * 
     * @param dest add a destination stream for logger, don't remove others.
     */
    public static synchronized void addStream(PrintStream dest){
        output.add(dest);
    }

    /**
     * Write message to log for DEBUG and higher priority.
     * 
     * @param msg is the message that will be written to the log
     */
    public static synchronized void debug(String msg){
        dispatchMsg(log.DEBUG, msg);
    }

    /**
     * Write a formatted message log for DEBUG and higher priority.
     * 
     * Formatting is done in {@link PrintStream#printf} format.
     *
     * @param fmt is format string
     * @param args are the arguments for the format string
     */
    public static synchronized void debug(String fmt, Object... args){
        dispatchFmt(log.DEBUG, fmt, args);
    }

    /**
     * Write message to log for INFO and higher priority.
     * 
     * @param msg is the message that will be written to the log
     */
    public static synchronized void info(String msg){
        dispatchMsg(log.INFO, msg);
    }

    /**
     * Write a formatted message log for INFO and higher priority.
     * 
     * Formatting is done in {@link PrintStream#printf} format.
     *
     * @param fmt is format string
     * @param args are the arguments for the format string
     */
    public static synchronized void info(String fmt, Object... args){
        dispatchFmt(log.INFO, fmt, args);
    }

    /**
     * Write message to log for WARNING and higher priority.
     * 
     * @param msg is the message that will be written to the log
     */
    public static synchronized void warning(String msg){
        dispatchMsg(log.WARNING, msg);
    }

    /**
     * Write a formatted message log for WARNING and higher priority.
     * 
     * Formatting is done in {@link PrintStream#printf} format.
     *
     * @param fmt is format string
     * @param args are the arguments for the format string
     */
    public static synchronized void warning(String fmt, Object... args){
        dispatchFmt(log.WARNING, fmt, args);
    }

    /**
     * Write message to log for ERROR and higher priority.
     * 
     * @param msg is the message that will be written to the log
     */
    public static synchronized void error(String msg){
        dispatchMsg(log.ERROR, msg);
    }

    /**
     * Write a formatted message log for ERROR and higher priority.
     * 
     * Formatting is done in {@link PrintStream#printf} format.
     *
     * @param fmt is format string
     * @param args are the arguments for the format string
     */
    public static synchronized void error(String fmt, Object... args){
        dispatchFmt(log.ERROR, fmt, args);
    }

    /**
     * Write message to log, no matter what priority.
     * 
     * @param msg is the message that will be written to the log
     */
    public static synchronized void fatal(String msg){
        dispatchMsg(log.FATAL, msg);
    }

    /**
     * Write a formatted message log no matter what priority.
     * 
     * Formatting is done in {@link PrintStream#printf} format.
     *
     * @param fmt is format string
     * @param args are the arguments for the format string
     */
    public static synchronized void fatal(String fmt, Object... args){
        dispatchFmt(log.FATAL, fmt, args);
    }


    ///////////////////////
    // private functions //
    ///////////////////////


    private static synchronized void dispatchMsg(int logLevel, String msg){
        if( level <= logLevel ){
            outStr(levelStr[logLevel], msg);
        }
    }

    private static synchronized void dispatchFmt(int logLevel, String fmt, Object... args){
        if( level <= logLevel ){
            outFmt(levelStr[logLevel], fmt, args);
        }
    }

    private static synchronized void outStr(String err, String msg){
        for(PrintStream s: output){
            s.printf("%s> %s\n", header(err), msg);
        }
    }

    private static synchronized void outFmt(String err, String fmt, Object... args){
        for(PrintStream s: output){
            s.printf("%s> %s\n", header(err), format(fmt, args));
        }
    }

    private static synchronized String header(String err){
        StackTraceElement stackTrace =
            Thread.currentThread().getStackTrace()[5];
        return format("[%s] %s | %s:%d\n",
                      err,
                      new Date(),
///                       stackTrace.getFileName(),
                      stackTrace.getClassName(),
                      stackTrace.getLineNumber());
    }

}
