package dev.lpa.domain.burger;

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();
//        System.out.println(regularMeal);

        Meal USRegularMeal = new Meal(0.68);
//        System.out.println(USRegularMeal);

        System.out.println();

        USRegularMeal.addToppings("lettuce");
        USRegularMeal.addToppings("onions");
        USRegularMeal.addToppings("cheese");
        System.out.println(USRegularMeal);

    }
}
