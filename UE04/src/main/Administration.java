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
    
    public void story(){}
    public void task(){}
    public void assign(){}
    public void stories(){}
    public void tasks(){}
    
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