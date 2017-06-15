package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.*;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        List<Dish> dishList = Collections.unmodifiableList(Arrays.asList(Dish.values()));
        int size = dishList.size();
        Random random = new Random();
        this.dishes = new ArrayList<>();
        int count = random.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            dishes.add(dishList.get(random.nextInt(size)));
        }
    }
}
