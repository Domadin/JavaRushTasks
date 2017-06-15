package com.javarush.task.task27.task2712.ad;

public class Advertisement implements Comparable<Advertisement> {
    private Object content;     //видео.
    private String name;
    private long initialAmount; //начальная сумма, стоимость рекламы в копейках.
    private long amountPerOneDisplaying;    //стоимость 1 показа рекламы
    private int hits;           //количество оплаченных показов.
    private int duration;       //продолжительность в секундах.

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName() {
        return name;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getDuration() {
        return duration;
    }

    public int getHits() {
        return hits;
    }

    public void revalidate() {
        if (hits < 1)
            throw new UnsupportedOperationException();
        hits--;
    }


    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + amountPerOneDisplaying * 1000 / duration;
    }

    @Override
    public int compareTo(Advertisement o) {
        int costCompare = Long.compare(o.amountPerOneDisplaying, this.amountPerOneDisplaying);
        if (costCompare == 0)
            return Long.compare((this.amountPerOneDisplaying * 1000 / this.duration), (o.amountPerOneDisplaying * 1000 / o.duration));
        else return costCompare;
    }
}
