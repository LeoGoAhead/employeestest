package com.example.webapi.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DegreeEnum {
    DAZHUAN(100, "大专"),
    BENKE(200, "本科"),
    YANJIUSHENG(300, "研究生"),
    ;

    private final int key;
    private final String value;

    DegreeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static DegreeEnum getDegreeEnum(int key) {
        for (DegreeEnum val : DegreeEnum.values()) {
            if (val.key == key) {
                return val;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static List<Map<String, Object>> listDegreeEnum() {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (DegreeEnum tEnum : DegreeEnum.values()) {
            Map<String, Object> map = new HashMap();
            map.put("key", tEnum.getKey());
            map.put("value", tEnum.getValue());
            maps.add(map);
        }
        return maps;
    }
}
