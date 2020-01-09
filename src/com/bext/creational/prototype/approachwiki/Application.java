package com.bext.creational.prototype.approachwiki;

public class Application {
    public static void main(String... args) throws CloneNotSupportedException {
        ConcretePrototype1 concretePrototype1 = new ConcretePrototype1();
        Object objectCloned = concretePrototype1.clone();
        System.out.println("original: " +concretePrototype1.toString());
        System.out.println("cloned: " + objectCloned.toString());
    }
}

// qualify this public if is in file apart
abstract class Prototype implements Cloneable {
    String prototypeAttribute = "In Prototype initialized";

    public Prototype clone() throws CloneNotSupportedException{
        return (Prototype) super.clone();
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "prototypeAttribute='" + prototypeAttribute + '\'' +
                '}';
    }
}

class ConcretePrototype1 extends Prototype {
    String concretePrototypeAttribute = "In ConcretePrototype1 initialized";

    public Prototype clone() throws CloneNotSupportedException{
        return (ConcretePrototype1) super.clone();
    }

    @Override
    public String toString() {
        return "ConcretePrototype1{" +
                "prototypeAttribute='" + prototypeAttribute + '\'' +
                ", concretePrototypeAttribute='" + concretePrototypeAttribute + '\'' +
                '}';
    }
}

class ConcretePrototype2 extends Prototype {

    public Prototype clone() throws CloneNotSupportedException{
        return (ConcretePrototype2) super.clone();
    }
}
