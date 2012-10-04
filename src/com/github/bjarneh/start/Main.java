package com.github.bjarneh.start;

import com.github.bjarneh.utilz.log;

class Main {
    public static void main(String[] args) throws Exception {
        log.error("this is logged");
        log.info("this is not read by anybody");
        log.fatal("this is read by all");
        log.setLevel(log.DEBUG);
        log.debug("this will be logged");
    }
}
