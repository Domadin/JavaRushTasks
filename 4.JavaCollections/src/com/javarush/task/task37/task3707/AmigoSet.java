package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object(); // Это будет наша заглушка.
    private transient HashMap<E, Object> map; //Список ключей будет нашим сэтом, а вместо значений будем пихать в мапу заглушку PRESENT.

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int max = (int) Math.max(16, Math.ceil(collection.size() / .75f));
        map = new HashMap<>(max);
        this.addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        Object obj = map.put(e, PRESENT);
        return obj == null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> cloneSet = new AmigoSet<>();
            cloneSet.addAll(this);
            cloneSet.map = (HashMap<E, Object>) map.clone();
            return cloneSet;
        } catch (Throwable e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        int size = map.size();
        objectOutputStream.writeInt(capacity);
        objectOutputStream.writeFloat(loadFactor);
        objectOutputStream.writeInt(size);
        Set<E> set = map.keySet();
        for (E element : set) {
            objectOutputStream.writeObject(element);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int capacity = objectInputStream.readInt();
        float loadFactor = objectInputStream.readFloat();
        int size = objectInputStream.readInt();
        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            map.put((E) objectInputStream.readObject(), PRESENT);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<>();
        amigoSet.add(1);
        amigoSet.add(2);
        AmigoSet<Integer> cloneSet = (AmigoSet<Integer>) amigoSet.clone();
        System.out.println(amigoSet.size());
        System.out.println(cloneSet.size());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(amigoSet);
        objectOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        AmigoSet<Integer> serSet = (AmigoSet<Integer>) objectInputStream.readObject();
        System.out.println(serSet.size());
    }
}
