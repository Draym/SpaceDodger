package com.andres_k.utils.tools;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by andres_k on 24/03/2015.
 */

public class StringTools {

    public static String readFile(URL fileName) {
        String content = "";
        if (fileName == null)
            return content;
        try {
            File file = new File(fileName.toURI());
            Debug.debug("file: " + file.getAbsolutePath());
            try {
                FileReader reader = new FileReader(file);
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                content = new String(chars);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeInFile(URL fileName, String value) {
        if (fileName == null)
            return;
        try {
            File file = new File(fileName.toURI());
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(value);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String duplicateString(String value, int number){
        String result = "";

        for (int i = 0; i < number; ++i){
            result += value;
        }
        return result;
    }

    public static String addCharacterEach(String value, String character, int number){
        StringBuilder result = new StringBuilder(value);
        int pos = result.length() - number;

        while (pos > 0){
            result.insert(pos, character);
            pos -= number;
        }
        return result.toString();
    }

    public static float charSizeX(){
        return 9.2f;
    }

    public static float charSizeY(){
        return 20f;
    }
}
