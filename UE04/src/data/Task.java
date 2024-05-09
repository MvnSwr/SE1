package data;

public class Task extends DatastructImpl{

    public Task(int id, String desc){
        this.setID(id);
        this.setDescription(desc);
    }

    public String toString(){
        return "\nID: " + this.getID() + ", Beschreibung: " + this.getDescription();
    }
}