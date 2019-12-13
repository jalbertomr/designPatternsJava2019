package com.bext.creational.factorymethod.approachA;

public class ApproachA {

    public static void main(String ... args) {
       ShapeFactory shapeFactory = new ShapeFactory();
       Shape shapeA = shapeFactory.factoryMethod("CIRCLE");
       shapeA.draw();

       Shape shapeB = shapeFactory.factoryMethod("RECTANGLE");
       shapeB.draw();

       Shape shapeC = shapeFactory.factoryMethod("SQUARE");
       shapeC.draw();
    }
}

interface Shape {
    public void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle.");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle.");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drewing a Square.");
    }
}

class ShapeFactory {
    public Shape factoryMethod(String shapeToCreate) {
        if (shapeToCreate == null) return null;
        switch (shapeToCreate) {
            case "CIRCLE": return new Circle();
            case "RECTANGLE" : return new Rectangle();
            case "SQUARE" : return new Square();
            default: return null;
        }
    }
}
