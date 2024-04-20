package main;

public class EnduserCard implements PersonCard{
    private String firstName;
    private String lastName;
    private Boolean hungry;
    private int ID;

    public EnduserCard(String firstName, String lastName, Boolean hungry, int ID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.hungry = hungry;
        this.ID = ID;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getId() {
        return ID;
    }

    public Boolean isHungry(){
        return hungry;
    }

    public String toString(){
        return "ID = " + ID + ", Vorname = " + firstName + ", Nachname = " + lastName + ", isHungry = " + hungry; 
    }
}