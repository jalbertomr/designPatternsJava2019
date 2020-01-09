package com.bext.behavioral.mediator.geeksforgeeks;

interface IATCMediator {
    public void registerRunway(Runway runway);
    public void registerFlight(Flight flight);
    public boolean isLandingOK();
    public void setLandingStatus(boolean status);
}

class ATCMediator implements IATCMediator {
    private Flight flight;
    private Runway runway;
    private boolean land;


    @Override
    public void registerRunway(Runway runway) {
        this.runway = runway;
    }

    @Override
    public void registerFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean isLandingOK() {
        return land;
    }

    @Override
    public void setLandingStatus(boolean landStatus) {
        this.land = landStatus;
    }
}

interface Command {
    void land();
}

class Flight implements Command {
    private IATCMediator atcMediator;

    public Flight(IATCMediator iatcMediator) {
        this.atcMediator = iatcMediator;
    }

    @Override
    public void land() {
        if (atcMediator.isLandingOK()) {
            System.out.println("Successfully Landed.");
            atcMediator.setLandingStatus( true);
        } else {
            System.out.println("Waiting for landing...");
        }
    }

    public void getReady() {
        System.out.println("Flight.getReady()");
    }
}

class Runway implements Command {
    private IATCMediator atcMediator;

    public Runway(IATCMediator atcMediator) {
        this.atcMediator = atcMediator;
        atcMediator.setLandingStatus( true);
    }

    @Override
    public void land() {
        System.out.println("Landing permission granted.");
    }
}
public class behavioralMediatorDemo {
    public static void main(String... args) {
        ATCMediator atcMediator =  new ATCMediator();
        Flight flight33 = new Flight(atcMediator);
        Runway runway11N = new Runway(atcMediator);
        atcMediator.registerFlight( flight33);
        atcMediator.registerRunway( runway11N);
        flight33.getReady();
        runway11N.land();
        flight33.land();
    }
}
