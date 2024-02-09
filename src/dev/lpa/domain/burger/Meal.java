package dev.lpa.domain.burger;

//All our meal items: burger, drink, and a side, will be an item. An item is called an inner class because it's not
//static, and it's declared as a class member.

//The code below is using inner classes for Bill's Burger Challenge.
public class Meal {

    private double base = 5.0;
    private Item burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    //The constructor below constructs a regular meal, with a regular burger, a coke and a side of fries.
    public Meal(double conversionRate){
        this.conversionRate = conversionRate;
        burger = new Item("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public double getTotal(){
        double total = burger.price + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }
    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f".formatted(burger, drink, side, "Total Due", getTotal());
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
}

//The meal and items are tightly coupled in this challenge, because we've said that Bill sells meals, and not individual
//items.