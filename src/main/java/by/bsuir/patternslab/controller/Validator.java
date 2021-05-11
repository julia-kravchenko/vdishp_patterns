package by.bsuir.patternslab.controller;

public class Validator {
    public static boolean isInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
