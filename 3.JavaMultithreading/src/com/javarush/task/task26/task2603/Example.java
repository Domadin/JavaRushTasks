package com.javarush.task.task26.task2603;

import java.util.Arrays;
import java.util.Comparator;

// создадим простой объект, в котором будем хранить данные
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

// теперь собственно реализуем интерфейс Comparator, для сортировки по названию
class SortedByName implements Comparator<Product> {

    @Override
    public int compare(Product obj1, Product obj2) {

        String str1 = obj1.getName();
        String str2 = obj2.getName();

        return str1.compareTo(str2);
    }
}

// а так же реализуем интерфейс Comparator, для сортировки по цене
class SortedByPrice implements Comparator<Product> {

    @Override
    public int compare(Product obj1, Product obj2) {

        double price1 = obj1.getPrice();
        double price2 = obj2.getPrice();

        if (price1 > price2) {
            return 1;
        } else if (price1 < price2) {
            return -1;
        } else {
            return 0;
        }
    }
}

class CustomizedComparator<T> implements Comparator<T> {
    private Comparator<T>[] comparators;

    public CustomizedComparator(Comparator... comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(T o1, T o2) {
        int compResult = 0;
        for (Comparator comparator : comparators) {
            compResult = comparator.compare(o1, o2);
            if (compResult != 0) {
                break;
            }
        }
        return compResult;
    }
}

// ну и собственно работа с нашими данными
public class Example {

    public static void main(String[] args) {
        Product[] prodArray = new Product[3];

        // заполним объект Product содержимым
        prodArray[0] = new Product("AMilk", 7.56, 56);

        prodArray[1] = new Product("AMilk", 17.00, 32);

        prodArray[2] = new Product("BTea", 12.50, 0);

        CustomizedComparator comparator = new CustomizedComparator<>(new SortedByName(), new SortedByPrice());
        Arrays.sort(prodArray, comparator);
        System.out.println(Arrays.toString(prodArray));
    }
}