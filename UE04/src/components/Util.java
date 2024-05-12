package components;

import repo.DataStorageException;
import repo.Datastruct;

import java.io.*;
import java.util.ArrayList;

public abstract class Util {
    private static Administration admin;

    public static void createAdministration(){
        admin = new Administration();
    }
    public static void deleteAdministration(){
        admin = null;
    }
    public static Administration getAdministration(){
        return admin;
    }

    static void safe(ArrayList<Datastruct> data) throws DataStorageException{
        try{
            FileOutputStream fos = new FileOutputStream("UE04/src/repo/UserStoryStorage.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Datastruct ds : data){
                oos.writeObject(ds);
            }
            oos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            throw new DataStorageException("A problem occures while saving the files");
        }
    }

    static ArrayList<Datastruct> load() throws DataStorageException{
        ArrayList<Datastruct> data = new ArrayList<>();
        
        try{
            FileInputStream fis = new FileInputStream("UE04/src/repo/UserStoryStorage.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Datastruct ds = (Datastruct) ois.readObject();
                    data.add(ds);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            throw new DataStorageException("A problem occured while loading the files");
        }
        return data;
    }
}