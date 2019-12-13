package com.bext.creational.factorymethod.approachB;

public class ApproachB {

    public static void main(String ... args) {
       ShapeFactory shapeFactory = new ShapeFactory();
       Circle circle = (Circle) shapeFactory.methodFactory("CIRCLE");
       circle.setColor("white");
       circle.draw();

       Rectangle rectangle = (Rectangle) shapeFactory.methodFactory("RECTANGLE");
       rectangle.setColor("blue");
       rectangle.draw();

       Square square = (Square) shapeFactory.methodFactory("SQUARE");
       square.setColor("green");
       square.draw();
    }
}

interface Shape {
    public void draw();
}

abstract class AbstractShape implements Shape {
    private String color = "";

    public String getColor() { return color; }
    public void setColor(String color) {
        this.color = color;
        System.out.println("setting color: " + color);
    }
}

class Circle extends AbstractShape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle.");
    }
}

class Rectangle extends AbstractShape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle.");
    }
}

class Square extends AbstractShape {
    @Override
    public void draw() {
        System.out.println("Drewing a Square.");
    }
}

class ShapeFactory {
    public Shape methodFactory(String shapeToCreate) {
        if (shapeToCreate == null) return null;
        switch (shapeToCreate) {
            case "CIRCLE": return new Circle();
            case "RECTANGLE" : return new Rectangle();
            case "SQUARE" : return new Square();
            default: return null;
        }
    }
}
