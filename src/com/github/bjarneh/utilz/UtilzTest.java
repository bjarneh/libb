//  Copyright © 2012 bjarneh
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.github.bjarneh.utilz;

// stdlib
import java.io.File;

// junit
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

// local
import com.github.bjarneh.utilz.handy;
import com.github.bjarneh.utilz.path;
import com.github.bjarneh.utilz.globals;


public class UtilzTest{

    @Test
    public void testJoin(){
        String[] arr = {"a","b"};
        assertEquals(handy.join("|", arr), "a|b");
    }

    @Test
    public void testPathJoin(){
        String[] p  = {"usr","local","bin"};
        String pathStr = handy.join(File.separator, p);
        assertEquals(pathStr, path.join(p));
    }

    @Test
    public void testFromSlash(){
        String slashed   = "usr/local/bin";
        String[] pathArr = {"usr","local","bin"};
        String pathStr   = path.join(pathArr);
        assertEquals(pathStr, path.fromSlash(slashed));
    }

    @Test
    public void testPathClean(){
        String pathStr  = path.fromSlash("//some///path/name/");
        String cleaned  = path.fromSlash("/some/path/name");
        assertEquals(cleaned, path.clean(pathStr));
    }

    @Test
    public void testDigest() throws Exception {

        String[] tests = {
          "abcde", "ab56b4d92b40713acc5af89985d4b786",
          "12345", "827ccb0eea8a706c4c34a16891f84e7b"
        };

        String md5Hex;
        byte[] md5Bytes;
        byte[] tmpBytes;

        for(int i = 0; i < tests.length; i += 2){
            tmpBytes = tests[i].getBytes();
            md5Bytes = message.md5( tmpBytes );
            md5Hex   = message.toHex( md5Bytes );
            assertEquals(md5Hex, tests[i+1]);
        }
    }


    @Test
    public void testGlobals(){

        globals.set("number", 1);
        globals.set("number", 1L);
        globals.set("key", "value");

        assertTrue( globals.getInt("number") == 1 );
        assertTrue( globals.getLong("number") == 1L );
        assertEquals( globals.getStr("key"), "value");

    }
}
