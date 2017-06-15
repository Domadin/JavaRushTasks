package com.javarush.task.task19.task1905;

public class ContactTest implements Solution.Contact {
    private String name;
    private String phoneNumber;

    public ContactTest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
