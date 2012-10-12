package com.github.bjarneh.start;

import java.util.Set;
import com.github.bjarneh.utilz.io;
import com.github.bjarneh.utilz.meta;

class Main {
    public static void main(String[] args) throws Exception {

        Class ioClass = Class.forName("com.github.bjarneh.utilz.io");
        Set<String> set = meta.complete(ioClass);

        for(String s : set){
            System.out.printf("%s\n", s);
        }

        set = meta.complete(null);

        for(String s : set){
            System.out.printf("%s\n", s);
        }
    }
}
