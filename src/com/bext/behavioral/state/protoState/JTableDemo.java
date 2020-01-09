package com.bext.behavioral.state.protoState;

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
    private ArrayList<String> nombres;
    private ArrayList<String> telefonos;

    public ContactManager() {
        nombres = new ArrayList<>();
        telefonos = new ArrayList<>();
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
        nombres.remove(index);
        nombres.add(index, nombre);
    }

    public void setContactTelefono( int index, String telefono) {
        telefonos.remove( index);
        telefonos.add( telefono);
    }

    public void addContact(String nombre, String telefono) {
        nombres.add(nombre);
        telefonos.add(telefono);
    }

    public void deleteContact(int index) {
        nombres.remove(index);
        telefonos.remove(index);
    }

    public String getNombre(int index) {
        return nombres.get(index);
    }

    public String getTelefono(int index) {
        return telefonos.get(index);
    }

    public int size() {
        return nombres.size();
    }

    public void edit() {
        currentState.edit();
    }

    public void save() throws IOException {
        currentState.save();
    }

    public void loadContacts(File file) throws IOException {
        DataInputStream dis = null;

        System.out.println("FileLoader loadData file.getAbsolutePath(): " + file.getAbsolutePath());
        try{
            int i = 0;
            dis = new DataInputStream( new BufferedInputStream( new FileInputStream( file)));
            while( dis.available() > 0) {
                String name = dis.readUTF();
                System.out.println("ContactManager.loadContacts() name" + name);
                setContactName( i, name);
                String telefono = dis.readUTF();
                System.out.println("ContactManager.loadContacts() telefono" + telefono);
                setContactTelefono( i , telefono);
                i++;
            }
        } catch (IOException  e) {
            e.printStackTrace();
        } finally {
            if ( dis != null) dis.close();
        }
    }

    public void storeContacts(File file) throws IOException {
        DataOutputStream dos = null;
        System.out.println("FileLoader storeData file.getAbsolutePath(): " + file.getAbsolutePath());

        try{
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream( file)));
            for ( int i =  0; i < nombres.size(); i++) {
                dos.writeBytes(nombres.get(i));
                dos.writeBytes(telefonos.get(i));
            }
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) dos.close();
        }
    }

    public void bulkContacts() {
        for ( int i = 0; i < nombres.size(); i++) {
            System.out.print( getNombre( i) + " " + getTelefono( i));
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