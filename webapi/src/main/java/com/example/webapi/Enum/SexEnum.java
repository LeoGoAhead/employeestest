package com.example.webapi.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    private final int key;
    private final String value;

    SexEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static SexEnum getSexEnum(int key) {
        for (SexEnum val : SexEnum.values()) {
            if (val.key == key) {
                return val;
            }
        }
        return null;
    }

    public static List<Map<String, Object>> listSexEnum() {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SexEnum tEnum : SexEnum.values()) {
            Map<String, Object> map = new HashMap();
            map.put("key", tEnum.getKey());
            map.put("value", tEnum.getValue());
            maps.add(map);
        }
        return maps;
    }
}
