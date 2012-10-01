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

import java.util.ArrayList;
import static java.lang.String.format;

/**
 * Represent string options, which are basically any option which
 * takes an argument.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

class StrOption extends Option{

    ArrayList<String> arguments;

    StrOption(String[] flags){
        super(flags);
        arguments = new ArrayList<String>(10);
    }

    void add(String arg){ arguments.add(arg); }

    void reset(){ arguments.clear(); }

    boolean isSet(){ return arguments.size() > 0; }

    String get(){ return arguments.get(0); }

    String[] getAll(){
        String[] args = new String[arguments.size()];
        arguments.toArray(args);
        return args;
    }

    public String toString(){
        return format("%37s : %s", flagStr(), arguments);
    }
}
