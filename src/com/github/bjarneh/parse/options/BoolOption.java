package com.github.bjarneh.parse.options;

import static java.lang.String.format;

class BoolOption extends Option{

    boolean set;

    BoolOption(String[] flags){
        super(flags);
        set = false;
    }

    void found(){ this.set = true; }

    void reset(){ this.set = false; }

    boolean isSet(){ return this.set; }

    public String toString() {
        return format("%37s : %b", flagStr(), set);
    }

}
