package main;

public class Util {
    public static String getRomanNumber(int number){
        return new RomanNumberTransformer().transformNumber(number);
    }
}
