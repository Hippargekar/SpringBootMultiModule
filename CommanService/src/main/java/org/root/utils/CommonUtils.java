package org.root.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.*;

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

    public static Instant convertToUTC(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return zonedDateTime.toInstant();
    }

    public static LocalDateTime convertFromUTC(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
// logical or physical clocks
    public static ZonedDateTime convertToZone(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId);
    }

    public static LocalDateTime convertToLocalDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }

    public static String formatBigDecimal(BigDecimal value) {
        if (value == null) return "";
        DecimalFormat df = new DecimalFormat("#.##########"); // Adjust the pattern as needed
        return df.format(value);
    }

    public static boolean checkDateBetween(LocalDate startDate, LocalDate endDate){
        LocalDate todayDate =  LocalDate.now();
        if( (todayDate.isAfter(startDate) && todayDate.isBefore(endDate)) || (todayDate.isEqual(startDate) || todayDate.isEqual(endDate)) )	{
            return true;
        }
        return false;
    }

    //serviceCharge
    public static BigDecimal getServiceCharge(BigDecimal txnAmount) {
        final String GST_RATE = "0.9";
        if (txnAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal gstPercentage = new BigDecimal(GST_RATE);
            return gstPercentage.divide(new BigDecimal(100)).multiply(txnAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public static String compareDecimal(BigDecimal b1, BigDecimal b2){
        String comparison = "";
        if (b1.compareTo(b2) == 0) {
            comparison = "Equals";
        } else if (b1.compareTo(b2) == 1) {
            comparison = "b1 is greater than b2";
        } else if (b1.compareTo(b2) == -1) {
            comparison = "b1 is smaller than b2";
        }
        return comparison;
    }
}
