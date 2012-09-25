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

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.io.File;

public class handyTest{

    @Test
    public void testJoin(){
        String[] arr = {"a","b"};
        assertEquals(handy.join("|", arr), "a|b");
    }

    @Test
    public void testFromSlash(){
        String[] path  = {"usr","local","bin"};
        String pathStr = handy.join(File.separator, path);
        assertEquals(pathStr, handy.pathJoin(path));
    }

}
