package service;

import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rpsin on 3/3/2017.
 */
public class RandomExperiment {

    public static double randomExperiment() {
        ZonedDateTime lipsDBBirthday = ZonedDateTime.parse("2017-02-24T00:00:00[America/Los_Angeles]");
        ZonedDateTime toCompare = ZonedDateTime.parse("2017-02-25T00:00:00[America/Los_Angeles]");

        if (toCompare.toInstant()isBefore(lipsDBBirthday.toInstant())) {
            Printer.print("Yess");
        } else {
            Printer.print("No");
        }
    }
}
