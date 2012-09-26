package com.github.bjarneh.start;

import com.github.bjarneh.utilz.handy;
import com.github.bjarneh.utilz.message;

class Main {

    public static void main(String[] args) throws Exception {
        byte[] b = message.md5(System.in);
        System.out.printf("%s -\n", handy.toHex(b));
    }
}
