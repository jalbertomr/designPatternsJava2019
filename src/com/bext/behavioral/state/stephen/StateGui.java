package com.bext.behavioral.state.stephen;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class StateGui implements ActionListener {
    private JFrame mainFrame;
    private JPanel controlPanel, editPanel;
    private static CalendarEditor editor;
    private JButton save, exit;

    public StateGui(CalendarEditor editor) {
        System.out.println( "StateGui(editor): " + editor);
        this.editor = editor;
    }

    public void createGui() {
        mainFrame = new JFrame("State Pattern Example");
        Container container = mainFrame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        editPanel = new JPanel();
        editPanel.setLayout(new BorderLayout());
        //init appointments if apply

        if ( editor.getAppointments() == null) {
            Appointment appointment = new Appointment("reason", "contacts", "location", new Date(), new Date());
            editor.getAppointments()[1] = appointment;
        }
        //
        //JTable appointmentTable = new JTable(new StateTableModel( (Appointment[]) editor.getAppointments().toArray(new Appointment[1])));
        JTable appointmentTable = new JTable(new StateTableModel( editor.getAppointments()));
        editPanel.add(new JScrollPane(appointmentTable));
        container.add(editPanel);

        controlPanel = new JPanel();
        save = new JButton("Save Appointments");
        exit = new JButton( "Exit");
        controlPanel.add( save);
        controlPanel.add( exit);
        container.add( controlPanel);

        save.addActionListener( this);
        exit.addActionListener( this);

        mainFrame.addWindowListener( new WindowCloseManager());
        mainFrame.pack();
        mainFrame.setVisible( true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object originator = actionEvent.getSource();
        if (originator == save) {
            try {
                saveAppointments();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (originator == exit) {
            exitApplication();
        }
    }

    private class WindowCloseManager extends WindowAdapter {
        public void windowClosing(WindowEvent event){
            exitApplication();
        }
    }

    private void saveAppointments() throws IOException, InvocationTargetException, IllegalAccessException {
        System.out.println("StateGui.saveAppointments this.editor: " + this.editor);
        System.out.println("StateGui.saveAppointments editor:" + editor);
        System.out.println("editor.getCurrentState().info: " + editor.getCurrentState().info);
        System.out.println("this.editor.getCurrentState(): " + this.editor.getCurrentState());
        editor.save();
    }

    private void exitApplication() {
        System.exit(0);
    }

    private class StateTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Appointment","Contacts","Location","Start Date","End Date" };
        private Appointment[] data;

        public StateTableModel(Appointment[] appointments) {
            System.out.println("StateTableModel(appointments) appointments.length" + appointments.length);
            //appointments[0].setContacts("ContactInit");
            data = appointments;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public int getRowCount(){ return data.length;}

        public int getColumnCount(){ return columnNames.length;}

        public Object getValueAt( int row, int column) {
            Object value = null;
            switch(column){
                case 0: value = "ReasonOfAppointment"; //data[row].getReason(); "Reason";
                break;
                case 1: value = "MyContacts"; // data[row].getContacts();
                break;
                case 2: value = "WhereLocation"; //data[row].getLocation();
                break;
                case 3: value = "Date"; //data[row].getStartDate();
                    break;
                case 4: value = "Date"; // data[row].getEndDate();
                    break;
            }
            return value;
        }

        public boolean isCellEditable(int row, int column){
            return ((column == 0) || (column == 2)) ? true : false;
        }

        public void setValueAt(Object value, int row, int column){
            System.out.println("StateGUI setValueAt " + value);
            switch(column){
                case 0: data[row].setReason( (String) value);
                editor.edit();
                break;
                case 1: break;
                case 2: data[row].setLocation( (String) value);
                editor.edit();
                break;
                case 3: break;
                case 4: break;
            }
            fireTableCellUpdated(row, column);
        }
    }
}
