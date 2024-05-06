package repo;

public class DataStorageException extends Exception{
    public DataStorageException(){
        super();
    }

    public DataStorageException(String msg){
        super(msg);
    }
}