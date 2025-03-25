package org.root.utils;

public class CommonUtils {
    public String camelToSnake(String str) {
        StringBuilder sb = new StringBuilder();

        char c = str.charAt(0);
        sb.append(Character.toLowerCase(c));

        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(ch));
        }
        return sb.toString();
    }
}
