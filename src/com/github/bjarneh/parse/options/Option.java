package com.github.bjarneh.parse.options;


abstract class Option{

    String[] flags;

    Option(String[] flags){
        this.flags = flags;
    }

    String flagStr(){
        StringBuilder sb = new StringBuilder();
        for(String f : flags){
            sb.append(f).append(" ");
        }
        return sb.toString();
    }

    abstract void reset();
    abstract boolean isSet();
}
