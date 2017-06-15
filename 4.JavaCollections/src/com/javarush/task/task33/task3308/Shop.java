package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop {
    public int count;
    public double profit;
    public String[] secretData;
    public Goods goods;

    public Shop() {
    }

    @XmlType(name = "goods")
    public static class Goods {
        public List<String> names;

        public Goods() {
        }
    }
}
