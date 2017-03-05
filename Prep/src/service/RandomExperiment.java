package service;

import util.Printer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 3/3/2017.
 */
public class RandomExperiment {

    public static void testZonedDateTime() {
        ZonedDateTime lipsDBBirthday = ZonedDateTime.parse("2017-02-24T00:05:00Z[America/Los_Angeles]");
        ZonedDateTime later = ZonedDateTime.parse("2017-02-25T00:00:00Z[America/Los_Angeles]");
        ZonedDateTime before = ZonedDateTime.parse("2017-02-24T00:00:00Z[America/Los_Angeles]");

        ZonedDateTime before2 = ZonedDateTime.parse("2017-02-24T00:10:00Z[Europe/Paris]");

        if (later.toInstant().isAfter(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }

        if (before.toInstant().isBefore(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }

        if (before2.toInstant().isBefore(lipsDBBirthday.toInstant())) {
            Printer.println("Yes!");
        } else {
            Printer.println("??");
        }
    }
}
