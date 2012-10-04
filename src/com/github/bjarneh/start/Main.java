package com.github.bjarneh.start;

import com.github.bjarneh.utilz.io;

class Main {
    public static void main(String[] args) throws Exception {
        io.pipe(System.in, System.out);
    }
}
