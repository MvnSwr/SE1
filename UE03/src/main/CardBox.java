package main;

import java.util.ArrayList;
import java.io.*;

public class CardBox {
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

    //FA3
    public void showContent(){
        for (PersonCard pc : data){
            System.out.println(pc.toString());
        }
    }

    //FA4
    public int size(){
        return data.size();
    }

    //CR2
    public void safe() throws CardboxStorageException{
        try{
            FileOutputStream fos = new FileOutputStream("CardBoxStorage.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(PersonCard pc : data){
                oos.writeObject(pc);
            }
            oos.close();
        }catch(Exception e){
            throw new CardboxStorageException("A problem occures while saving the files");
        }
    }

    public void load() throws CardboxStorageException{
        data.clear();

        try{
            FileInputStream fis = new FileInputStream("CardBoxStorage.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(ois.available() > 0){
                data.add((PersonCard) ois.readObject());
            }
            ois.close();
        }catch(Exception e){
            throw new CardboxStorageException("A problem occured while loading the files");
        }
    }
}