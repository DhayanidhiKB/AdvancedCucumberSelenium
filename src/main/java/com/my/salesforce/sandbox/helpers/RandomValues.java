package com.my.salesforce.sandbox.helpers;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RandomValues {
    // In pattern pass '?' marks based on number of characters needed
    public String randomString(String pattern) {
        return new Faker(new Locale("en-US")).letterify(pattern);
    }

    // In pattern pass '#' marks based on number of numeric needed
    public String randomNumber(String pattern) {
        return new Faker(new Locale("en-US")).numerify(pattern);
    }

    // In pattern pass '?' marks based on number of characters needed
    // In pattern pass '#' marks based on number of numeric needed
    public String randomAlphaNumeric(String pattern) {
        return new Faker(new Locale("en-US")).bothify(pattern);
    }

    public String getFullName() {
        return new Faker().name().fullName();
    }

    public Object[] getFullName(int listSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(new Faker().name().fullName());
        }
        return list.toArray();
    }

    public String getFirstName() {
        return new Faker().name().firstName();
    }

    public Object[] getFirstName(int listSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(new Faker().name().firstName());
        }
        return list.toArray();
    }

    public String getLastName() {
        return new Faker().name().lastName();
    }

    public Object[] getLastName(int listSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(new Faker().name().lastName());
        }
        return list.toArray();
    }

    public String getNameWithMiddle() {
        return new Faker().name().nameWithMiddle();
    }

    public Object[] getNameWithMiddle(int listSize) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(new Faker().name().nameWithMiddle());
        }
        return list.toArray();
    }
}