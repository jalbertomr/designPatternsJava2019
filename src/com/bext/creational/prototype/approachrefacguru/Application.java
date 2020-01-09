package com.bext.creational.prototype.approachrefacguru;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args){
        List<Shape> shapes = new ArrayList<>();
        List<Shape> copyOfShapes = new ArrayList<>();

        Circle circle = new Circle();
        circle.x = 100;
        circle.y = 200;
        circle.radius = 50;
        shapes.add( circle);

        Circle clonedCircle = circle.clone();
        shapes.add( clonedCircle);

        Rectangle rectangle = new Rectangle();
        rectangle.x = 120;
        rectangle.y = 80;
        rectangle.height = 50;
        rectangle.width = 60;
        rectangle.color = "white";
        shapes.add( rectangle);

        Rectangle clonedRectangle = rectangle.clone();
        shapes.add( clonedRectangle);

        shapes.stream().forEach( e -> System.out.println( e.toString()));

        for ( Shape shape : shapes) {
            copyOfShapes.add( shape);
        }
    }
}

//Prototype
abstract class Shape {
    int x;
    int y;
    String color;

    public Shape(){
        this.x = 0;
        this.y = 0;
        this.color = "blue";
    }

    public Shape( Shape source){
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
    }

    public Shape(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    abstract public Shape clone();

    @Override
    public String toString() {
        return "Shape{" +
                "x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                '}';
    }
}

class Rectangle extends Shape{
    int width;
    int height;

    public Rectangle(){
    }

    public Rectangle(Rectangle source) {
        super( source);
        this.width = source.width;
        this.height = source.height;
    }

    public Rectangle clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

class Circle extends Shape{
    int radius;

    public Circle() {
    }

    public Circle( Circle source) {
        super( source);
        this.radius = source.radius;
    }

    public Circle clone() {
        return new Circle( this );
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                ", radius=" + radius +
                '}';
    }
}

