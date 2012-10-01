package com.github.bjarneh.parse.options;

import java.util.ArrayList;
import static java.lang.String.format;

class StrOption extends Option{

    ArrayList<String> arguments;

    StrOption(String[] flags){
        super(flags);
        arguments = new ArrayList<String>(10);
    }

    void add(String arg){ arguments.add(arg); }

    void reset(){ arguments.clear(); }

    boolean isSet(){ return arguments.size() > 0; }

    String get(){ return arguments.get(0); }

    String[] getAll(){
        String[] args = new String[arguments.size()];
        arguments.toArray(args);
        return args;
    }

    public String toString(){
        return format("%37s : %s", flagStr(), arguments);
    }
}
