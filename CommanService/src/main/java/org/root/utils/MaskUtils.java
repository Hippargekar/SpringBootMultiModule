package org.root.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class MaskUtils {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //System.out.println(maskDOB(today.format(pattern)));
        System.out.println(maskMiddle("dd/MM/yyyy",6));
    }

    public static String maskLeft(String input, int maskChars) {
        if (input.length() <= maskChars) return input;
        return input.substring(0, maskChars) + StringUtils.repeat('*', input.length() - maskChars);
    }

    public static String maskRight(String input, int maskChars) {
        if (input.length() <= maskChars) return input;
        return StringUtils.repeat('*', input.length() - maskChars) + input.substring(input.length() - maskChars);
    }

    public static String maskMiddle(String input, int maskChars) {
        int length = input.length();
        // No masking needed
        if (length <= maskChars) return input;

        int maskLength = length - maskChars;
        int startMask = maskLength / 2;
        int endMask = maskLength - startMask;
        return input.substring(0, startMask) + StringUtils.repeat('*', maskChars) + input.substring(length - endMask);
    }

    public static String maskDOB(String dob) {
        // Simple regex to validate "DD/MM/YYYY"
        if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", dob)) {
            return dob; // Return unmodified if invalid
        }
        String[] parts = dob.split("/");
        return "**/**/" + parts[2];
    }
}
