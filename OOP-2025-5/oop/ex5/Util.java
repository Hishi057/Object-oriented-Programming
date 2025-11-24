package oop.ex5;

import java.util.*;

public final class Util {
    public static Map<String,String> parseArgs(String[] args){
        Map<String,String> m = new LinkedHashMap<>();
        for (int i=0;i<args.length;i++){
            String a = args[i];
            if (a.startsWith("--")){
                if (i+1 >= args.length || args[i+1].startsWith("--")) m.put(a, "true");
                else m.put(a, args[++i]);
            }
        }
        return m;
    }
    private Util(){}
}
