package com.jobfever.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {
    @JsonEnumDefaultValue
    NONE,
    EMPLOYMENT,
    COMMISSION,
    SPECIFIC,
    B2B,
    INTERNSHIP;


    public static JobType from(String s) {
        if (s.equals("EMPLOYMENT")) {
            return EMPLOYMENT;
        } else if (s.equals("COMMISSION")) {
            return COMMISSION;
        } else if (s.equals("SPECIFIC")) {
            return SPECIFIC;
        } else if (s.equals("B2B")) {
            return B2B;
        } else if (s.equals("INTERNSHIP")) {
            return INTERNSHIP;
        }
        throw new RuntimeException("Unknown job type.");
    }
}

