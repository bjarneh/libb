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
 * <b>Example</b>
 * <pre>
 * // Assuming that the jar-file containing your program
 * // contains a top-level directory filled with images 
 * // called <b>img</b> this should fetch an image:
 *
 * ImageIcon icon = res.get().icon("img/someIcon.png"));
 *
 * </pre>
 *
 * @version 1.0
 * @author  bjarneh@ifi.uio.no
 */

public class res {

    private static res single;

    public static res get(){
        if( single == null ){
            single = new res();
        }
        return single;
    }


    /**
     * Return an URL to a file within your jar archive.
     * @param ref should be reference to a file within your jar
     * @return an URL to a resource within your jar
     */
    public URL url(String ref){
        return this.getClass().getClassLoader().getResource(ref);
    }


    /**
     * Return an ImageIcon to a file within your jar archive.
     * @param ref should be reference to an image file within your jar
     * @return an ImageIcon object of a resource within your jar
     */
    public ImageIcon icon(String ref){
        return new ImageIcon(url(ref));
    }
}
