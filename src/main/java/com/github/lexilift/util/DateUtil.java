package com.github.lexilift.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getCurrentForID() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DATE_FORMATTER);
    }


    public static void main(String[] args) {
        System.out.println(getCurrentForID());
    }


}
