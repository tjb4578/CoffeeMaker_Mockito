package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;

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
}
