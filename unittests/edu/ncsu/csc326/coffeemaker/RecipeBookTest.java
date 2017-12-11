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

        assertTrue(recipeBook.addRecipe(r1));
        Recipe[] recipeBookContents = recipeBook.getRecipes();

        assertEquals(r1,recipeBookContents[0]);
    }

    public void testDeleteRecipe(){

        when(r1.getName()).thenReturn("recipe_1");

        recipeBook.addRecipe(r1);

        assertEquals("recipe_1", recipeBook.deleteRecipe(0));

        verify(r1).getName();
    }

    public void testEditRecipe(){

        Recipe r2 = mock(Recipe.class);

        when(r2.getName()).thenReturn("recipe_2");

        recipeBook.addRecipe(r1);
        assertEquals("recipe_1",recipeBook.editRecipe(0,r2));
        verify(r1).getName();

        assertEquals("recipe_2", recipeBook.deleteRecipe(0));
        verify(r2).getName();
    }


}
