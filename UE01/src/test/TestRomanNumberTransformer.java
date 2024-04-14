package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import main.RomanNumberTransformer;

public class TestRomanNumberTransformer {
    RomanNumberTransformer rn;

    @BeforeEach
    public void setup(){
        this.rn = new RomanNumberTransformer();
    }

    @AfterEach
    public void teardown(){
        this.rn = null;
    }

    @Test
    public void case1(){
        assertEquals("Der Wert liegt ausserhalb des Wertebereiches!! (1-3000)", this.rn.transformNumber(-15));
    }

    @Test
    public void case2(){
        assertEquals("Der Wert liegt ausserhalb des Wertebereiches!! (1-3000)", this.rn.transformNumber(0));
    }

    @Test
    public void case3(){
        assertEquals("I", this.rn.transformNumber(1));
    }

    @Test
    public void case4(){
        assertEquals("MCCCLII", this.rn.transformNumber(1352));
    }

    @Test
    public void case5(){
        assertEquals("MMM", this.rn.transformNumber(3000));
    }

    @Test
    public void case6(){
        assertEquals("Der Wert liegt ausserhalb des Wertebereiches!! (1-3000)", this.rn.transformNumber(3001));
    }

    @Test
    public void case7(){
        assertEquals("Der Wert liegt ausserhalb des Wertebereiches!! (1-3000)", this.rn.transformNumber(15000));
    }
}