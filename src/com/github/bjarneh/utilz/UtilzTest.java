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
}
