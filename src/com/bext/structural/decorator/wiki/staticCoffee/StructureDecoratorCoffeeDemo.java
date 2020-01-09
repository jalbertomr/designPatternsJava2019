package com.bext.structural.decorator.wiki.staticCoffee;

class structureDecoratorCoffeeDemo {
    public static void main(String... args) {
        ICoffee iCoffee = new SimpleCoffee();
        printInfo(iCoffee);

        iCoffee = new CoffeeWithMilk( iCoffee);
        printInfo(iCoffee);

        iCoffee = new CoffeeWithChocolate( iCoffee);
        printInfo(iCoffee);

        //OR
        ICoffee iCoffee2 = new CoffeeWithMilk( new CoffeeWithChocolate( new SimpleCoffee()));
        printInfo( iCoffee2);
    }

    private static void printInfo(ICoffee iCoffee) {
        System.out.println("Cost: " + iCoffee.getCost() + "; Ingredients: " + iCoffee.getIngredients());
    }
}

interface ICoffee {
    public double getCost();
    public String getIngredients();
}

class SimpleCoffee implements ICoffee {
    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}

abstract class CoffeeDecorator implements ICoffee {
    private final ICoffee decorateCoffee;

    public CoffeeDecorator(ICoffee coffee) {
        this.decorateCoffee = coffee;
    }

    @Override
    public double getCost() {
        return decorateCoffee.getCost();
    }

    @Override
    public String getIngredients() {
        return decorateCoffee.getIngredients();
    }
}

class CoffeeWithMilk extends CoffeeDecorator {

    public CoffeeWithMilk(ICoffee coffee) {
        super(coffee);
    }
    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }
}

class CoffeeWithChocolate extends CoffeeDecorator {

    public CoffeeWithChocolate(ICoffee coffee) {
        super(coffee);
    }
    @Override
    public double getCost() {
        return super.getCost() + 0.4;
    }
    @Override
    public String getIngredients() {
        return super.getIngredients() + ", Chocolate";
    }
}