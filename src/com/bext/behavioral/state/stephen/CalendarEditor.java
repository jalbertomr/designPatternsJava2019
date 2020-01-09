package com.bext.behavioral.state.stephen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CalendarEditor {
    private State currentState;
    private File appointmentFile;
    private Appointment[] appointments;
    private static final String DEFAULT_APPOINTMENT_FILE = "D:/temp/appointments.app";

    public CalendarEditor() {
        //System.out.println("CalendarEditor()");
        this(DEFAULT_APPOINTMENT_FILE);
    }

    public CalendarEditor(String appointmentFilename) {
        System.out.println("IN CalendarEditor("+ appointmentFilename + ")" );

        appointmentFile = new File(appointmentFilename);
        System.out.println("CalendarEditor(...) appointmentFile.getAbsolutePath(): " + appointmentFile.getAbsolutePath());
        try {
            appointments = FileLoader.loadData(appointmentFile);
            System.out.println( "appointments.length: " + appointments.length);
        } catch (IOException e) {
            System.err.println("No se pudo cargar notas de calendario del archivo.");
            //e.printStackTrace();
        }
        this.currentState = new CleanState();
        System.out.println("OUT CalendarEditor("+ appointmentFilename + ")" + " this.currentState.info: " + this.currentState.info);
    }

    public State getCurrentState() {
        return currentState;
    }

    public void save() throws IOException {
        System.out.println("CalendarEditor.save() this.currentState.info " + this.currentState.info);
        if (this.currentState == null) System.out.println("this.currentState is null");
        this.currentState.save();
    }

    public void edit() {
        System.out.println("CalendarEditor.edit() this.currentState.info " + this.currentState.info);
        this.currentState.edit();
    }

    private class DirtyState extends State {
        private State nextState;

        public DirtyState(State nextState) {
            System.out.println("IN DirtyState( " + nextState.info + ")");
            super.info = "DirtyState";
            this.nextState = nextState;
            System.out.println("OUT DirtyState( " + nextState.info + ") this.nextState: " + this.nextState.info);
        }

        @Override
        public void edit() {
            System.out.println("DirtyState.edit()");
        }

        @Override
        public void save() throws IOException {
            System.out.println("DirtyState.save()");
            FileLoader.storeData( appointmentFile, appointments);
            currentState = this.nextState;
        }
    }

    private class CleanState extends State {
        private State nextState;// = new DirtyState( this);

        public CleanState() {
            System.out.println("IN CleanState()");
            super.info = "CleanState";
            this.nextState = new DirtyState(this);
            System.out.println("OUT CleanState()" + " this.nextState: " + this.nextState.info);
        }

        @Override
        public void edit() {
            System.out.println("CleanState.edit();");
            currentState = this.nextState;
        }

        @Override
        public void save() throws IOException {
            System.out.println("CleanState.save() no need to save");
        }
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public void addAppointment(int index, Appointment appointment) {
        appointments[index] = appointment;
    }

    public void removeAppointment(int index) {
        appointments[1] = null;
    }
}

