package com.example.webapi.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DepartmentEnum {
    YEWUBU(100, "业务部"),
    HOUQINBU(200, "后勤部"),
    RENSHIBU(300, "人事部"),
    ;

    private final int key;
    private final String value;

    DepartmentEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static List<Map<String, Object>> listDepartmentEnum() {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (DepartmentEnum tEnum : DepartmentEnum.values()) {
            Map<String, Object> map = new HashMap();
            map.put("key", tEnum.getKey());
            map.put("value", tEnum.getValue());
            maps.add(map);
        }
        return maps;
    }
}
