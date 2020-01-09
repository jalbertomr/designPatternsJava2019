package com.bext.structural.adapter.approachwiki;

public class AdapterSimpleBatteryDemo {

    public static void main(String... args) {
        Battery5V battery5VSource = new Battery5V();
        Battery5V battery5VToCharge = new Battery5V();

        battery5VToCharge.setVoltage( battery5VSource.getVoltage());
    }


}

class Battery5V {
    int voltage = 5;
    public void setVoltage( int voltage) {
        if (voltage != 5)
            this.voltage = voltage;
    }
    public int getVoltage() {
        return this.voltage;
    }
}

class Battery12V {
    int voltage = 12;
    public void setVoltage( int voltage) {
        this.voltage = voltage;
    }
    public int getVoltage() {return this.voltage;}
}

