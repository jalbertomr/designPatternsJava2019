package com.bext.creational.builder.approachrefacguru;

public class Application {
    public static void main(String... args) {
        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar( carBuilder);
        Car car = carBuilder.getProduct();

        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar( manualBuilder);
        Manual manual = manualBuilder.getProduct();

    }
}
    interface ICar{}

    class Car implements ICar{
    }

    class Manual implements ICar{
    }

    interface Builder {
        void reset();
        public ICar getProduct();
        public void setSeats(int seats);
        public void setPower(int power);
        public void setTripComputer(boolean tripComputer);
        public void setGPS(boolean GPS);
    }

    class CarBuilder implements Builder {
        private Car car;
        private int seats;
        private int power;
        private boolean tripComputer;
        private boolean GPS;

        public CarBuilder() {
            this.reset();
        }
        @Override
        public Car getProduct() {
            Car product = this.car;
            this.reset();
            return product;
        }
        @Override
        public void reset() { this.car = new Car();}
        @Override
        public void setSeats(int seats) {
            this.seats = seats;
        }
        @Override
        public void setPower(int power) {
            this.power = power;
        }
        @Override
        public void setTripComputer(boolean tripComputer) {
            this.tripComputer = tripComputer;
        }
        @Override
        public void setGPS(boolean GPS) {
            this.GPS = GPS;
        }
    }

    class CarManualBuilder implements Builder {
        private Manual manual;
        private int seats;
        private int power;
        private boolean tripComputer;
        private boolean GPS;

        public CarManualBuilder() {
            this.reset();
        }
        @Override
        public Manual getProduct() {
            Manual product = this.manual;
            this.reset();
            return product;
        }
        @Override
        public void reset() { this.manual = new Manual();}
        @Override
        public void setSeats(int seats) {
            this.seats = seats;
        }
        @Override
        public void setPower(int power) {
            this.power = power;
        }
        @Override
        public void setTripComputer(boolean tripComputer) {
            this.tripComputer = tripComputer;
        }
        @Override
        public void setGPS(boolean GPS) {
            this.GPS = GPS;
        }
    }

    class Director {
        private Builder builder;

        void setBuilder( Builder builder) {
            this.builder = builder;
        }

        void constructSportsCar(Builder builder){
            builder.reset();
            builder.setSeats(2);
            builder.setPower(1000);
            builder.setTripComputer(true);
            builder.setGPS(true);
        }

        void constructSUV(Builder builder){
            builder.reset();
            builder.setSeats(4);
            builder.setPower(600);
            builder.setTripComputer(true);
            builder.setGPS(true);
        }
    }

