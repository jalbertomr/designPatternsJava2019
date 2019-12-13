package com.bext.creational.abstractfactory.approachA;

public class AbstractFactoryA {
    public static void main(String ... args) {
       AbstractFactory abstractFactoryNormal = FactoryProducer.createFactory("normal");
       Shape shape = abstractFactoryNormal.createShape("Rectangle");
       shape.draw();
       Shape shape1 = abstractFactoryNormal.createShape("Square");
       shape1.draw();
       AbstractFactory abstractFactoryRounded = FactoryProducer.createFactory("rounded");
       Shape shape2 = abstractFactoryRounded.createShape("rectangle");
       shape2.draw();
       Shape shape3 = abstractFactoryRounded.createShape("square");
       shape3.draw();

    }
}

interface Shape {
    public void draw();
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("drawing Rectangle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("drawing Square");
    }
}

class RoundedRectangle implements Shape {
    public void draw() {
        System.out.println("drawing RoundedRectangle");
    }
}

class RoundedSquare implements Shape {
    public void draw() {
        System.out.println("drawing RoundedSquare");
    }
}

abstract class AbstractFactory {
    abstract Shape createShape(String shape);
}

class ShapeFactory extends AbstractFactory {
    @Override
    Shape createShape(String shape) {
        switch (shape.toLowerCase()){
            case "rectangle": return new Rectangle();
            case "square": return new Square();
            default : return null;
        }
    }
}

class RoundedShapeFactory extends AbstractFactory{
    @Override
    Shape createShape(String shape) {
        switch (shape.toLowerCase()){
            case "rectangle": return new RoundedRectangle();
            case "square": return new RoundedSquare();
            default : return null;
        }
    }
}

class FactoryProducer {
    public static AbstractFactory createFactory(String style){
        switch(style.toLowerCase()){
            case "rounded": return new RoundedShapeFactory();
            default: return new ShapeFactory();
        }
    }
}