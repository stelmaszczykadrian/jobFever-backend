package com.jobfever.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkType {
    REMOTE,
    ONSITE,
    HYBRID;


    public static WorkType from(String s){
        if(s.equals("REMOTE")){
            return REMOTE;
        } else if (s.equals("ONSITE")) {
            return ONSITE;
        } else if (s.equals("HYBRID")) {
            return HYBRID;
        }

        throw new RuntimeException("Unknown work type." + s);
    }
}
