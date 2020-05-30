package krados.oose.assignment.controller;

import java.text.DecimalFormat;

public class Controller {
    //todo explain that it's meant only for string formatting
    public static String cleanDouble(double num) { //test
        String s = "";
        if (num % 1 == 0) {
            s += (int)num; //Truncate the decimal places to remove unnecessary cluttering of UI
        }
        else {
            DecimalFormat twoDP = new DecimalFormat("#.##");
            s += twoDP.format(num); //Shorten decimal to prevent clutter
        }
        return s;
    }
}
