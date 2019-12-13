package com.bext.creational.factorymethod.approachD;

public class ApproachD {
    Dialog dialog;

    void initialize() throws Exception {
        String config = "WINDOWS";

        switch (config.toLowerCase()){
            case "windows":
                this.dialog = new WindowsDialog(); break;
            case "Web":
                dialog = new WebDialog(); break;
            default: throw new Exception("Error Unknown Environment");
        }
    }

    public static void main(String... args) throws Exception {
        ApproachD app = new ApproachD();
        app.initialize();
        app.dialog.render();
    }
}

interface Button {
   public void render(int x, int y);
   public void onClick(String at);
}

class WindowsButton implements Button {
    public void render(int x, int y){
        System.out.println("render windowsButton on " + x +","+ y);
    }
    public void onClick(String at){
        System.out.println("onClick windowsButton " + at);
    }
}

class HTMLButton implements Button {
    public void render(int x, int y){
        System.out.println("render HTMLButton on " + x +","+ y);
    }
    public void onClick(String at){
        System.out.println("onClick HTMLButton " + at);
    }
}

abstract class Dialog {
    abstract protected Button createButton();
    void render() {
       Button okButton = createButton();
       okButton.onClick("closeDialog");
       okButton.render(10, 24);
    };
}

class WindowsDialog extends Dialog {
    @Override
    protected Button createButton() {
        System.out.println("Creating WindowsButton");
        return new WindowsButton();
    }
}

class WebDialog extends Dialog {
    @Override
    protected Button createButton() {
        System.out.println("Creating HTMLButton");
        return new HTMLButton();
    }
}