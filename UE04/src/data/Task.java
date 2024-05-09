package data;

public class Task extends DatastructImpl{

    public String toString(){
        return "\nID: " + this.getID() + ", Beschreibung: " + this.getDescription();
    }
}