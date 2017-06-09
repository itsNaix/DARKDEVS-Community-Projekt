package de.darkdevs.cp.utils;

public class IntUtils {

    public static boolean stringIsInt(String input ){
        try{ Integer.parseInt(input); }
        catch( Exception ex ){ return false; }
        return true;
    }

}
