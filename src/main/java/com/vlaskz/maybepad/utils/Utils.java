package com.vlaskz.maybepad.utils;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class Utils {

    public static String normalizePath(String path) {
        try {
            path = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            e.printStackTrace();
        }

        path = path.trim();

        path = Normalizer.normalize(path, Normalizer.Form.NFD);
        path = path.replaceAll("[^\\p{ASCII}]", "");

        path = path.replaceAll("[^a-zA-Z0-9_/]+", "");
        path = path.replaceAll("/+", "/");

        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        return path;
    }
}