package com.bext.behavioral.command.refacguru;

import java.util.Stack;

class Editor {
    String text;

    public String getSelection() {
        return "Selected text. ";
    }

    public void deleteSelection() {
        System.out.println("delete Selected text");
    }

    public void replaceSelection(String text) {
        System.out.println("Replace selected Text");
    }
}

abstract class Command {
    protected Application app;
    protected Editor editor;
    protected String backup;

    public Command(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    void saveBackup() {
        backup = editor.text;
    }

    void undo() {
        editor.text = backup;
    }

    abstract boolean execute();
}

class CopyCommand extends Command {

    public CopyCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        app.clipboard = editor.getSelection();
        return false;
    }
}

class CutCommand extends Command {

    public CutCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        saveBackup();
        app.clipboard = editor.getSelection();
        editor.deleteSelection();
        return true;
    }
}

class PasteCommand extends Command {

    public PasteCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        saveBackup();
        editor.replaceSelection(app.clipboard);
        return true;
    }
}

class UndoCommand extends Command {

    public UndoCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    boolean execute() {
        app.undo();
        return false;
    }
}

class CommandHistory {
    private Stack<Command> commandStack;

    Command push(Command command) {
        return commandStack.push(command);
    }

    Command pop() {
        return commandStack.pop();
    }
}

class Application {
    String clipboard;
    private Editor activeEditor;
    private Editor[] editors;
    private CommandHistory history;

    void createUI() {
        //... create gui and assign commands to keys

        /*
        shortcuts.onKeyPress("Ctrl+C", new CopyCommand( this, activeEditor));
        shortcuts.onKeyPress("Ctrl+X", new CopyCommand( this, activeEditor));
        shortcuts.onKeyPress("Ctrl+V", new CopyCommand( this, activeEditor));
        shortcuts.onKeyPress("Ctrl+Z", new CopyCommand( this, activeEditor));

         */
    }

    void executeCommand(Command command) {
        if (command.execute())
            history.push(command);
    }

    void undo() {
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}

public class BehaviorCommandGUIDemo {
    public static void main(String... args) {

    }
}
