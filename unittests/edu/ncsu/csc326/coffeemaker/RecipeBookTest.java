package edu.ncsu.csc326.coffeemaker;


import junit.framework.TestCase;

import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class RecipeBookTest extends TestCase {

    private RecipeBook recipeBook;
    private Recipe r1;

    public void setUp(){
        recipeBook = new RecipeBook();

        r1 = mock(Recipe.class);
        when(r1.getName()).thenReturn("recipe_1");
    }

    public void testAddRecipe(){

        //Test to see if you get a positive response from adding a new recipe
        assertTrue(recipeBook.addRecipe(r1));
        Recipe[] recipeBookContents = recipeBook.getRecipes();

        //Check to make sure the added recipe appears in the recipeBook
        assertEquals(r1,recipeBookContents[0]);

        //Check to make sure that adding a duplicate recipe fails
        assertFalse(recipeBook.addRecipe(r1));

    }

    public void testDeleteRecipe(){

        when(r1.getName()).thenReturn("recipe_1");

        recipeBook.addRecipe(r1);

        //Check that deleting a recipe returns its name
        assertEquals("recipe_1", recipeBook.deleteRecipe(0));

        verify(r1).getName();

        //Check that deleting a non-existent recipe returns null
        assertEquals(null,recipeBook.deleteRecipe(1));
    }

    public void testEditRecipe(){

        Recipe r2 = mock(Recipe.class);

        when(r2.getName()).thenReturn("recipe_2");

        recipeBook.addRecipe(r1);
        //Check that
        assertEquals("recipe_1",recipeBook.editRecipe(0,r2));
        verify(r1).getName();

        //Check that editing a non-existent recipe returns null
        assertEquals(null,recipeBook.editRecipe(1, r2));
    }


}
