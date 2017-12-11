package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * 
 * @author Sarah Heckman
 *
 * Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest extends TestCase {

    private CoffeeMaker cm;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;

    protected void setUp() throws Exception {
        cm = new CoffeeMaker();

        //Set up for r1
        r1 = mock(Recipe.class);

        //Set up for r2
        r2 = mock(Recipe.class);

        //Set up for r3
        r3 = mock(Recipe.class);

        //Set up for r4
        r4 = mock(Recipe.class);

        //Set up for r5
        r5 = mock(Recipe.class);

        super.setUp();
    }

    // No mocked functionality needed
    public void testAddInventory() {
        try {
            cm.addInventory("4", "7", "0", "9");
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
    }

    // No mocked functionality needed
	public void testAddInventoryException() {
		try {
			cm.addInventory("4", "-1", "asdf", "3");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			//success if thrown
		}
	}

	public void testMakeCoffee() {
        when(r1.getPrice()).thenReturn(50);
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}

	public void testAddRecipe() {
        assertTrue(cm.addRecipe(r1));
    }

    public void testAddDuplicateRecipe() {
        cm.addRecipe(r1);
        assertFalse(cm.addRecipe(r1));
    }

    public void testAddOverMaxRecipes() {
        cm.addRecipe(r1);
        cm.addRecipe(r2);
        cm.addRecipe(r3);
        cm.addRecipe(r4);
        assertFalse(cm.addRecipe(r5));
    }

    public void testDeleteRecipe() {
        when(r1.getName()).thenReturn("Recipe 1");
        cm.addRecipe(r1);
        assertEquals("Recipe 1", cm.deleteRecipe(0));
    }

    public void testDeleteNullRecipe() {
        assertNull(cm.deleteRecipe(0));
    }

    public void testEditRecipe() {
        when(r1.getName()).thenReturn("Recipe 1");
        cm.addRecipe(r1);
        cm.editRecipe(0, r2);
    }

    public void testEditNullRecipe() {
        assertNull(cm.editRecipe(0, r2));
    }

    public void testGetRecipes() {
        cm.addRecipe(r1);
        cm.addRecipe(r2);
        cm.addRecipe(r3);
        cm.addRecipe(r4);
        when(r1.getName()).thenReturn("Recipe 1");
        when(r2.getName()).thenReturn("Recipe 2");
        when(r3.getName()).thenReturn("Recipe 3");
        when(r4.getName()).thenReturn("Recipe 4");

        Recipe[] array = new Recipe[] {r1, r2, r3, r4};
        assertSame(array[0].getName(), cm.getRecipes()[0].getName());
        assertSame(array[1].getName(), cm.getRecipes()[1].getName());
        assertSame(array[2].getName(), cm.getRecipes()[2].getName());
        assertSame(array[3].getName(), cm.getRecipes()[3].getName());
    }

    public void testCheckInventory() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", cm.checkInventory());
    }

}


