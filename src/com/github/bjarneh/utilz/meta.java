package com.github.bjarneh.utilz;

import java.util.HashSet;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class meta {

    public static HashSet<String> complete(Class c){

        HashSet<String> arr = new HashSet<String>();

        if(c == null){
            return arr;
        }

        if(c.isArray()){
            arr.add("length");
        }else{

            Field[] fields = c.getDeclaredFields();

            for(Field f: fields){
                if( Modifier.isPublic(f.getModifiers()) ){
                    arr.add(f.getName());
                }
            }

            Method[] methods = c.getDeclaredMethods();

            for(Method m : methods){
                if( Modifier.isPublic(m.getModifiers()) ){
                    //arr.add(m.toGenericString());
                    arr.add(m.getName());
                }
            }
        }
        
        return arr;
    }

    public static HashSet<String> complete(Class c, String stub){

        HashSet<String> set     = complete(c);
        HashSet<String> startOk = new HashSet<String>();

        for(String s : set){
            if(s.startsWith(stub)){
                startOk.add(s);
            }
        }

        return startOk;
    }

    public static HashSet<String> complete(Object obj){
        return complete(obj.getClass());
    }

    public static HashSet<String> complete(Object obj, String stub){
        return complete(obj.getClass(), stub);
    }

}
