package com.javarush.task.task19.task1903;

import java.text.DecimalFormat;
import java.text.MessageFormat;
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

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            String countryCode = data.getCountryCode();
            return countries.get(countryCode);
        }

        @Override
        public String getName() {
            String firstName = data.getContactFirstName();
            String lastName = data.getContactLastName();
            return lastName + ", " + firstName;
        }

        @Override
        public String getPhoneNumber() {
            DecimalFormat numberFormat = new DecimalFormat("0000000000");
            String number = numberFormat.format(data.getPhoneNumber());
            String code = String.valueOf(data.getCountryPhoneCode());
            String[] numberArray = {code,
                    number.substring(0, 3),
                    number.substring(3, 6),
                    number.substring(6, 8),
                    number.substring(8)};
            MessageFormat fullFormat = new MessageFormat("+{0}({1}){2}-{3}-{4}");
            return fullFormat.format(numberArray);
        }
    }


    public interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}