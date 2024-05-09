package main;
import java.util.*;

import data.Task;
import data.UserStory;

public class Main {
    // Logik in der Abfrage der Eingaben und Erstellung der Objekte zur Übergabe an Util->Administration
    public static void main(String[] args){
        Util.createAdministration();
        input();
    }

    private static void input(){
        Scanner sc = new Scanner(System.in);
        try{
            String line = sc.next(); // First paramter

            if(line.equalsIgnoreCase("story")){ // add story
                try{
                    Boolean prioTest = false;
                    int id = Integer.parseInt(sc.next());
                    String descrip = sc.next();
                    String prio = sc.next();
                    if(prio.equalsIgnoreCase("must-have")){
                        prioTest = true;
                    }else if(prio.equalsIgnoreCase("should-have")){
                        prioTest = true;
                    }else if(prio.equalsIgnoreCase("could-have")){
                        prioTest = true;
                    }else if(prio.equalsIgnoreCase("wont-have")){
                        prioTest = true;
                    }
                    if(!prioTest){
                        if(sc.hasNext()){
                            System.err.println("Es wurden zu viele Argumente übergeben\nProbiere mal 'help'");
                            throw new Exception();
                        }
                        Util.getAdministration().story(new UserStory(id, descrip, prio));
                    }else{
                        System.err.println("Die Priorität stimmt nicht. Es muss 'must-have', 'should-have', 'could-have' oder 'wont-have' sein!\nProbiere mal 'help'");
                    }
                }catch(Exception e){
                    System.err.println("Es wurden nicht die richtigen Argumente übergeben\nProbiere mal 'help'");
                }
            }else if(line.equalsIgnoreCase("task")){ // add task
                try{
                    int id = Integer.parseInt(sc.next());
                    String descrip = sc.next();
                    if(sc.hasNext()){
                        System.err.println("Es wurden zu viele Argumente übergeben\nProbiere mal 'help'");
                        throw new Exception();
                    }
                    Util.getAdministration().task(new Task(id, descrip));
                }catch(Exception e){
                    System.err.println("Es wurden nicht die richtigen Argumente übergeben\nProbiere mal 'help'");
                }
            }else if(line.equalsIgnoreCase("assign")){ // Noch nicht fertig
                try{
                    int storyId = Integer.parseInt(sc.next());
                    int taskId = Integer.parseInt(sc.next());
                }catch(Exception e){
                    
                }
            }else if(line.equalsIgnoreCase("stories")){
                ;
            }else if(line.equalsIgnoreCase("tasks")){
                ;
            }else if(line.equalsIgnoreCase("load")){
                ;
            }else if(line.equalsIgnoreCase("save")){
                ;
            }else if(line.equalsIgnoreCase("help")){
                // Anweisung für eine Help Anweisung
            }else{
                System.err.println("Der Befehl stimmt nicht. Es muss 'story', 'task', 'assign', 'stories', 'tasks', 'load' oder 'safe' sein!\nProbiere mal 'help'");
            }
        }catch(Exception e){

        }finally{
            sc.close();
        }
    }
}