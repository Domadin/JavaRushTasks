package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static Map<String, String> countries = new HashMap<>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");

    }

    public static void main(String[] args) {
        Customer customer = new CustomerTest("JavaRush Ltd.", "Ukraine");
        Contact contact = new ContactTest("Ivanov, Ivan", "+38(050)123-45-67");
        RowItem rowItem = new DataAdapter(customer, contact);
        System.out.println(rowItem.getCountryCode());
        System.out.println(rowItem.getCompany());
        System.out.println(rowItem.getContactFirstName());
        System.out.println(rowItem.getContactLastName());
        System.out.println(rowItem.getDialString());
    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode() {
            String country = customer.getCountryName();
            String code = null;
            for (Map.Entry<String, String> entry : countries.entrySet()) {
                if (entry.getValue().equals(country)) {
                    code = entry.getKey();
                }
            }
            return code;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String fullName = contact.getName();
            return fullName.substring(fullName.indexOf(" ") + 1);
        }

        @Override
        public String getContactLastName() {
            String fullName = contact.getName();
            return fullName.substring(0, fullName.indexOf(","));
        }

        @Override
        public String getDialString() {
            String phone = contact.getPhoneNumber();
            phone = phone.replaceAll("[()-]", "");
            return "callto://" + phone;
        }
    }

    public interface RowItem {
        String getCountryCode();

        String getCompany();

        String getContactFirstName();

        String getContactLastName();

        String getDialString();
    }

    public interface Customer {
        String getCompanyName();

        String getCountryName();
    }

    public interface Contact {
        String getName();

        String getPhoneNumber();
    }
}