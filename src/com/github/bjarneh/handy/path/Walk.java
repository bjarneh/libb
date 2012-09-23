package com.github.bjarneh.handy.path;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.LinkedList;


public class Walk {

    private static final String OS_SEP = File.separator;

    private static final FileFilter yesFilter = new FileFilter() {
        @Override public boolean accept(File file){
            return true;
        }
    };

    public static String[] walk(String root, FileFilter filter){

        File fileRoot = new File(root);
        if( ! fileRoot.isDirectory() ){ return new String[0]; }

        LinkedList<String> list = new LinkedList<String>();
        walk(pathClean(root), fileRoot, list, filter);

        String[] arr = new String[list.size()];
        list.toArray(arr);

        return arr;

    }

    public static String[] walk(String root){
        return walk(pathClean(root), yesFilter);
    }

    public static void walk(String root,
                            File dir,
                            List<String> save,
                            FileFilter filter)
    {
        File[] ls = dir.listFiles(filter);

        for(File sub : ls){
            if(sub.isFile()){
                save.add(root + OS_SEP + sub.getName());
            }else if(sub.isDirectory()){
                walk(root + OS_SEP + sub.getName(),
                     new File(dir, sub.getName()), save, filter);
            }
        }
    }

    //TODO remove duplicate slash etc perhaps..
    public static String pathClean(String path){
        while(path.length() > 1 && path.endsWith(OS_SEP)){
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }
}
