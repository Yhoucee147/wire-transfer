package com.me_cash.wire_transfer.util;

import com.me_cash.wire_transfer.model.Currency;

public class WireTransferUtil {

    public static String generateAccountNumber() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }

    public static char pickCurrency() {
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        char currency = (temp == 1) ? 'A' : 'B';
        return currency;
    }

    public static double convertCurrency(char currency, double amount) {
        double convertedAmount = (currency == 'A') ? amount * 1.3455 : amount * 0.7432;
        return convertedAmount;
    }

}
