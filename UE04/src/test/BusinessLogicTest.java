package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import components.Util;
import model.Task;
import model.UserStory;
import repo.ExistingException;

public class BusinessLogicTest {
    
    @BeforeEach
    public void setup(){
        Util.createAdministration();
    }

    @AfterEach
    public void teardown(){
        Util.deleteAdministration();
    }

    @Test
    public void newAdmin(){
        try {
            Util.getAdministration().addtask(new Task(1, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }
        Util.deleteAdministration();
        Util.createAdministration();
        Assertions.assertFalse(Util.getAdministration().hasTask(1));
    }

    @Test
    public void hasTask(){
        try {
            Util.getAdministration().addtask(new Task(1, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }
        Assertions.assertTrue(Util.getAdministration().hasTask(1));
    }

    @Test
    public void hasUser(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }
        Assertions.assertTrue(Util.getAdministration().hasUser(1));
    }

    @Test
    public void persistance(){
        try {
            Util.getAdministration().addtask(new Task(1, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }
        Util.getAdministration().save();

        Util.deleteAdministration();
        Util.createAdministration();
        
        Util.getAdministration().load();
        Assertions.assertTrue(Util.getAdministration().hasTask(1));
    }

    @Test
    public void assign(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
            Util.getAdministration().addtask(new Task(2, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        try{
            Util.getAdministration().assign(1, 2);
        }catch(ExistingException e){
            Assertions.assertSame(1, 0);
        }

        // asserting the output-String. Output is testet in the EndToEndTest
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Util.getAdministration().stories();
            String output = outputStream.toString().trim();
            assertEquals("[H\033[2JID: 1, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: \nID: 2, Beschreibung: test", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void addUserException(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        Assertions.assertThrows(ExistingException.class, () -> {
            Util.getAdministration().addstory(new UserStory(1, "wrong", "could-have"));
        });
    }

    @Test
    public void addTaskException(){
        try {
            Util.getAdministration().addtask(new Task(1, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        Assertions.assertThrows(ExistingException.class, () -> {
            Util.getAdministration().addtask(new Task(1, "wrong"));
        });
    }

    @Test
    public void assignUserException(){
        Assertions.assertThrows(ExistingException.class, () -> {
            Util.getAdministration().assign(0, 0);
        });
    }

    @Test
    public void assignTaskException(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        Assertions.assertThrows(ExistingException.class, () -> {
            Util.getAdministration().assign(1, 0);
        });
    }
}