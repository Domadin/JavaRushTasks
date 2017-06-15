package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        First first = new First();


        String jsonBuilder = mapper.writeValueAsString(first);
        System.out.println(jsonBuilder);

        Second second = new Second();

        jsonBuilder = mapper.writeValueAsString(second);
        System.out.println(jsonBuilder);


        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(one);
        String firstClassName = one.getClass().getSimpleName().toLowerCase();
        String secondClassName = resultClassObject.getSimpleName().toLowerCase();
        jsonString = jsonString.replaceFirst(firstClassName, secondClassName);

        return mapper.readValue(jsonString, resultClassObject);

    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;
    }
}
