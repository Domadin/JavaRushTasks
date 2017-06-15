package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(int j) {
            this.j = j;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (i != a.i) return false;
            return j == a.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone() throws CloneNotSupportedException {
            return new C(getI(), getJ(), getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A(1, 1);
        B b = new B(2, 2, "BName");
        C c = new C(3, 3, "CName");
        B bClone = b.clone();
        C cClone = c.clone();
        System.out.println("До изменений");
        System.out.println("a: " + a.i + a.j);
        System.out.println("b: " + b.getI() + b.getJ() + b.getName());
        System.out.println("c: " + cClone.getI() + cClone.getJ() + cClone.getName());
        System.out.println();
        System.out.println("После изменений");
        a.i = 111;
        b.setJ(222);
        System.out.println("a: " + a.i + a.j);
        System.out.println("b: " + b.getI() + b.getJ() + b.getName());
        System.out.println("c: " + cClone.getI() + cClone.getJ() + cClone.getName());


    }
}
