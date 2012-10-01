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

import static java.lang.String.format;

/**
 * Represent boolean flag.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

class BoolOption extends Option{

    boolean set;

    BoolOption(String[] flags){
        super(flags);
        set = false;
    }

    void found(){ set = true; }

    void reset(){ set = false; }

    boolean isSet(){ return set; }

    public String toString() {
        return format("%37s : %b", flagStr(), set);
    }

}
