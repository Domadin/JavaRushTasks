package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"closed {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0} {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort(new Comparator<Stock>() {
            @Override
            public int compare(Stock stock1, Stock stock2) {
                int compare = String.valueOf(stock1.get("name")).compareTo(String.valueOf(stock2.get("name")));
                if (compare != 0) {
                    return compare;
                } else {
                    Date s1Date = (Date) stock1.get("date");
                    Date s2Date = (Date) stock2.get("date");
                    compare = s1Date.compareTo(s2Date);
                    if (compare != 0 && (s1Date.getYear() != s2Date.getYear() && s1Date.getMonth() != s2Date.getMonth() && s1Date.getDay() != s2Date.getDay())) {
                        System.out.println(compare);
                        return -compare;
                    } else {
                        Double s1Profit = stock1.containsKey("change") ?
                                (double) stock1.get("change") :
                                (double) stock1.get("last") - (double) stock1.get("open");

                        Double s2Profit = stock2.containsKey("change") ?
                                (double) stock2.get("change") :
                                (double) stock2.get("last") - (double) stock2.get("open");
                        return -s1Profit.compareTo(s2Profit);
                    }
                }
            }
        });
    }

/*    public static void sort(List<Stock> list) {
        list.sort(new Comparator<Stock>() {
            @Override
            public int compare(Stock stock1, Stock stock2) { //stock1 < stock2 ? -1 : stock1 == stock2 ? 0 : 1;
                int compare = String.valueOf(stock1.get("name")).compareTo(String.valueOf(stock2.get("name")));
                if (compare != 0) {
                    return compare;
                } else {
                    Date s1Date = (Date) stock1.get("date");
                    Date s2Date = (Date) stock2.get("date");
                    compare = Solution.compare(s1Date.getYear(), s2Date.getYear());
                    if (compare != 0) {
                        return compare;
                    } else {
                        compare = Solution.compare(s1Date.getMonth(), s2Date.getMonth());
                        if (compare != 0) {
                            return compare;
                        } else {
                            compare = Solution.compare(s1Date.getDay(), s2Date.getDay());
                            if (compare != 0) {
                                return compare;
                            } else {
                                Double s1Profit = stock1.containsKey("change") ?
                                        (double) stock1.get("change") :
                                        (double) stock1.get("last") - (double) stock1.get("open");

                                Double s2Profit = stock2.containsKey("change") ?
                                        (double) stock2.get("change") :
                                        (double) stock2.get("last") - (double) stock2.get("open");
                                return s1Profit.compareTo(s2Profit);
                            }
                        }
                    }
                }
            }
        });
    }*/

    public static class Stock extends HashMap {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() throws InterruptedException {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));//1
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .6, getRandomDate()));
        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .3, getTodayDate()));//1
        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .4, getTodayDate()));//1
        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .2, getTodayDate()));//1
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public static Date getTodayDate() throws InterruptedException {
        Thread.sleep(100);
        Date date = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.getTime();
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

