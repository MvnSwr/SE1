package test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import main.CardBox;
import repo.CardboxException;
import main.DeveloperCard;
import main.EnduserCard;
import main.PersonCardView;

import java.io.*;

public class CardBoxTest {
    private CardBox cd;
    private DeveloperCard dc1;
    private EnduserCard ec1;
    private PersonCardView pcw; //Change to UE02

     @BeforeEach
     public void setup(){
        this.cd = new CardBox();
        this.pcw = new PersonCardView(); //Change to UE02
        this.dc1 = new DeveloperCard("Manuel", "Meyer", true, 0);
        this.ec1 = new EnduserCard("Markus", "Mueller", true, 1);
     }

     @AfterEach
     public void teardown(){
        this.cd = null;
        this.pcw = null; //Change to UE02
        this.dc1 = null;
        this.ec1 = null;
     }

     @Test
     public void add(){
      assertSame(0, cd.size());
      try{
         cd.addPersonCard(dc1);
      }catch(CardboxException e){};
      assertSame(1, cd.size());
      try{
         cd.addPersonCard(ec1);
      }catch(CardboxException e){};
      assertSame(2, cd.size());
     }

     @Test
     public void delete(){
      try{
         cd.addPersonCard(dc1);
      }catch(CardboxException e){};
      cd.deletePersonCard(0);
      assertSame(0, cd.size());
     }

     @Test
     public void addException(){
      try{
         cd.addPersonCard(dc1);
      }catch(CardboxException e){};
      assertThrows(CardboxException.class, () -> {
         cd.addPersonCard(dc1);
      });
     }

     @Test
     public void deleteException(){
      try{
         cd.addPersonCard(dc1);
      }catch(CardboxException e){};

      PrintStream originalErr = System.err;
      try {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         System.setErr(new PrintStream(outputStream));
         cd.deletePersonCard(1);
         String output = outputStream.toString().trim();
         assertEquals("Das Objekt ist nicht in der CardBox", output);
        } finally {
         System.setErr(originalErr);
        }
     }

     @Test
     public void toStringTest1(){
      try{
         cd.addPersonCard(dc1);
      }catch(CardboxException e){};

      PrintStream originalOut = System.out;
      try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          System.setOut(new PrintStream(outputStream));
          pcw.showContent(cd.getCurrentList()); //Change to UE02
          String output = outputStream.toString().trim();
          assertEquals("ID = 0, Vorname = Manuel, Nachname = Meyer, hasEnoughCoffee = true", output);
      } finally {
          System.setOut(originalOut);
      }
     }

     @Test
     public void toStringTest2(){
      try{
         cd.addPersonCard(ec1);
      }catch(CardboxException e){};

      PrintStream originalOut = System.out;
      try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          System.setOut(new PrintStream(outputStream));
          pcw.showContent(cd.getCurrentList()); //Change to UE02
          String output = outputStream.toString().trim();
          assertEquals("ID = 1, Vorname = Markus, Nachname = Mueller, isHungry = true", output);
      } finally {
          System.setOut(originalOut);
      }
     }
}