package com.prokopenko.diploma.utils;

public class YouTubeLinkParser {
    public static String parseLink(String link){
        String firstSubStr = link.split("v=")[1];
        String secondSubStr = firstSubStr.split("&")[0];
        return "https://www.youtube.com/embed/" + secondSubStr;
    }
}
