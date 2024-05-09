package main;

import java.util.ArrayList;
import data.*;
import repo.DataStorageException;
import repo.Datastruct;

public class Administration {
    private ArrayList<UserStory> userData; // Only userstorys, the related tasks are safed in the userstory
    private ArrayList<Task> taskData; // Tasks without a related userstory

    public Administration(){
        userData = new ArrayList<>();
        taskData = new ArrayList<>();
    }
    
    public void story(UserStory us){
        userData.add(us);
    }

    public void task(Task t){
        taskData.add(t);
    }

    public void assign(int user, int task){
        Boolean taskBoolean = false;
        Boolean userBoolean = false;
        // Safe User and Task after found
        UserStory tmpUser = null;
        Task tmpTask = null;

        for(UserStory ud : userData){
            if(ud.getID() == user){
                userBoolean = true;
                tmpUser = ud;
            }
            for(Task t : ud.getTasks()){
                if(t.getID() == user){
                    taskBoolean = true;
                    tmpTask = t;
                }
            }
        }
        for(Task t : taskData){
            if(t.getID() == user){
                taskBoolean = true;
                tmpTask = t;
            }
        }

        if(!userBoolean){
            System.err.println("Es gibt keinen User mit der ID: " + user);
            return;
        }
        if(!taskBoolean){
            System.err.println("Es gibt keinen Task mit der ID: " + task);
            return;
        }

        tmpUser.addTask(tmpTask);
    }

    public void stories(){
        userData.forEach(System.out::println);
    }

    public void tasks(){
        taskData.forEach(System.out::print);
    }
    
    public void load(){
        //To delete the existing files for loading the safed files
        userData.clear();
        taskData.clear();

        ArrayList<Datastruct> data = null;
        try{
            data = Util.load();
        }catch(DataStorageException e){
            e.printStackTrace();
        }
        for(Datastruct ds : data){
            if(ds.getClass() == UserStory.class){ //TESTEN ob hier ggf. ein Fehler auftritt. Ist die if-Abfrage korrekt?
                userData.add((UserStory)ds);
            }else if(ds.getClass() == Task.class){
                taskData.add((Task)ds);
            }
        }
    }

    public void save(){
        ArrayList<Datastruct> list = new ArrayList<Datastruct>();
        list.addAll(userData);
        list.addAll(taskData);
        try{
            Util.safe(list);
        }catch(DataStorageException e){
            e.printStackTrace();
        }
    }

    // In main die Eingabe und schleife für Programm
    // Hier die Logik für die Implementierung der verschiedenen Befehle
    // Alles wird über die Util gemacht. Darüber wird auch 'Administration' aufgerufen
}