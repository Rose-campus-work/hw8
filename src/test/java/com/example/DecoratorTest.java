package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.example.Decorator.Burger;
import com.example.Decorator.Cheese;
import com.example.Decorator.FoodItem;
import com.example.Decorator.Fries;
import com.example.Decorator.HotDog;
import com.example.Decorator.Ketchup;
import com.example.Decorator.LoyaltyStatus;
import com.example.Decorator.Onions;
import com.example.Decorator.Order;

public class DecoratorTest 
{
    @Test
    public void testBaseComponents() 
    {
        FoodItem burger = new Burger();
        assertEquals("Burger", burger.getDescription());
        assertEquals(5.50, burger.getCost(), 0.001);

        FoodItem fries = new Fries();
        assertEquals("Fries", fries.getDescription());
        assertEquals(2.50, fries.getCost(), 0.001);
    }

    @Test
    public void testSingleToppingDecorator() 
    {
        FoodItem cheeseBurger = new Cheese(new Burger());
        assertEquals("Burger, Cheese", cheeseBurger.getDescription());
        assertEquals(6.25, cheeseBurger.getCost(), 0.001); // 5.50 + 0.75
    }

    @Test
    public void testMultipleToppingsDecorator() 
    {
        FoodItem fullyLoadedHotDog = new Ketchup(new Onions(new Cheese(new HotDog())));
        assertEquals("Hot Dog, Cheese, Onions, Ketchup", fullyLoadedHotDog.getDescription());
        assertEquals(4.90, fullyLoadedHotDog.getCost(), 0.001); // 3.50 + 0.75 + 0.40 + 0.25
    }

    @Test
    public void testOrderTotalCalculation() 
    {
        Order order = new Order();
        order.addItem(new Cheese(new Burger())); // 6.25
        order.addItem(new Fries());              // 2.50

        assertEquals(8.75, order.calculateTotalCost(), 0.001);
    }

    @Test
    public void testLoyaltyDiscounts() 
    {
        double subtotal = 100.00;

        LoyaltyStatus regular = new LoyaltyStatus(LoyaltyStatus.Status.REGULAR);
        assertEquals(100.00, regular.applyDiscount(subtotal), 0.001);

        LoyaltyStatus silver = new LoyaltyStatus(LoyaltyStatus.Status.SILVER);
        assertEquals(90.00, silver.applyDiscount(subtotal), 0.001);

        LoyaltyStatus gold = new LoyaltyStatus(LoyaltyStatus.Status.GOLD);
        assertEquals(85.00, gold.applyDiscount(subtotal), 0.001);
    }
}
