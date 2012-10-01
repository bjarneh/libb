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

package com.github.bjarneh.parse.options;

// junit
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

// local
import com.github.bjarneh.parse.options.Getopt;

public class GetoptTest {

    @Test
    public void testParseArgv(){

        Getopt getopt = new Getopt();

        getopt.addBoolOption("-h -help --help");
        getopt.addBoolOption("-v -version --version");
        getopt.addFancyStrOption("-I");
        getopt.addFancyStrOption("-p --port");

        String[] argv = "-help a -I/lib1 -I/lib2 b -v -p=1".split("\\s+");

        String[] args = getopt.parse(argv);

        assertEquals(args[0], "a");
        assertEquals(args[1], "b");

        assertTrue(getopt.isSet("-h"));
        assertTrue(getopt.isSet("-help"));
        assertTrue(getopt.isSet("--help"));

        assertTrue(getopt.isSet("-v"));
        assertTrue(getopt.isSet("-version"));
        assertTrue(getopt.isSet("--version"));

        assertTrue(getopt.isSet("-p"));
        assertTrue(getopt.isSet("-port"));
        assertTrue(getopt.isSet("--port"));

        assertTrue(getopt.getInt("-port") == 1);

        assertTrue(getopt.isSet("-I"));

        String[] iargs = getopt.getAll("-I");
        assertEquals(iargs[0], "/lib1");
        assertEquals(iargs[1], "/lib2");

    }
}
