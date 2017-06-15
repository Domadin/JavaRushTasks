package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Solution {
    public static void main(String[] paths) throws IOException {
        String resultFileName = paths[0];

        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(getStreams(paths))));
             FileOutputStream outputStream = new FileOutputStream(resultFileName)) {
            System.out.println(zipInputStream.getNextEntry());
            byte[] buffer = new byte[1024 * 4];
            int length;
            while ((length = zipInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    private static List<InputStream> getStreams(String[] paths) throws FileNotFoundException {
        List<InputStream> result = new ArrayList<>();

        Set<String> zipPaths = new TreeSet<>();
        for (int i = 1; i < paths.length; i++) {
            zipPaths.add(paths[i]);
        }

        for (String path : zipPaths) {
            result.add(new FileInputStream(path));
        }
        return result;
    }
}
