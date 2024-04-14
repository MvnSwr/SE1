package main;

public class Client {
    public void printTransformation(int number) {
        String result = Util.getRomanNumber(number);
        System.out.println("Die römische Schreibweise der Zahl " + number + " ist: " + result);
    }
}