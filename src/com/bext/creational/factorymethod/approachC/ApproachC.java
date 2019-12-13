package com.bext.creational.factorymethod.approachC;

public class ApproachC {

    public static void main(String... args) {
       Creator creator = new ConcreteCreatorA();
       creator.doSomethingAboutCreation();
       Product productA = creator.createProduct();
       productA.setAttributeX();
       creator = new ConcreteCreatorB();
       creator.doSomethingAboutCreation();
       Product product = creator.createProduct();
       product.setAttributeX();
    }
}

interface Product {
   public void setAttributeX();
   public void setAttributeY();
}

class ConcreteProductA implements Product {
    public void setAttributeX(){
        System.out.println("setting attribute X on ProductA");
    }
    public void setAttributeY(){
        System.out.println("setting attribute Y on ProductA");
    }
}

class ConcreteProductB implements Product {
    public void setAttributeX(){
        System.out.println("setting attribute X on ProductB");
    }
    public void setAttributeY(){
        System.out.println("setting attribute Y on ProductA");
    }
}

abstract class Creator {
    abstract protected Product createProduct();
    abstract void doSomethingAboutCreation();
}

class ConcreteCreatorA extends Creator {
    @Override
    protected Product createProduct() {
        System.out.println("Creating ProductA");
        return new ConcreteProductA();
    }
    @Override
    void doSomethingAboutCreation() {
        System.out.println("someOperation Relative to creation A");
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    protected Product createProduct() {
        System.out.println("Creating ProductB");
        return new ConcreteProductB();
    }
    @Override
    void doSomethingAboutCreation() {
        System.out.println("someOperation Relative to creation B");
    }
}