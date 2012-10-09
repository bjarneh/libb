package com.github.bjarneh.start;

import java.io.ByteArrayInputStream;
/// import java.io.FileOutputStream;

import com.github.bjarneh.utilz.encoding;
import com.github.bjarneh.utilz.io;

class Main {
    public static void main(String[] args) throws Exception {
///         io.pipe(System.in, System.out);

        if(args.length < 1){
            System.exit(1);
        }

        byte[] utf8bytes = io.raw(args[0]);
        byte[] iso1bytes = encoding.fromTo(utf8bytes, "UTF-8", "ISO-8859-1");

        ByteArrayInputStream bi = new ByteArrayInputStream(iso1bytes);
        io.pipe(bi, System.out);

    }
}
