package maeda.combinecsvs.demo.utils;

import java.util.Arrays;

public class StringUtils {

    /**
     *
     * @param arr [1,2,3]
     * @param trail e.g. "\n"
     * @return "1,\n2,\n3"
     */
    public static String joinWithSep(Object[] arr, String trail){
        String str = Arrays.stream(arr)
                .map((a) -> a + "")
                .reduce("", (a, b) -> a + "" + trail + b);
        return str.length() > 0 ? str.substring(0 + trail.length()) : "";
    }
}
