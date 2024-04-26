package repo;

public class CardboxException extends Exception{
    public CardboxException(){
        System.out.println("Das CardBox-Objekt ist bereits vorhanden");
    }

    public CardboxException(String msg){
        System.out.println("Das CardBox-Objekt mit der ID " + msg + " ist bereits vorhanden");
    }
}