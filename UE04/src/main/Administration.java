package main;

import java.util.ArrayList;
import data.*;
import repo.DataStorageException;
import repo.Datastruct;
import repo.ExistingException;

public class Administration {
    private ArrayList<UserStory> userData; // Only userstorys, the related tasks are safed in the userstory
    private ArrayList<Task> taskData; // Tasks without a related userstory

    public Administration(){
        userData = new ArrayList<>();
        taskData = new ArrayList<>();
    }
    
    public void addstory(UserStory us) throws ExistingException{
        for(UserStory s : userData){
            if(s.getID() == us.getID()){
                throw new ExistingException();
            }
        }
        userData.add(us);
    }

    public void addtask(Task t) throws ExistingException{
        for(UserStory ud : userData){
            for(Task ts : ud.getTasks()){
                if(ts.getID() == t.getID()){
                    throw new ExistingException();
                }
            }
        }
        for(Task ts : taskData){
            if(ts.getID() == t.getID()){
                throw new ExistingException();
            }
        }

        taskData.add(t);
    }

    public void assign(int user, int task) throws ExistingException{
        Boolean taskBoolean = false;
        Boolean userBoolean = false;
        // Safe User and Task after found
        UserStory tmpUser = null;
        Task tmpTask = null;
        Boolean found = false;

        for(Task t : taskData){
            if(t.getID() == task){
                taskBoolean = true;
                found = true;
                tmpTask = t;
            }
        }

        if(!found){
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
        }else{
            for(UserStory ud : userData){
                if(ud.getID() == user){
                    userBoolean = true;
                    tmpUser = ud;
                }
            } 
        }

        if(!userBoolean){
            System.err.println("Es gibt keinen User mit der ID: " + user);
            throw new ExistingException();
        }
        if(!taskBoolean){
            System.err.println("Es gibt keinen Task mit der ID: " + task);
            throw new ExistingException();
        }

        tmpUser.addTask(tmpTask);
    }

    public void stories(){
        System.out.print("\033[H\033[2J"); // clear console
        userData.forEach(System.out::println);
    }

    public void tasks(){
        System.out.print("\033[H\033[2J"); // clear console
        taskData.forEach(System.out::print);
    }
    
    public void load(){
        System.out.print("\033[H\033[2J"); // clear console

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
        System.out.print("\033[H\033[2J"); // clear console

        ArrayList<Datastruct> list = new ArrayList<Datastruct>();
        list.addAll(userData);
        list.addAll(taskData);
        try{
            Util.safe(list);
        }catch(DataStorageException e){
            e.printStackTrace();
        }
    }

    public Boolean hasUser(int id){
        for(UserStory ud : userData){
            if(ud.getID() == id){
                return true;
            }
        }
        return false;
    }

    public Boolean hasTask(int id){
        for(Task t : taskData){
            if(t.getID() == id){
                return true;
            }
        }
        return false;
    }

    // In main die Eingabe und schleife für Programm
    // Hier die Logik für die Implementierung der verschiedenen Befehle
    // Alles wird über die Util gemacht. Darüber wird auch 'Administration' aufgerufen
}