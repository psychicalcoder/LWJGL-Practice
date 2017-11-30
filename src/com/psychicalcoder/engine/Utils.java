package com.psychicalcoder.engine;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static String loadResource(String filename) throws Exception {
        String result;
        try (InputStream in = Utils.class.getResourceAsStream(filename);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }

}
