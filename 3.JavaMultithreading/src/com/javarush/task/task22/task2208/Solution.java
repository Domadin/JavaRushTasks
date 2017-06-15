package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println("map " + getQuery(map));

        Map<String, String> map1 = new HashMap<>();
        System.out.println("map1 " + getQuery(map1));

        Map<String, String> map2 = new HashMap<>();
        map2.put("age", null);
        System.out.println("map2 " + getQuery(map2));

        Map<String, String> map3 = new HashMap<>();
        map3.put(null, null);
        map3.put("name", "Ivanov");
        System.out.println("map3 " + getQuery(map3));

        Map<String, String> map4 = new HashMap<>();
        map4.put(null, null);
        map4.put("", "");
        System.out.println("map4 " + getQuery(map4));
    }

    public static String getQuery(Map<String, String> params) {
        if (params.isEmpty()) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder("");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                String key = entry.getKey();
                if (value != null && key != null) {
                    builder.append(String.format("%s = '%s' and ", key, value));
                }
            }
            if (builder.length() == 0) {
                return "";
            } else {
                return builder.delete(builder.length() - 5, builder.length()).toString();
            }
        }
    }
}
