package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private Long lastId = 0L; //будет отвечать за последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище
    private StorageStrategy storageStrategy; //будет храниться стратегия хранения данных

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) { //будет возвращать идентификатор id для заданной строки
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        } else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id) { //будет возвращать строку для заданного идентификатора или null, если передан неверный идентификатор
        return storageStrategy.getValue(id);
    }
}
