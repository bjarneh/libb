package com.github.bjarneh.start;

import com.github.bjarneh.utilz.handy;

public class Main {

    public static void main(String[] args) throws Exception {
        for(String s: args){
            String[] files = handy.pathWalkVCS(s);
            for(String file: files){
                System.out.printf("> %s\n", file);
            }
        }
    }
}
