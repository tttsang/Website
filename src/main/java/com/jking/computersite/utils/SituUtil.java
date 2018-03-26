package com.jking.computersite.utils;

public class SituUtil {
    public static boolean notfull(String str ) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        else {return false;}
    }
}
