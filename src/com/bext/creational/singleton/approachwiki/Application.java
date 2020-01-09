package com.bext.creational.singleton.approachwiki;

public class Application {
    public static void main(String... args){
        Singleton singletonObject = Singleton.getInstance();

        singletonObject.setSingletonAttribute("este valor es el mismo para todas las instancias de singleton object");

        System.out.println( singletonObject.getSingletonAttribute());

        Singleton otroSingleton = Singleton.getInstance();
        System.out.println( otroSingleton.getSingletonAttribute());

        singletonObject.setSingletonAttribute("Sigue siendo el mismo valor ahora actualizado para todas las instancias");
        System.out.println( singletonObject.getSingletonAttribute());
        System.out.println( otroSingleton.getSingletonAttribute());

        singletonObject = null;
        //System.out.println( singletonObject.getSingletonAttribute());  // null pointer exception
        System.out.println( otroSingleton.getSingletonAttribute());
    }

}

class Singleton {
    private static Singleton instance = new Singleton();
    private String singletonAttribute = "";

    private Singleton(){}
    public static Singleton getInstance() {
        return instance;
    }
    public String getSingletonAttribute() {
        return singletonAttribute;
    }
    public void setSingletonAttribute(String singletonAttribute) {
        this.singletonAttribute = singletonAttribute;
    }
}
