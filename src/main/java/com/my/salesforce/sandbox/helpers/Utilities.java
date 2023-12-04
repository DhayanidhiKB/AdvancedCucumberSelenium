package com.my.salesforce.sandbox.helpers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Utilities {
    public static String encode(@NotNull String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    @Contract("_ -> new")
    public static @NotNull String decode(@NotNull String encodedValue) {
        return new String(Base64.getDecoder().decode(encodedValue.getBytes()));
    }

    public static @NotNull String currentDateTime() {
        var date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(date);
    }

    public static @NotNull String localTime() {
        return LocalTime.now().toString();
    }

    public static @NotNull String localTime(String zoneID) {
        return LocalTime.now(ZoneId.of(zoneID)).toString();
    }

    public static long epochMilliSeconds() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    public static long epochSeconds() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }

    public static @NotNull String today() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static @NotNull String tomorrow() {
        return LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static @NotNull String oneMonthFromToday() {
        return LocalDate.now().getDayOfMonth()
                + "/" + LocalDate.now().plusMonths(1).getMonthValue() + "/" +
                LocalDate.now().getYear();
    }

    public static @NotNull String oneYearFromToday() {
        return LocalDate.now().getDayOfMonth()
                + "/" + LocalDate.now().getMonthValue() + "/" +
                LocalDate.now().plusYears(1).getYear();
    }
}