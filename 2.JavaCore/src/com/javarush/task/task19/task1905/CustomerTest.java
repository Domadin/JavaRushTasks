package com.javarush.task.task19.task1905;

public class CustomerTest implements Solution.Customer {
    private String companyName;
    private String countryName;

    public CustomerTest(String companyName, String countryName) {
        this.companyName = companyName;
        this.countryName = countryName;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String getCountryName() {
        return countryName;
    }
}
