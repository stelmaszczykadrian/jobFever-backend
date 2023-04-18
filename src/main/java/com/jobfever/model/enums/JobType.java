package com.jobfever.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {
    @JsonEnumDefaultValue
    NONE,
    FULL_TIME,
    PART_TIME,
    CONTRACT,
    FREELANCE,
    INTERNSHIP,
    TEMPORARY;



    public static JobType from(String s){
        if(s.equals("FULL_TIME")){
            return FULL_TIME;
        } else if (s.equals("PART_TIME")) {
            return PART_TIME;
        } else if (s.equals("CONTRACT")) {
            return CONTRACT;
        } else if (s.equals("FREELANCE")) {
            return FREELANCE;
        } else if (s.equals("INTERNSHIP")) {
            return INTERNSHIP;
        }else if (s.equals("TEMPORARY")){
            return TEMPORARY;
        }

        throw new RuntimeException("Unknown job type.");
    }
}

