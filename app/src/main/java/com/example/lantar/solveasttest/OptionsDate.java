package com.example.lantar.solveasttest;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Lantar on 01.12.2015.
 */
public class OptionsDate {


    private static OptionsDate _instance = null;
    private static String path;
    private static int interval;
    private static int shange;
    private static ArrayList<String> list;

    private OptionsDate() {
    }

    public static synchronized OptionsDate getInstance() {
        if (_instance == null)
            _instance = new OptionsDate();
        return _instance;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        OptionsDate.path = path;
    }

    public static int getInterval() {
        return interval;
    }

    public static void setInterval(int interval) {
        OptionsDate.interval = interval;
    }

    public static ArrayList<String> getList() {
        return list;
    }

    public static void setList(ArrayList<String> list) {
        OptionsDate.list = list;
    }

    public static int getShange() {
        return shange;
    }

    public static void setShange(int shange) {
        OptionsDate.shange = shange;
    }
}
