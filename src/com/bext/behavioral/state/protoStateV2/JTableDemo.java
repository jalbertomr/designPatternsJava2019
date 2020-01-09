package com.bext.behavioral.state.protoStateV2;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class JTableDemo implements ActionListener {
    private JFrame jFrame;
    private ConcreteTableModel miTableModel;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JButton jButtonNew, jButtonDelete, jButtonSave, jButtonExit;

    public JTableDemo() throws IOException {
        jFrame = new JFrame("JFrame Demo");
        jFrame.setPreferredSize( new Dimension( 400, 400));
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout( new FlowLayout());
        miTableModel = new ConcreteTableModel();
        jTable = new JTable( miTableModel);
        jTable.setPreferredScrollableViewportSize(new Dimension( 300,300));
        jScrollPane = new JScrollPane( jTable);

        jButtonNew = new JButton("Nuevo");
        jButtonNew.addActionListener( this);

        jButtonDelete = new JButton("Borrar");
        jButtonDelete.addActionListener( this);

        jButtonSave = new JButton("Guardar");
        jButtonSave.addActionListener( this);

        jButtonExit = new JButton("Salir");
        jButtonExit.addActionListener( this);

        jFrame.getContentPane().add(jScrollPane);
        jFrame.getContentPane().add(jButtonNew);
        jFrame.getContentPane().add(jButtonDelete);
        jFrame.getContentPane().add(jButtonSave);
        jFrame.getContentPane().add(jButtonExit);
        jFrame.setVisible( true);
        jFrame.pack();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Nuevo")) {
            miTableModel.newContact();
        } else if (ae.getActionCommand().equals("Borrar")) {
            miTableModel.deleteContact(jTable.getSelectedRow());
        } else if (ae.getActionCommand().equals("Guardar")){
            try {
                guardarContacto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (ae.getActionCommand().equals("Salir")){
            salir();
        }
    }

    private void guardarContacto() throws IOException {
        System.out.println("JTableDemo guardarContacto()");
        miTableModel.contactManager.save();
        miTableModel.contactManager.changeState("CLEAN");
    }

    private void salir() {
        System.exit(0);
    }

    public static void main(String[] args) {
	   SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               try {
                   new JTableDemo();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
    }
}

class ConcreteTableModel extends AbstractTableModel {
    private String[] cols = {"Nombre","Telefono"};
    ContactManager contactManager;

    public ConcreteTableModel() throws IOException {
        String pathname = "D:/temp/contacts.app";
        File file = new File(pathname);

        contactManager = new ContactManager();
        contactManager.loadContacts( file);

        if (contactManager.size() == 0) {
            contactManager.addContact("Jose Alberto", "1234554321");
            contactManager.addContact("Guille Tepa", "2233112233");
            contactManager.addContact("Vickus Monarque", "7766554433");
        }
    }

    @Override
    public int getRowCount() {
        return contactManager.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0){
            return contactManager.getNombre(rowIndex);
        } else if (columnIndex == 1) {
            return contactManager.getTelefono(rowIndex);
        }
        return null;
    }

    public String getColumnName(int c) {
        return cols[c];
    }

    public void setValueAt(Object value, int row, int col) {
        if (col == 0) {
            contactManager.setContactName( row, (String) value);
        } else if ( col == 1) {
            contactManager.setContactTelefono( row, (String) value);
        }
        System.out.println("ConcreteTableModel setValueAt fireTableCellUpdate(row, col)" + row + "," + col);
        contactManager.changeState( "DIRTY" );
        fireTableCellUpdated( row, col);
    }

    public boolean isCellEditable( int row, int col) {
        return true;
    }

    public void newContact() {
        contactManager.addContact("Nombre...", "telefono...");
        contactManager.changeState( "DIRTY" );
        System.out.println("ConcreteTableModel newContact fireTableDataChanged()");
        fireTableDataChanged();
    }

    public void deleteContact(int index) {
        contactManager.deleteContact(index);
        contactManager.changeState( "DIRTY" );
        System.out.println("ConcreteTableModel deleteContact fireTableDataChanged()");
        fireTableDataChanged();
    }
}

class ContactManager {
    private State currentState;
    private State dirtyState;
    private State cleanState;
    private Contacts contacts;

    public ContactManager() {
        contacts = new Contacts();
        cleanState = new CleanState();
        dirtyState = new DirtyState( cleanState);
        this.currentState = cleanState;
    }


    public void changeState(String nombreState) {
        switch (nombreState){
            case "DIRTY": this.currentState = dirtyState; break;
            case "CLEAN": this.currentState = cleanState; break;
            default:
                System.out.println("Estado no registrado");
        }
    }

    public void setContactName( int index, String nombre) {
        Contact contact = null;
        if ( (contact = contacts.get( index)) != null) {
            String telefono = contact.getTelefono();
            contacts.remove( index);
            contacts.add( index, new Contact(nombre, telefono));
        } else {
            contacts.remove( index);
            contacts.add( index, new Contact(nombre, ""));
        }
    }

    public void setContactTelefono( int index, String telefono) {
        Contact contact = null;
        if ( (contact = contacts.get( index)) != null) {
            String nombre = contact.getNombre();
            contacts.remove( index);
            contacts.add( index, new Contact(nombre, telefono));
        } else {
            contacts.remove( index);
            contacts.add( index, new Contact("", telefono));
        }
    }

    public void addContact(String nombre, String telefono) {
        contacts.add( new Contact( nombre, telefono));
    }

    public void deleteContact(int index) {
        contacts.remove( index);
    }

    public String getNombre(int index) {
        return contacts.get( index).getNombre();
    }

    public String getTelefono(int index) {
        return contacts.get( index).getTelefono();
    }

    public int size() {
        return contacts.size();
    }

    public void edit() {
        currentState.edit();
    }

    public void save() throws IOException {
        currentState.save();
    }

    public void loadContacts(File file) throws IOException {
        System.out.println("loadContacts() file.getAbsolutePath(): " + file.getAbsolutePath());

        try {
            FileInputStream fis = new FileInputStream( file);
            ObjectInputStream ois = new ObjectInputStream( fis);
            contacts = (Contacts) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void storeContacts(File file) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutputStream);
            objectOutputStream.writeObject( contacts);
            objectOutputStream.close();
            System.out.println("Object to file written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bulkContacts() {
        for ( int i = 0; i < contacts.size(); i++) {
            System.out.print( contacts.get(i).getNombre() + " " + contacts.get( i).getTelefono());
        }
    }

    private class DirtyState extends State {
        private State nextState;

        public DirtyState(State nextState) {
            this.nextState = nextState;
        }

        @Override
        public void edit() {
            System.out.println("DirtyState edit()");
        }

        @Override
        public void save() throws IOException {
            String pathname = "D:/temp/contacts.app";
            File file = new File(pathname);
            System.out.println("DirtyState save()");
            storeContacts( file);
            bulkContacts();
        }
    }

    private class CleanState extends State {
        private State nextState;

        public CleanState() {
            this.nextState = new DirtyState( this);
        }

        @Override
        public void edit() {
            System.out.println("CleanState edit()");
        }

        @Override
        public void save() throws IOException {
            System.out.println("CleanState save() no need to save");
        }
    }
}