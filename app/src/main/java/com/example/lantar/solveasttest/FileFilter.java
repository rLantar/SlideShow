package com.example.lantar.solveasttest;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lantar on 01.12.2015.
 */
public class FileFilter {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";


    public static boolean validate(String image) {

        matcher = pattern.matcher(image);
        return matcher.matches();


    }

    public static void getJpg() {
        pattern = Pattern.compile(IMAGE_PATTERN);

        File fileDirectory = new File(OptionsDate.getPath());
        File[] dirFiles = fileDirectory.listFiles();

        if (dirFiles.length != 0) {
            ArrayList<String> listPhoto = new ArrayList<String>();
            for (int ii = 0; ii < dirFiles.length; ii++) {
                String fileOutput = dirFiles[ii].toString();
                if (validate(fileOutput))
                    listPhoto.add(fileOutput);
            }
            OptionsDate.getInstance().setList(listPhoto);
        }
    }
}
