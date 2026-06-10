package com.bus.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDateTime {
    
    public String formatDate(Timestamp ts){

        LocalDateTime dateTime = ts.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        return dateTime.format(formatter);
    }
}
