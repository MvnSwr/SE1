package model;

import repo.Datastruct;
import java.io.Serializable;

public class DatastructImpl implements Datastruct, Serializable{
    private int ID;
    private String description;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int id) {
        this.ID = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
}