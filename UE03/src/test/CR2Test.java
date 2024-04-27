package test;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.CardBoxUtil;
import main.DeveloperCard;
import main.EnduserCard;
import main.PersonCard;

public class CR2Test {
    ArrayList<PersonCard> liste;

    /*
     * Testanforderungen:
     * Speichern und laden der aktuellen CardBox
     * Überschreibung einer gefüllten CardBox nach dem laden
     */

    @BeforeEach
    public void setup(){
        CardBoxUtil.createCardBox();
        liste = new ArrayList<>();
        liste.add(new EnduserCard("Marc", "Mueller", false, 0));
        liste.add(new EnduserCard("Hans", "Freier", false, 1));
        liste.add(new EnduserCard("Nico", "Meyer", true, 2));
        liste.add(new DeveloperCard("Jonas", "Schueren", true, 3));
        liste.add(new DeveloperCard("Frida", "Faula", true, 4));
    }

    @AfterEach
    public void teardown(){
        CardBoxUtil.deleteCardBox();
        liste = null;
    }

    @Test
    public void function(){
        //Cardbox befuellen
        for(PersonCard pc : liste){
            try{
                CardBoxUtil.getCardBox().addPersonCard(pc);
            }catch(Exception e){
                Assertions.assertSame(1, 0); //Error, thrown Exception
            };
        }
        
        //Cardbox speichern
        try{
            CardBoxUtil.getCardBox().safe();
        }catch(Exception e){
            Assertions.assertSame(1, 0); //Error, thrown Exception
        };

        //Cardbox loeschen und neu erstellen
        CardBoxUtil.deleteCardBox();
        CardBoxUtil.createCardBox();

        //neue Cardbox auf leere pruefen
        Assertions.assertSame(CardBoxUtil.getCardBox().size(), 0);

        //Cardbox laden
        try{
            CardBoxUtil.getCardBox().load();
        }catch(Exception e){
            Assertions.assertSame(1, 0); //Error, thrown Exception
        };

        //Assertion
        Assertions.assertEquals(liste.toString(), CardBoxUtil.getCardBox().getCurrentList().toString());
    }

    @Test
    public void erase(){
        //Leere Cardbox speichern
        try{
        CardBoxUtil.getCardBox().safe();
        }catch(Exception e){
            Assertions.assertSame(1, 0); //Error, thrown Exception
        };

        //Cardbox befuellen
        for(PersonCard pc : liste){
            try{
                CardBoxUtil.getCardBox().addPersonCard(pc);
            }catch(Exception e){
                Assertions.assertSame(1, 0); //Error, thrown Exception
            };
        }

        //Leere Cardbox laden
        try{
            CardBoxUtil.getCardBox().load();
        }catch(Exception e){
            Assertions.assertSame(1, 0); //Error, thrown Exception
        };

        //Assertion
        Assertions.assertSame(0, CardBoxUtil.getCardBox().size());
    }
}