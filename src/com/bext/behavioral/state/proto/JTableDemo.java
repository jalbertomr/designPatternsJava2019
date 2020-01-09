package com.bext.behavioral.state.proto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JTableDemo implements ActionListener {
    private JFrame jFrame;
    private ConcreteTableModel miTableModel;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JButton jButtonNew, jButtonDelete;

    public JTableDemo(){
        jFrame = new JFrame("JFrame Demo");
        jFrame.setPreferredSize( new Dimension( 400, 400));
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout( new FlowLayout());
        miTableModel = new ConcreteTableModel();
        jTable = new JTable(miTableModel);
        jTable.setPreferredScrollableViewportSize(new Dimension( 300,300));
        jScrollPane = new JScrollPane( jTable);

        jButtonNew = new JButton("Nuevo Contacto");
        jButtonNew.addActionListener( this);

        jButtonDelete = new JButton("Borra Contacto");
        jButtonDelete.addActionListener( this);

        jFrame.getContentPane().add(jScrollPane);
        jFrame.getContentPane().add(jButtonNew);
        jFrame.getContentPane().add(jButtonDelete);
        jFrame.setVisible( true);
        jFrame.pack();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Nuevo Contacto")) {
            miTableModel.newContact();
        } else if (ae.getActionCommand().equals("Borra Contacto")) {
            miTableModel.deleteContact(jTable.getSelectedRow());
        }
    }

    public static void main(String[] args) {
	   SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               new JTableDemo();
           }
       });
    }
}

class ConcreteTableModel extends AbstractTableModel {
    private String[] cols = {"Nombre","Telefono"};
    ContactManager contactManager;

    public ConcreteTableModel() {
        contactManager = new ContactManager();
        contactManager.addContact("Jose Alberto", "1234554321");
        contactManager.addContact("Guille Tepa", "2233112233");
        contactManager.addContact("Vickus Monarque", "7766554433");
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
            contactManager. setContactName( row, (String) value);
        } else if ( col == 1) {
            contactManager.setContactTelefono( row, (String) value);
        }
        System.out.println("ConcreteTableModel setValueAt fireTableCellUpdate(row, col)" + row + "," + col);
        fireTableCellUpdated( row, col);
    }

    public boolean isCellEditable( int row, int col) {
        return true;
    }

    public void newContact() {
        contactManager.addContact("Nombre...", "telefono...");
        System.out.println("ConcreteTableModel newContact fireTableDataChanged()");
        fireTableDataChanged();
    }

    public void deleteContact(int index) {
        contactManager.deleteContact(index);
        System.out.println("ConcreteTableModel deleteContact fireTableDataChanged()");
        fireTableDataChanged();
    }
}

class ContactManager {
    private ArrayList<String> nombres;
    private ArrayList<String> telefonos;

    public ContactManager() {
        nombres = new ArrayList<>();
        telefonos = new ArrayList<>();
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
}