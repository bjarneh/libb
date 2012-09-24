package com.github.bjarneh.main;

import com.github.bjarneh.utilz.handy;

public class TestStuff{

    public static void main(String[] args) throws Exception {
        for(String s: args){
            String[] files = handy.pathWalk(s);
            for(String file: files){
                System.out.printf("> %s\n", file);
            }
        }
    }
}
