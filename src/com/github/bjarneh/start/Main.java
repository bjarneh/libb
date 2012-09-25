package com.github.bjarneh.start;

import com.github.bjarneh.utilz.handy;

public class Main {

    public static void main(String[] args) throws Exception {
        byte[] b = handy.md5(System.in);
        System.out.printf("%s -\n", handy.toHex(b));
    }
}
