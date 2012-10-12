package com.github.bjarneh.start;

import java.util.Set;
import com.github.bjarneh.utilz.io;
import com.github.bjarneh.utilz.meta;

class Main {
    public static void main(String[] args) throws Exception {

        Set<String> set = meta.completeForName("com.github.bjarneh.utilz.io");

        for(String s : set){
            System.out.printf("%s\n", s);
        }
    }
}
