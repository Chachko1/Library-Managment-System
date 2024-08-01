package org.librarymanagementsystem.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeZoneController {

    @GetMapping("/timezone")
    public String getTimeZone() {
        ZonedDateTime now = ZonedDateTime.now();
        return "Current server time: " + now.format(DateTimeFormatter.RFC_1123_DATE_TIME) +
                " Time zone: " + now.getZone();
    }
}