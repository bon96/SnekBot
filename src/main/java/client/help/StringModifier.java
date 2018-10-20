package client.help;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 19.45
 */

public class StringModifier {

    public static String reverseString(String string) {
        String reverse = "";
        char[] charString = string.toCharArray();
        for (int i = string.length() -1; i >= 0; i--) {
            reverse += charString[i];
        }
        return reverse;
    }

    public static String[] cutStringAfter(String string, int length) {
        return Iterables.toArray(Splitter.fixedLength(8).split(string), String.class);
    }
}
