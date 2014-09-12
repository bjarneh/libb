//  Copyright © 2014 bjarneh
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

import java.net.URL;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 * Get resources from your zip/jar file.
 *
 * @author bjarneh@ifi.uio.no
 */

public class res {

    private static res single;

    public static res get(){
        if( single == null ){
            single = new res();
        }
        return single;
    }


    public URL url(String ref){
        return this.getClass().getClassLoader().getResource(ref);
    }


    public ImageIcon icon(String ref){
        return new ImageIcon(url(ref));
    }
}
