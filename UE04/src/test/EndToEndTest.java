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

public class EndToEndTest {
    
    @BeforeEach
    public void setup(){
        Util.createAdministration();
    }

    @AfterEach
    public void teardown(){
        Util.deleteAdministration();
    }

    @Test
    public void stories(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
            Util.getAdministration().addstory(new UserStory(2, "test", "must-have"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Util.getAdministration().stories();
            String output = outputStream.toString().trim();
            assertEquals("[H\033[2JID: 1, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: keine\nID: 2, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: keine", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void tasks(){
        try {
            Util.getAdministration().addtask(new Task(5, "test"));
            Util.getAdministration().addtask(new Task(6, "test"));
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Util.getAdministration().tasks();;
            String output = outputStream.toString().trim();
            assertEquals("[H\033[2J\nID: 5, Beschreibung: test\nID: 6, Beschreibung: test", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void storyAndTask(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
            Util.getAdministration().addstory(new UserStory(2, "test", "must-have"));
            Util.getAdministration().addtask(new Task(5, "test"));
            Util.getAdministration().assign(1, 5);
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Util.getAdministration().stories();
            String output = outputStream.toString().trim();
            assertEquals("[H\033[2JID: 1, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: \nID: 5, Beschreibung: test\nID: 2, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: keine", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void storyAndTask2(){
        try {
            Util.getAdministration().addstory(new UserStory(1, "test", "must-have"));
            Util.getAdministration().addstory(new UserStory(2, "test", "must-have"));
            Util.getAdministration().addtask(new Task(5, "test"));
            Util.getAdministration().assign(1, 5);
            Util.getAdministration().assign(2, 5);
        } catch (Exception e) {
            Assertions.assertSame(1,0);
        }

        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Util.getAdministration().stories();
            String output = outputStream.toString().trim();
            assertEquals("[H\033[2JID: 1, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: \nID: 5, Beschreibung: test\nID: 2, Beschreibung: test, Priorität: must-have, Zugeordnete Tasks: \n" + //
                                "ID: 5, Beschreibung: test", output);
        } finally {
            System.setOut(originalOut);
        }
    }
}