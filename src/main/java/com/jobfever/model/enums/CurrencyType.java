package com.jobfever.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyType {
    PLN,
    EURO,
    DOLLAR;

    public static CurrencyType from(String s) {
        if (s.equals("PLN")) {
            return PLN;
        } else if (s.equals("EURO")) {
            return EURO;
        } else if (s.equals("DOLLAR")) {
            return DOLLAR;
        }
        throw new RuntimeException("Unknown currency type.");
    }
}

