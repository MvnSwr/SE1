package main;
import java.util.*;

public class Main {
    // Logik in der Abfrage der Eingaben und Erstellung der Objekte zur Übergabe an Util->Administration
    public static void main(String[] args){
        input();
    }

    private static String[] input(){
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        

        sc.close();
        return null;
    }

    // Check if the first input is correct
    private static Boolean checkAction(String str){
        if(str.equalsIgnoreCase("story")){
            return true;
        }
        if(str.equalsIgnoreCase("task")){
            return true;
        }
        if(str.equalsIgnoreCase("assign")){
            return true;
        }
        if(str.equalsIgnoreCase("stories")){
            return true;
        }
        if(str.equalsIgnoreCase("tasks")){
            return true;
        }
        if(str.equalsIgnoreCase("load")){
            return true;
        }
        if(str.equalsIgnoreCase("save")){
            return true;
        }
        return false;
    }
}