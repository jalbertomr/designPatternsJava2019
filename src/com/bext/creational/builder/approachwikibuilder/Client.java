package com.bext.creational.builder.approachwikibuilder;

public class Client {

    public static void main(String... args) {
        FerrariBuilder builder = new FerrariBuilder();
        SportCarBuildDirector director = new SportCarBuildDirector(builder);

        director.construct();
        Car miRaceCar = builder.getResult();
        System.out.println(miRaceCar.toString());
    }
}

    class Car {
        private String Make;
        private String Model;
        private int NumDoors;
        private String color;

        public Car(String make, String model, int numDoors, String color) {
            Make = make;
            Model = model;
            NumDoors = numDoors;
            this.color = color;
        }

        public String getMake() { return Make; }
        public void setMake(String make) { Make = make; }
        public String getModel() { return Model; }
        public void setModel(String model) { Model = model; }
        public int getNumDoors() { return NumDoors;  }
        public void setNumDoors(int numDoors) { NumDoors = numDoors; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        @Override
        public String toString() {
            return "Car{" +
                    "Make='" + Make + '\'' +
                    ", Model='" + Model + '\'' +
                    ", NumDoors=" + NumDoors +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    interface ICarBuilder {
        public int getNumDoors();
        public void setNumDoors(int numDoors);
        public String getColor();
        public void setColor(String color);
        Car getResult();
    }

    class FerrariBuilder implements ICarBuilder {
        private int numDoors;
        private String color;

        public int getNumDoors() { return this.numDoors; }
        public void setNumDoors(int numDoors) { this.numDoors =numDoors;}
        public String getColor() { return this.color; }
        public void setColor(String color) { this.color = color;}
        public Car getResult() {
            return  numDoors == 2 ? new Car("Ferrari", "488 Spider", numDoors, color) : null;
        }
    }

    class SportCarBuildDirector {
        private ICarBuilder iCarBuilder;

        public SportCarBuildDirector(ICarBuilder iCarBuilder) {
            this.iCarBuilder = iCarBuilder;
        }
        public void construct() {
            iCarBuilder.setColor("Red");
            iCarBuilder.setNumDoors(2);
        }
    }

