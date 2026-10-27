package com.example;

import java.util.ArrayList;
import java.util.List;

public class Decorator {
    public static void main(String[] args) {
        // 1. Create a customer order
        Order order = new Order();

        // 2. Build a Burger with Cheese and Ketchup
        FoodItem customBurger = new Ketchup(new Cheese(new Burger()));
        order.addItem(customBurger);

        // 3. Build Fries with Cheese and Onions
        FoodItem customFries = new Onions(new Cheese(new Fries()));
        order.addItem(customFries);

        // 4. Print individual itemizations
        System.out.println("--- Order Itemization ---");
        for (FoodItem item : order.getItems()) {
            System.out.printf("%s -> $%.2f%n", item.getDescription(), item.getCost());
        }

        // 5. Calculate base order total
        double baseTotal = order.calculateTotalCost();
        System.out.printf("%nSubtotal: $%.2f%n", baseTotal);

        // 6. Apply a loyalty status discount
        LoyaltyStatus loyalty = new LoyaltyStatus(LoyaltyStatus.Status.GOLD);
        double finalTotal = loyalty.applyDiscount(baseTotal);
        
        System.out.printf("Final Total (Gold Status 15%% Discount Applied): $%.2f%n", finalTotal);
    }

    public interface FoodItem 
    {
        String getDescription();
        double getCost();
    }

    public static class Burger implements FoodItem 
    {
        @Override
        public String getDescription() 
        { 
            return "Burger"; 
        }

        @Override
        public double getCost() 
        { 
            return 5.50; 
        }
    }

    public static class Fries implements FoodItem 
    {
        @Override
        public String getDescription()
        { 
            return "Fries"; 
        }
        
        @Override
        public double getCost() 
        { 
            return 2.50; 
        }
    }

    public static class HotDog implements FoodItem 
    {
        @Override
        public String getDescription() 
        { 
            return "Hot Dog"; 
        }

        @Override
        public double getCost() 
        { 
            return 3.50; 
        }
    }

    public abstract static class ToppingDecorator implements FoodItem 
    {
        protected FoodItem tempFoodItem;

        protected ToppingDecorator(FoodItem newFoodItem) 
        {
            this.tempFoodItem = newFoodItem;
        }

        @Override
        public String getDescription() 
        {
            return tempFoodItem.getDescription();
        }

        @Override
        public double getCost() 
        {
            return tempFoodItem.getCost();
        }
    }

    public static class Cheese extends ToppingDecorator 
    {
        public Cheese(FoodItem newFoodItem) 
        { 
            super(newFoodItem); 
        }

        @Override
        public String getDescription() 
        { 
            return super.getDescription() + ", Cheese"; 
        }

        @Override
        public double getCost() 
        { 
            return super.getCost() + 0.75; 
        }
    }

    public static class Onions extends ToppingDecorator 
    {
        public Onions(FoodItem newFoodItem) 
        { 
            super(newFoodItem); 
        }

        @Override
        public String getDescription() 
        { 
            return super.getDescription() + ", Onions"; 
        }

        @Override
        public double getCost() 
        { 
            return super.getCost() + 0.40; 
        }
    }

    public static class Ketchup extends ToppingDecorator 
    {
        public Ketchup(FoodItem newFoodItem) 
        { 
            super(newFoodItem); 
        }

        @Override
        public String getDescription() 
        { 
            return super.getDescription() + ", Ketchup"; 
        }

        @Override
        public double getCost() 
        { 
            return super.getCost() + 0.25; 
        }
    }

    public static class Order 
    {
        private final List<FoodItem> items = new ArrayList<>();

        public void addItem(FoodItem item) 
        {
            items.add(item);
        }

        public List<FoodItem> getItems() 
        {
            return items;
        }

        public double calculateTotalCost() 
        {
            double total = 0.0;
            for (FoodItem item : items) 
            {
                total += item.getCost();
            }
            return total;
        }
    }

    public static class LoyaltyStatus 
    {
        public enum Status { REGULAR, SILVER, GOLD }

        private final Status status;

        public LoyaltyStatus(Status status) 
        {
            this.status = status;
        }

        public double applyDiscount(double currentTotal) 
        {
            switch (status) 
            {
                case GOLD:
                    return currentTotal * 0.85; // 15% discount
                case SILVER:
                    return currentTotal * 0.90; // 10% discount
                case REGULAR:
                default:
                    return currentTotal;        // No discount
            }
        }
    }
}
