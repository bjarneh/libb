package com.github.bjarneh.main;

import com.github.bjarneh.handy.sec.Hash;

public class Hashing{

    public static void main(String[] args) throws Exception {
        byte[] b;
        for(String s: args){
            b = Hash.md5(s);
            System.out.printf(" md5sum(%s)  =>  %s\n", s, Hash.toHex(b));
            b = Hash.sha1(s);
            System.out.printf("sha1sum(%s)  =>  %s\n", s, Hash.toHex(b));
        }
    }
}
