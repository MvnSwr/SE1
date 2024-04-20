package main;

public class CardboxException extends Exception{
    CardboxException(){
        System.out.println("Das CardBox-Objekt ist bereits vorhanden");
    }

    CardboxException(String msg){
        System.out.println("Das CardBox-Objekt mit der ID " + msg + " ist bereits vorhanden");
    }
}