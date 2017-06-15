package com.javarush.task.task27.task2710;

/* 
Расставьте wait-notify
*/
public class Solution {
    public static void main(String[] args) {
        Mail mail = new Mail();
        Thread mailServer = new Thread(new MailServer(mail));
        Thread person = new Thread(new Person(mail));

        mailServer.start();
        person.start();
    }
}
