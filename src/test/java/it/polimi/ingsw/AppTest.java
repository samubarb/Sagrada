package it.polimi.ingsw;

/**
 * Unit test for simple App.
 *//*
public class AppTest
{
    /**
     * Rigorous Test :-)
     *//*
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}*/
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class FirstJUnit5Tests {
    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }
}
