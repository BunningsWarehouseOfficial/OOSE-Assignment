package krados.oose.assignment.controller;

import java.text.DecimalFormat;

public class Controller {
  /*Takes in a double and formats it as a String, cleaning it so that it can be displayed to the UI without cluttering
    the UI by truncating unnecessary zeros and rounding the 2 decimal places*/
    public static String cleanDouble(double num) {
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
