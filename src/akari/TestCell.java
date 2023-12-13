package akari;

import akari.Cell.State;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCell {
    Cell c1, c2, c3, c4;
    
	@Before
	public void setUP() throws Exception {
        c1 = new Cell(State.WHITE);
        c2 = new Cell(State.BLACK);
        c3 = new Cell(State.LAMP);
        c4 = new Cell(State.MARKED);
	}

	@Test
	public void testGetState() {
        assertEquals("c1.getState", c1.getState(), State.WHITE);
        assertEquals("c2.getState", c2.getState(), State.BLACK);
        assertEquals("c3.getState", c3.getState(), State.LAMP);
        assertEquals("c4.getState", c4.getState(), State.MARKED);
	}
	
    @Test
    public void testGetNumber() {
        assertEquals("c1.getNumber", c1.getNumber(), -1);
    }
    
    @Test
    public void testGetLightLevel() {
        assertEquals("c1.getLightLevel", c1.getLightLevel(), 0);
    }
    
    @Test
    public void testSetState() {
    	c3.setState(State.MARKED);
        assertEquals("c3.setState", c3.getState(), State.MARKED);
    }

    @Test
    public void testSetNumber() {
    	c1.setNumber(2);
        assertEquals("c1.setNumber", c1.getNumber(), 2);
    }
    
    @Test
    public void testSetLightLevel() {
    	c3.setLightLevel(1);
        assertEquals("c3.setLightLevel", c3.getLightLevel(), 1);
    }
    
    @Test
    public void testAddLight() {
    	c1.addLight();
        assertEquals("c1.addLight", c1.getLightLevel(), 1);
    }
    
    @Test
    public void testSubLight() {
    	c1.addLight();
    	c1.subLight();
        assertEquals("c1.subLight", c1.getLightLevel(), 0);
    }

}
