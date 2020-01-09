package com.bext.behavioral.state.stephen;

import java.io.*;
import java.util.ArrayList;

public class FileLoader {
    public static Appointment[] loadData(File file) throws IOException {
        ObjectInputStream ois = null;
        Appointment[] Appointments = new Appointment[1];

        System.out.println("FileLoader loadData file.getAbsolutePath(): " + file.getAbsolutePath());
        try{
            ois = new ObjectInputStream( new BufferedInputStream( new FileInputStream( file)));
            Appointments = (Appointment[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if ( ois != null) ois.close();
        }
        return Appointments;
    }

    public static void storeData( File file, Appointment[] Appointments) throws IOException {
        ObjectOutputStream oos = null;
        System.out.println("FileLoader storeData file.getAbsolutePath(): " + file.getAbsolutePath());

        try{
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream( file)));
            oos.writeObject( Appointments);
            oos.flush();
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) oos.close();
        }
    }
}
