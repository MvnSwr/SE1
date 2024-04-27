package main;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import repo.*;

public class CardBox{
    // datastructure to safe classes that extend the interface "PersonCard"
    private ArrayList<PersonCard> data;

    // Constructor
    public CardBox(){
        data = new ArrayList<>();
    }

    //FA1
    public void addPersonCard(PersonCard personCard) throws CardboxException{
        for (PersonCard pc : data) {
            if(personCard.getId() == pc.getId()){
                throw new CardboxException();
            }   
        }
        data.add(personCard);
    }

    //FA2
    public String deletePersonCard(int id){
        for (PersonCard pc : data) {
            if(pc.getId() == id){
                data.remove(pc);
                return null;
            }
        }
        System.err.println("Das Objekt ist nicht in der CardBox");
        /*
            Wenn wir hier mit Exceptions arbeiten würden, dann könnten wir diese auch noch weiter geben.
            Dadurch könnten wir diese auch noch an einer anderen Stelle behandeln. Mit der aktuellen Version
            geben wir lediglich eine Fehlermeldung in der Konsole aus
        */
        return null;
    }

    //FA4
    public int size(){
        return data.size();
    }

    //CR2
    public void safe() throws CardboxStorageException{
        try{
            FileOutputStream fos = new FileOutputStream("UE03/src/repo/CardBoxStorage.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(PersonCard pc : data){
                oos.writeObject(pc);
            }
            oos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            //PersonCard Objekte muessen Serializable sein
            e.printStackTrace();
        }catch(Exception e){
            throw new CardboxStorageException("A problem occures while saving the files");
        }
    }

    public void load() throws CardboxStorageException{
        data.clear();

        try{
            FileInputStream fis = new FileInputStream("UE03/src/repo/CardBoxStorage.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    PersonCard pc = (PersonCard) ois.readObject();
                    data.add(pc);
                } catch (EOFException e) {
                    break; //ois.available() ist nicht zuverlaessig genug. readObject gibt direkt null zurueck
                }
            }
            ois.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            throw new CardboxStorageException("A problem occured while loading the files");
        }
    }

    //CR3
    public List<PersonCard> getCurrentList(){
        return data;
    }
}