package dev.lpa.domain.burger;

//All our meal items: burger, drink, and a side, will be an item. An item is called an inner class because it's not
//static, and it's declared as a class member.

import java.util.ArrayList;

//The code below is using inner classes for Bill's Burger Challenge.
public class Meal {

    private double base = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    //The constructor below constructs a regular meal, with a regular burger, a coke and a side of fries.
    public Meal(double conversionRate){
        this.conversionRate = conversionRate;
        burger = new Burger("good burger", "burger", 2.15);
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public double getTotal(){
        double total = burger.price + drink.price + side.price + burger.toppingsTotal;
        return Item.getPrice(total, conversionRate);
    }

    public void addToppings(String topping){

        switch (topping){
            case "lettuce" -> {
                burger.toppings.add(burger.lettuce);
                burger.toppingsTotal += burger.lettuce.price;
            }
            case "tomato" -> {
                burger.toppings.add(burger.tomato);
                burger.toppingsTotal += burger.tomato.price;
            }
            case "onions" -> {
                burger.toppings.add(burger.onions);
                burger.toppingsTotal += burger.onions.price;
            }
            case "pickles" -> {
                burger.toppings.add(burger.pickles);
                burger.toppingsTotal += burger.pickles.price;
            }
            case "cheese" -> {
                burger.toppings.add(burger.cheese);
                burger.toppingsTotal += burger.cheese.price;
            }
        }
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side,
                "Total Due", getTotal());
    }

    //A bonus for the nested class is that both the inner class and outer class have direct access to the other's
    //instance members.
    private class Item {
        private String name;
        private String type;
        private double price;

        //In the constructor below, we are able to assign base, the base price, a private field on the enclosing Meal
        //class, directly to an attribute on an instance of Item, if it's a burger. This shows that inner class has
        //direct access to the outer class's attributes, even private ones.

        //If we were to change Meal's double variable "base" to be "price" instead, we would get an error here on the
        //ternary operator for "base". Fixing that by changing it to "price" would give us an error, since there would
        //be a price field on both Meal and Item. If we were to reference "price" without any qualifier in the code, it
        //refers to the price applicable to the current scope. We can tell it to use Meal's price by adding specifiers:
        //Meal.this.price.
        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? base : 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }

        private static double getPrice(double price, double rate){
            return price * rate;
        }
    }

    private class Burger {
        private String name;
        private String type;
        private double price;
        private ArrayList<Item> toppings;
        private Item lettuce;
        private Item tomato;
        private Item pickles;
        private Item onions;
        private Item cheese;
        private double toppingsTotal;

        public Burger(String name, String type, double price) {
            this.lettuce = new Item("Lettuce", "Topping", .30);
            this.tomato = new Item("Tomato", "Topping", .40);
            this.pickles = new Item("Pickles", "Topping", .25);
            this.onions = new Item("Onions", "Topping", .30);
            this.cheese = new Item("Cheese", "Topping", .40);
            this.name = name;
            this.type = type;
            this.price = price;
            this.toppings = new ArrayList<>();

        }

        private static double getPrice(double price, double rate){
            return price * rate;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            int iterator = 0;
            for(Item toppings : toppings){
                iterator++;
                result.append(toppings);
                if(iterator == this.toppings.size()){
                    break;
                }
                result.append("\n");
            }
            System.out.printf("%10s%15s $%.2f%n ".formatted(type, name, getPrice(price, conversionRate)));
            return result.toString();
        }
    }

}

//The meal and items are tightly coupled in this challenge, because we've said that Bill sells meals, and not individual
//items.