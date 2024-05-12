package main;
import java.util.*;

import model.*;
import components.Util;
import repo.ExistingException;

public class Main {
    public static void main(String[] args){
        Util.createAdministration();
        System.out.print("\033[H\033[2J"); // clear console
        System.out.println("Für Hilfe zu den Befehlen 'help' eingeben");

        while (true) {
            input();
        }
    }

    @SuppressWarnings("resource")
    private static void input(){
        Scanner sc = new Scanner(System.in);

        try{
            String line = sc.next(); // Erster Parameter
    
            if(line.equalsIgnoreCase("story")){ 
                try{
                    int id = 0;
                    try{
                        id = Integer.parseInt(sc.next());
                    }catch(NumberFormatException e){
                        System.err.println("Der erste Parameter ist keine Zahl");
                        return;
                    }

                    String descrip = sc.nextLine();
                    String prio = "";

                    // assign prio
                    if(descrip.contains("must-have")){
                        prio = "must-have";
                    }else if(descrip.contains("should-have")){
                        prio = "should-have";
                    }else if(descrip.contains("could-have")){
                        prio = "could-have";
                    }else if(descrip.contains("wont-have")){
                        prio = "wont-have";
                    }

                    // delete prio
                    descrip = descrip.replace("must-have", "").replace("should-have", "").replace("could-have", "").replace("wont-have", "");
                    descrip = descrip.replace("\" ", "").replace("\"", "");
    
                    if(!prio.isEmpty()){
                        try{
                            Util.getAdministration().addstory(new UserStory(id, descrip, prio));
                            System.out.println("UserStory: " + id + " " + descrip + " " + prio + " wurde gespeichert!");
                        }catch(ExistingException e){
                            System.err.println("Es gibt bereits eine UserStory mit der ID: " + id);
                        }
                    }else{
                        System.err.println("Die Priorität ist ungültig. Es muss 'must-have', 'should-have', 'could-have' oder 'wont-have' sein!\nProbiere mal 'help'");
                    }
                }catch(Exception e){
                    System.err.println("Es wurden nicht die richtigen Argumente übergeben\nProbiere mal 'help'");
                    e.printStackTrace();
                }

            }else if(line.equalsIgnoreCase("task")){ // add task
                try{
                    int id = 0;

                    try{
                        id = Integer.parseInt(sc.next());
                    }catch(NumberFormatException e){
                        System.err.println("Der erste Parameter ist keine Zahl");
                    }

                    String descrip = sc.nextLine();
                    descrip = descrip.replace("\" ", "").replace("\"", "");

                    try{
                        Util.getAdministration().addtask(new Task(id, descrip));
                        System.out.println("Task: " + id + " " + descrip + " wurde gespeichert!");
                    }catch(ExistingException e){
                        System.err.println("Es gibt bereits eine Task mit der ID: " + id);
                    }
                }catch(Exception e){
                    System.err.println("Es wurden nicht die richtigen Argumente übergeben\nProbiere mal 'help'");
                    sc.close();
                    return;
                }

            }else if(line.equalsIgnoreCase("assign")){ // assign a task to a story
                try{
                    int storyId = 0;
                    int taskId = 0;

                    try{
                        storyId = Integer.parseInt(sc.next());
                        taskId = Integer.parseInt(sc.next());
                    }catch(NumberFormatException e){
                        System.err.println("Einer der Parameter ist keine Zahl");
                        return;
                    }

                    Boolean check = true;
                    
                    if(!Util.getAdministration().hasUser(storyId)){
                        System.err.println("Die Story-ID " + storyId + " existiert nicht!");
                        check = false;
                    }
                    if(!Util.getAdministration().hasTask(taskId)){
                        System.err.println("Die Task-ID " + taskId + " existiert nicht!");
                        check = false;
                    }
                    
                    if(!check){
                        sc.close();
                        return;
                    }

                    try{
                        Util.getAdministration().assign(storyId, taskId);
                        System.out.println("Zuweisung war erfolgreich!");
                    }catch(ExistingException e){
                    }
                }catch(Exception e){
                    System.err.println("Es wurden nicht die richtigen Argumente übergeben\nProbiere mal 'help'");
                    sc.close();
                    return;
                }

            }else if(line.equalsIgnoreCase("stories")){ // show stories with assigned tasks
                Util.getAdministration().stories();

            }else if(line.equalsIgnoreCase("tasks")){ // show every task
                Util.getAdministration().tasks();

            }else if(line.equalsIgnoreCase("load")){
                Util.getAdministration().load();
                System.out.println("Daten wurden geladen!");
                
            }else if(line.equalsIgnoreCase("save")){
                Util.getAdministration().save();
                System.out.println("Daten wurden gespeichert!");

            }else if(line.equalsIgnoreCase("help")){
                // Anweisung für eine Help Anweisung

                System.out.println("Es gibt folgende Befehle zur auswahl:");
                System.out.println("'story' <ID> <\"Bescheibung\"> <Priorität nach MoSCoW(must-have, should-have, could-have, wont-have)>");
                System.out.println("'task' <ID> <\"Beschreibung\">");
                System.out.println("'assign' <Story-ID> <Task-ID>");
                System.out.println("'stories'");
                System.out.println("'tasks'");
                System.out.println("'load'");
                System.out.println("'save'");
                System.out.println("'exit'");
            }else if(line.equalsIgnoreCase("exit")){
                sc.close();
                System.exit(0);
            }else{
                System.err.println("Der Befehl stimmt nicht. Es muss 'story', 'task', 'assign', 'stories', 'tasks', 'load' oder 'safe' sein!\nProbiere mal 'help'");
                sc.close();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}