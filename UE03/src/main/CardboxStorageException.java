package main;

public class CardboxStorageException extends Exception{
    CardboxStorageException(){
        super();
    }

    CardboxStorageException(String msg){
        super(msg);
    }
}