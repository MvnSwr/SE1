package main;

import java.io.Serializable;

public class DeveloperCard implements PersonCard, Serializable{
    private String firstName;
    private String lastName;
    private Boolean coffee;
    private int ID;

    public DeveloperCard(String firstName, String lastName, Boolean coffee, int ID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.coffee = coffee;
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

    public Boolean hasEnoughCoffee(){
        return coffee;
    }

    public String toString(){
        return "ID = " + ID + ", Vorname = " + firstName + ", Nachname = " + lastName + ", hasEnoughCoffee = " + coffee; 
    }
    
}