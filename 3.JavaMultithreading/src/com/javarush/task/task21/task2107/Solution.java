package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Глубокое клонирование карты
*/
public class Solution implements Cloneable {
    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution solution = (Solution) super.clone();
        LinkedHashMap<String, User> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue().clone());
        }
        users = newMap;
        return solution;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println();
            System.out.print("Содержимое map в solution: ");
            System.out.println(solution.users);
            for (Map.Entry<String, User> entry : solution.users.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " " + entry.getValue().age + " " + entry.getValue().name);
            }
            System.out.print("Содержимое map в clone: ");
            System.out.println(clone.users);
            for (Map.Entry<String, User> entry : clone.users.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " " + entry.getValue().age + " " + entry.getValue().name);
            }

            System.out.println();
            System.out.println("До изменения элемента в solution");
            System.out.println(clone.users.get("Zapp"));
            System.out.println(solution.users.get("Zapp"));
            solution.users.put("Zapp", new User(1488, "Kek"));

            System.out.println();
            System.out.println("После изменения");
            System.out.println(clone.users.get("Zapp"));
            System.out.println(solution.users.get("Zapp"));

            System.out.println();
            System.out.println("Изменение в clone");
            clone.users.put("Zapp", new User(1337, "lale"));
            System.out.println(clone.users.get("Zapp"));
            System.out.println(solution.users.get("Zapp"));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        return users != null ? users.equals(solution.users) : solution.users == null;
    }

    @Override
    public int hashCode() {
        return users != null ? users.hashCode() : 0;
    }

    private LinkedHashMap<String, User> users = new LinkedHashMap<>();

    public static class User implements Cloneable {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!(o instanceof User)) return false;

            User user = (User) o;

            if (age != user.age) return false;
            return name != null ? name.equals(user.name) : user.name == null;
        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            return (User) super.clone();
        }
    }
}
