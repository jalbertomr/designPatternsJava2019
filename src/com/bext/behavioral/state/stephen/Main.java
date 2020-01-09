package com.bext.behavioral.state.stephen;

/*   This app need to be corrected
     Appoinment in CalendarEdiror must be a List
     StateGui must have buttons to add, delete
     a guide is in protoStateV2
 */

public class Main {
    public static void main(String... args) {
        CalendarEditor calendarEditor = new CalendarEditor();
        System.out.println( "main calendarEditor: " + calendarEditor);
        System.out.println( "main calendarEditor.getCurrentState().info: " + calendarEditor.getCurrentState().info);
        StateGui stateGui = new StateGui( calendarEditor);
        stateGui.createGui();
    }
}
