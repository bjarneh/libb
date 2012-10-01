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

/**
 * Super-type for the options, boolean and string option.
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

abstract class Option{

    String[] flags;

    Option(String[] flags){
        this.flags = flags;
    }

    String flagStr(){
        StringBuilder sb = new StringBuilder();
        for(String f : flags){
            sb.append(f).append(" ");
        }
        return sb.toString();
    }

    abstract void reset();
    abstract boolean isSet();
}
