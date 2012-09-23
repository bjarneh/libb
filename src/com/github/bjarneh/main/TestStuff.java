package com.github.bjarneh.main;

import com.github.bjarneh.handy.path.Walk;

public class TestStuff{

    public static void main(String[] args) throws Exception {
        for(String s: args){
            String[] files = Walk.walk(s);
            for(String file: files){
                System.out.printf("> %s\n", file);
            }
        }
    }
}
