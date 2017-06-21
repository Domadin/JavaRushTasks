package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() {
        Path path = new File(packageName).toPath();
        ClassLoaderFromPath classLoader = new ClassLoaderFromPath();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {
                if (file.getFileName().toString().endsWith(".class"))
                    try {
                        hiddenClasses.add(classLoader.loadClass(file.getFileName().toString().replace(".class", "")));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key)) {
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor constructor : constructors) {
                    if (constructor.getParameterTypes().length == 0) {
                        constructor.setAccessible(true);
                        try {
                            return (HiddenClass) constructor.newInstance(null);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    public class ClassLoaderFromPath extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] b = Files.readAllBytes(new File(Solution.this.packageName + "/" + name + ".class").toPath());
                return defineClass(b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }
    }
}

