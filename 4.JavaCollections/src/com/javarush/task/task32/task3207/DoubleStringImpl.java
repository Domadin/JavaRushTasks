package com.javarush.task.task32.task3207;

import java.rmi.RemoteException;

public class DoubleStringImpl implements DoubleString {
    @Override
    public String doubleString(String str) throws RemoteException {
        return str + str;
    }
}