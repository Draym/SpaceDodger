package com.andres_k.utils.tools;

/**
 * Created on 2021/07/13.
 *
 * @author Kevin Andres
 */
public class StringTools {
    public static String duplicateString(String value, int number) {
        String result = "";

        for (int i = 0; i < number; ++i) {
            result += value;
        }
        return result;
    }

    public static String addCharacterEach(String value, String character, int number) {
        StringBuilder result = new StringBuilder(value);
        int pos = result.length() - number;

        while (pos > 0) {
            result.insert(pos, character);
            pos -= number;
        }
        return result.toString();
    }

    public static float charSizeX() {
        return 9.2f;
    }

    public static float charSizeY() {
        return 20f;
    }
}
