package edu.ncsu.csc326.coffeemaker;

import com.sun.org.apache.xpath.internal.SourceTree;
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
    private Inventory inv;
    private RecipeBook recipeBook;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;

    protected void setUp() throws Exception {
        r1 = mock(Recipe.class);
        r2 = mock(Recipe.class);
        r3 = mock(Recipe.class);
        r4 = mock(Recipe.class);
        r5 = mock(Recipe.class);
        inv = mock(Inventory.class);
        recipeBook = mock(RecipeBook.class);
        cm = new CoffeeMaker(inv, recipeBook);


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
        when(cm.getRecipes()).thenReturn(new Recipe[]{r1});
        when(inv.useIngredients(r1)).thenReturn(true);
		assertEquals(25, cm.makeCoffee(0, 75));
	}

	public void testAddRecipe() {
        when(recipeBook.addRecipe(r1)).thenReturn(true);
        assertTrue(cm.addRecipe(r1));
        verify(recipeBook).addRecipe(r1);
    }

    public void testAddDuplicateRecipe() {
        when(cm.addRecipe(r1)).thenReturn(true);
        cm.addRecipe(r1);
        when(cm.addRecipe(r1)).thenReturn(false);
        assertFalse(cm.addRecipe(r1));
        verify(recipeBook, times(2)).addRecipe(r1);
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
        when(recipeBook.deleteRecipe(0)).thenReturn("Recipe 1");
        //when(recipeBook.deleteRecipe(0)).thenReturn(r1.getName());
        cm.addRecipe(r1);
        assertEquals("Recipe 1", cm.deleteRecipe(0));
        verify(recipeBook).deleteRecipe(0);
    }

    public void testDeleteNullRecipe() {
        when(recipeBook.deleteRecipe(anyInt())).thenReturn(null);
        assertNull(cm.deleteRecipe(0));
        verify(recipeBook).deleteRecipe(0);
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

        when(recipeBook.getRecipes()).thenReturn(new Recipe[]{r1, r2, r3, r4});


        Recipe[] array = new Recipe[] {r1, r2, r3, r4};
        assertSame(array[0].getName(), cm.getRecipes()[0].getName());
        assertSame(array[1].getName(), cm.getRecipes()[1].getName());
        assertSame(array[2].getName(), cm.getRecipes()[2].getName());
        assertSame(array[3].getName(), cm.getRecipes()[3].getName());
        verify(recipeBook, times(4)).getRecipes();
    }

    public void testCheckInventory() {
        when(inv.toString()).thenReturn("Hello world");
        assertEquals("Hello world", cm.checkInventory());
    }

}


