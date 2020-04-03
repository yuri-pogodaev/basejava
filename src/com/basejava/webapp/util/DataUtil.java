package com.basejava.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DataUtil {
    public static LocalDate of(int year, Month month) {
        LocalDate.of(year, month, 1);
        return null;
    }
}
