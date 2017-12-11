package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryTest extends TestCase {

    private Inventory inventory;

    protected void setUp(){
        inventory = new Inventory();
    }

    public void testAddChocolate(){
        try {
            inventory.addChocolate("5");
        } catch(InventoryException e) {

        }

        assertEquals(20,inventory.getChocolate());
    }

    public void testAddCoffee(){
        try {
            inventory.addCoffee("5");
        } catch(InventoryException e) {

        }

        assertEquals(20,inventory.getCoffee());
    }

    public void testAddMilk(){
        try {
            inventory.addMilk("5");
        } catch(InventoryException e) {

        }

        assertEquals(20,inventory.getMilk());
    }

    public void testAddSugar(){
        try {
            inventory.addSugar("5");
        } catch(InventoryException e) {

        }

        assertEquals(20,inventory.getSugar());
    }

    public void testUseIngredients(){
        Recipe r1 = mock(Recipe.class);
        when(r1.getAmtChocolate()).thenReturn(10);
        when(r1.getAmtCoffee()).thenReturn(10);
        when(r1.getAmtMilk()).thenReturn(10);
        when(r1.getAmtSugar()).thenReturn(10);

        assertTrue(inventory.useIngredients(r1));
    }

    public void testToString(){
        String s1 = inventory.toString();

        //Checks to see if toString shows the amount of coffee
        assertTrue(s1.contains("Coffee: 15"));

        //Checks to see if toString shows the amount of chocolate
        assertTrue(s1.contains("Chocolate: 15"));

        //Checks to see if toString shows the amount of milk
        assertTrue(s1.contains("Milk: 15"));

        //Checks to see if toString shows the amount of sugar
        assertTrue(s1.contains("Sugar: 15"));
    }
}
