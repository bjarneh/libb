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

package com.github.bjarneh.parse.options;

import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * Getopt is a command line parser.
 *
 * NOTE: This is not a re-implementation of the
 * GNU-getopt, I just chose a very similar name :-)
 
<pre>
 
   // typical usage

   Getopt getopt = new Getopt();

   getopt.addBoolOption("-h -help --help help");
   getopt.addBoolOption"("-v -version --version");

   // fancy option creates all variants of a flag, i.e.
   // this is equivalent to calling addFancyStrOption
   //
   // getopt.addStrOption("-p -p= -port -port= --port --port=")
   
   getopt.addFancyStrOption("-p --port");
   getopt.addFancyStrOption("-r --root");
   getopt.addStrOption("-I -I=");

   String[] rest = getopt.parse(args);

   if(getopt.isSet("-help")){
     System.out.println(" I will help you ");
   }

   if(getopt.isSet("-port")){
     port = getopt.getInt("-port");
   }

   if(getopt.isSet("-root")){
     root = getopt.get("-root");
   }

   if(getopt.isSet("-I")){
     String[] iArgs = getopt.getAll("-I");
     for(int i = 0; i &lt; iArgs.length; i++){
       System.out.printf(" -I: %s\n", iArgs[i]);
     }
   }
   
   for(String s: rest){
     System.out.printf("unparsed: %s\n", s);
   }


 </pre>
 *
 *
 * @author bjarneh@ifi.uio.no
 * @version 1.0
 */

public class Getopt{

    ArrayList<Option> options;
    HashMap<String, Option> cache;
    private boolean dieOnError;

    public Getopt(){
        this(true);// die on error
    }

    public Getopt(boolean dieOnError){
        options = new ArrayList<Option>(50);
        cache = new HashMap<String, Option>();
        dieOnError = dieOnError;
    }

    public void addBoolOption(String flagStr){

        String[] flags = splitFlags(flagStr);
        Option opt   = new BoolOption(flags);
        options.add(opt);

        for(String f : flags){
            cache.put(f, opt);
        }
    }

    public void addStrOption(String flagStr){

        String[] flags = splitFlags(flagStr);
        Option opt   = new StrOption(flags);
        options.add(opt);

        for(String f : flags){
            cache.put(f, opt);
        }
    }

    // some duplicate code but whatever...
    public void addFancyStrOption(String flagStr){

        String[] flags = makeFancyOptions(flagStr);
        Option opt   = new StrOption(flags);
        options.add(opt);

        for(String f : flags){
            cache.put(f, opt);
        }
    }

    public String[] parse(String[] argv){

        Option opt;
        StrOption stropt;
        BoolOption boolopt;
        ArrayList<String> rest = new ArrayList<String>();

        for(int i = 0; i < argv.length; i++){

            if(cache.containsKey(argv[i])){

                opt = cache.get(argv[i]);

                if(opt instanceof BoolOption){

                    boolopt = (BoolOption) opt;
                    boolopt.found();

                }else if(opt instanceof StrOption) {

                    if( i+1 >= argv.length){
                        String errmsg = "Missing argument for: %s" ;
                        errmsg = String.format(errmsg, argv[i]);
                        if(! dieOnError){
                            throw new Error(errmsg);
                        }else{
                            System.err.printf("%s\n", errmsg);
                            System.exit(1);
                        }
                    }

                    stropt = (StrOption) opt;
                    stropt.add(argv[++i]);
                }

            }else{ // not plain options 

                if( ! juxtaBool( argv[i] ) && ! juxtaStr(argv[i]) ){
                    rest.add( argv[i] );
                }
            }
        }

        String[] tmp = new String[rest.size()];
        return rest.toArray(tmp);
    }

    // -xvzf == -x -v -z -f
    private boolean juxtaBool(String flag){

        if( flag.length() < 3 ){ return false; }

        String tmp;
        Option opt;
        BoolOption boolopt;

        char[] c = flag.toCharArray();

        for(int i = 1; i < c.length; i++){
            tmp = "-" + c[i];
            opt = cache.get(tmp);
            if( opt == null || ! (opt instanceof BoolOption) ){
                return false;
            }
        }

        for(int i = 1; i < c.length; i++){
            tmp = "-" + c[i];
            boolopt = (BoolOption) cache.get(tmp);
            boolopt.found();
        }

        return true;
    }

    // -I/dir == -I /dir
    private boolean juxtaStr(String flag){

        if( ! flag.startsWith("-") ){ return false; }

        int maxLen = -1;
        String maxFlag = "";
        Option opt;
        StrOption stropt;

        for(String s : cache.keySet()){
            if( flag.startsWith(s) ){
                opt = cache.get(s);
                if( opt instanceof StrOption ){
                    if( s.length() > maxLen ){
                        maxFlag = s;
                        maxLen  = s.length();
                    }
                }
            }
        }

        if( maxLen > 0 ){
            stropt = (StrOption) cache.get(maxFlag);
            stropt.add( flag.substring(maxFlag.length()) );
            return true;
        }

        return false;
    }

    private String[] makeFancyOptions(String flagStr){

        ArrayList<String> fancy = new ArrayList<String>();
        String[] flags = splitFlags(flagStr);

        for(String f: flags){
            fancy.add(f);
            if(f.startsWith("--")){
                fancy.add(f.substring(1));
                fancy.add(f.substring(1) + "=");
                fancy.add(f + "=");
            }else if(f.startsWith("-")){
                fancy.add(f + "=");
            }
        }

        String[] fancyFlags = new String[fancy.size()];
        return fancy.toArray(fancyFlags);
    }

    public boolean isSet(String flag){
        Option opt = cache.get(flag);
        return opt.isSet();
    }

    public String get(String flag){
        StrOption stropt = (StrOption) cache.get(flag);
        return stropt.get();
    }

    public String[] getAll(String flag){
        StrOption stropt = (StrOption) cache.get(flag);
        return stropt.getAll();
    }

    public int getInt(String flag){
        return Integer.parseInt( get(flag) );
    }

    public float getFloat(String flag){
        return Float.parseFloat( get(flag) );
    }

    public double getDouble(String flag){
        return Double.parseDouble( get(flag) );
    }

    private String[] splitFlags(String flagStr){
        return flagStr.trim().split("\\s+");
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Option opt : options){
            sb.append(opt).append("\n");
        }
        return sb.toString();
    }

}
