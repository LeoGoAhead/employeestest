package utils;

import com.mysql.cj.util.StringUtils;

import java.io.File;
import java.util.ArrayList;

public class Util {

    public static String trimRightSlash(String s) {
        if (StringUtils.isNullOrEmpty(s))
            return s;
        s = s.trim();
        if (s.endsWith("/") || s.endsWith("\\")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public static String trimLeftSlash(String s) {
        if (StringUtils.isNullOrEmpty(s))
            return s;
        s = s.trim();
        if (s.startsWith("/") || s.startsWith("\\")) {
            s = s.substring(1, s.length());
        }
        return s;
    }

    public static String trimSlash(String s) {
        return trimLeftSlash(trimRightSlash(s));
    }

    public static String combineUrl(Object... paras) {
        ArrayList<String> items = new ArrayList<>();
        if (paras.length > 0) {
            items.add(trimRightSlash(paras[0].toString()).replace('\\', '/'));
        }
        if (paras.length > 1) {
            for (int i = 1; i < paras.length; i++) {
                String path = trimSlash(paras[i].toString()).replace('\\', '/');
                while (path.startsWith("..")) {
                    path = path.substring(3);
                    if (items.size() > 0) {
                        items.remove(items.size() - 1);
                    }
                }
                String ps[] = path.split("\\/");
                for (String p : ps) {
                    items.add(p);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1) {
                sb.append("/");
            }
        }
        return sb.toString();
    }

    public static String combinePath(Object... paras) {
        if (File.separator.equals("/")) {
            return combineUrl(paras);
        } else {
            return combineWinPath(paras);
        }
    }

    public static String combineWinPath(Object... paras) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < paras.length; i++) {
            String path = trimSlash(paras[i].toString()).replace('/', '\\');
            while (path.startsWith("..")) {
                path = path.substring(3);
                if (items.size() > 0) {
                    items.remove(items.size() - 1);
                }
            }
            String ps[] = path.split("\\\\");
            for (String p : ps) {
                items.add(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1) {
                sb.append("\\");
            }
        }
        return sb.toString();
    }


}
