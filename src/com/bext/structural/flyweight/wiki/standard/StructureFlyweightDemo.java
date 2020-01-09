package com.bext.structural.flyweight.wiki.standard;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class StructureFlyweightDemo {
    public static void main(String... args) {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.takeOrder("Capuchino", 3);
        coffeeShop.takeOrder("Frappe", 7);
        coffeeShop.takeOrder("Expresso", 6);
        coffeeShop.takeOrder("Expresso", 1);
        coffeeShop.takeOrder("Capuchino", 6);
        coffeeShop.takeOrder("Frappe", 1);
        coffeeShop.takeOrder("Expresso", 1);
        coffeeShop.takeOrder("Frappe", 5);
        coffeeShop.takeOrder("Capuchino", 7);
        coffeeShop.takeOrder("Expresso", 2);
        coffeeShop.takeOrder("Capuchino", 2);
        coffeeShop.takeOrder("Frappe", 2);
        coffeeShop.takeOrder("Expresso", 1);
        coffeeShop.takeOrder("latte",6);

        coffeeShop.service();
        System.out.println("CoffeeFlavour.flavoursInCache(): " + CoffeeFlavour.flavoursInCache());
        System.out.println("dump CACHE"); CoffeeFlavour.dump();
        coffeeShop.dump();
    }
}

class CoffeeFlavour {
    private final String name;
    private static WeakHashMap<String, CoffeeFlavour> CACHE = new WeakHashMap<>();

    // only intern call it
    public CoffeeFlavour(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static CoffeeFlavour intern(String name) {
        synchronized (CACHE) {
            return CACHE.computeIfAbsent( name, CoffeeFlavour::new );
        }
    }

    public static int flavoursInCache() {
        synchronized (CACHE) {
            return CACHE.size();
        }
    }

    public static void dump() {
        CACHE.forEach( (a,b) -> System.out.println( a + b));
    }
}

@FunctionalInterface
interface Order {
    void serve();

    static Order create(String flavourName, int tableNumber) {
        CoffeeFlavour flavour = CoffeeFlavour.intern( flavourName);
        return () -> System.out.println("Serving " +  flavour + " to table " + tableNumber);
    }
}

class CoffeeShop {
    private final ArrayList<Order> orders = new ArrayList<>();

    public void takeOrder(String flavour, int tableNumber) {
        orders.add( Order.create( flavour, tableNumber));
    }

    public void service() {
        orders.forEach( Order::serve);
    }

    public void dump() {
        for (Order order: orders) {
            System.out.println(order.toString());
        }
    }
}