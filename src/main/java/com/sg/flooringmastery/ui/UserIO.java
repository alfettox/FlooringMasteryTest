package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * GROUP 2
 * Wiley Edge
 **/
public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    LocalDate readDate(String msgPrompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    BigInteger readBigInteger(String s);
    BigDecimal readBigDecimal(String msgPrompt);
}