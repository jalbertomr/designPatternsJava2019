package com.bext.structural.adapter.approachwiki;

public class AdapterStringDemo {
    public static void main(String... args){
        ClassLowerTrimA classLowerTrimA = new ClassLowerTrimA();
        ClassLowerTrimB classLowerTrimB = new ClassLowerTrimB();
        ClassUpperA classUpperA = new ClassUpperA();
        ClassUpperB classUpperB = new ClassUpperB();
        ClassATypeUpper classATypeUpper = new ClassATypeUpper();
        ClassBTypeUpper classBTypeUpper = new ClassBTypeUpper();

        classLowerTrimA.setStringData( classLowerTrimB.getStringData());  // simple no need to adapt
        classLowerTrimA.setStringData( classUpperA.getStringData());      // data is not on format
        // adapt, formatted through a child class with format Adapter using a Wrapper form.
        classLowerTrimA.setStringData( new FormatClassUpperAtoClassLowerTrimA().getStringData());

        // Adapt, formatted through a Class that receives the source class to be adapted
        classLowerTrimA.setStringData( new ClassUpperAFormatToClassLowerTrimA( classUpperA).getStringData());
        // Adapt, formatted in Json through a Class that receives the source class to be adapted
        classLowerTrimA.setStringData( new ClassUpperAFormatToClassLowerTrimAInJson( classUpperA).getStringData());

        // Adapt, formatted through a class ClassAtoformat -> Format -> ClassAfomated using Interfaces Providers
        // this way no need to create an specific Class, just another method name.
        classLowerTrimA.setStringData(  new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperA).getStringData() );
        classLowerTrimA.setStringData(  new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperA).getStringDataJson());
        /* Specific ClassUpperAFormatToClassLowerTrimwithProvider can not accept classUpperB, must be generalized (with interface ITypeClassUpper)
        classLowerTrimA.setStringData(  new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperB).getStringData() );
        classLowerTrimA.setStringData(  new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperB).getStringDataJson());
        */
        // Adapt, formatted through a generic class input typeUpper, The return Type is String, very generic, no need to change in this case
        classLowerTrimA.setStringData( new ClassTypeUpperFormatToClassLowerTrimA( classATypeUpper).getStringData());
        classLowerTrimA.setStringData( new ClassTypeUpperFormatToClassLowerTrimA( classBTypeUpper).getStringData());
        classLowerTrimB.setStringData( new ClassTypeUpperFormatToClassLowerTrimA( classATypeUpper).getStringData());
        // specifying provider by casting, redundant.
        classLowerTrimA.setStringData( ((StringProvider) new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperA)).getStringData() );
        System.out.println( classLowerTrimA.getStringData());
        classLowerTrimA.setStringData( ((StringJsonProvider) new ClassUpperAFormatToClassLowerTrimAwithProvider( classUpperA)).getStringDataJson() );
        System.out.println( classLowerTrimA.getStringData());

        // formatted through de adapter
        Adapter adapter = new ClassUpperAFormatToClassLowerTrimAAdapter();
        classLowerTrimA.setStringData( ((StringProvider) adapter.adapt( classUpperA)).getStringData()
        );
        classLowerTrimA.setStringData( ((StringProvider)(new ClassUpperAFormatToClassLowerTrimAAdapter()).adapt( classUpperA)).getStringData()
                                );

        classLowerTrimA.setStringData( ((StringJsonProvider) adapter.adapt( classUpperA)).getStringDataJson());

    }

    static class ClassLowerTrimA {
        private String stringData;

        public String getStringData() { return this.stringData;  }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }

    static class ClassLowerTrimB {
        private String stringData = "abcde";

        public String getStringData() { return this.stringData; }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }

    static class ClassUpperA {
        private String stringData = "ABCDE";
        public String getStringData() { return this.stringData; }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }

    static class ClassUpperB {
        private String stringData = "BBBBB";
        public String getStringData() { return this.stringData; }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }
    // formatted through a child class with format, Adapter using a Wrapper form
    static class FormatClassUpperAtoClassLowerTrimA extends ClassUpperA{
        public String getStringData() {
            return formatToClassLowerTrimA( super.getStringData());
        }

        private String formatToClassLowerTrimA( final String sourceData) {
            return sourceData.toLowerCase().trim();
        }
    }

    //**********************************************************************************
    static public class ClassUpperAFormatToClassLowerTrimA {
        private ClassUpperA classUpperA = null;

        public ClassUpperAFormatToClassLowerTrimA(final ClassUpperA classUpperA) {
            this.classUpperA = classUpperA;
        }

        public String getStringData() {
            return formatToClassLowerTrimA( classUpperA.getStringData());
        }

        private String formatToClassLowerTrimA(final String sourceData) {
            return sourceData.toLowerCase().trim();
        }
    }

    static public class ClassUpperAFormatToClassLowerTrimAInJson {
        private ClassUpperA classUpperA = null;

        public ClassUpperAFormatToClassLowerTrimAInJson(final ClassUpperA classUpperA) {
            this.classUpperA = classUpperA;
        }

        public String getStringData() {
            return formatToClassLowerTrimAJson( classUpperA.getStringData());
        }

        private String formatToClassLowerTrimAJson( final String sourceData) {
            return formatToJson( sourceData.toLowerCase().trim());
        }

        private String formatToJson(final String sourceData){
            return "'{ \"stringData\":\"" + sourceData + "\" }'";
        }

    }
    //***************************************************************************************

    public interface StringProvider {
        public String getStringData();
    }

    public interface StringJsonProvider {
        public String getStringDataJson();
    }

    static public class ClassUpperAFormatToClassLowerTrimAwithProvider implements StringProvider, StringJsonProvider {
        private ClassUpperA classUpperA = null;

        public ClassUpperAFormatToClassLowerTrimAwithProvider(final ClassUpperA classUpperA) {
            this.classUpperA = classUpperA;
        }

        @Override
        public String getStringData() {
            return formatToClassLowerTrimA( classUpperA.getStringData());
        }

        @Override
        public String getStringDataJson() {
            return formatToClassLowerTrimAJson( classUpperA.getStringData());
        }

        private String formatToClassLowerTrimA(final String sourceData) {
            return sourceData.toLowerCase().trim();
        }

        private String formatToClassLowerTrimAJson( final String sourceData) {
            return formatToJson( sourceData.toLowerCase().trim());
        }

        private String formatToJson(final String sourceData){
            return "'{ \"stringData\":\"" + sourceData + "\" }'";
        }
    }

    static abstract class Adapter {
        abstract public Object adapt(final Object anObject);
    }

    static public class ClassUpperAFormatToClassLowerTrimAAdapter extends Adapter{
        @Override
        public Object adapt(Object anObject) {
            return  new ClassUpperAFormatToClassLowerTrimAwithProvider( (ClassUpperA) anObject);
        }
    }

    // Generalizing ClassUpper classes ******************************************************
    interface ITypeUpper {
        public String getStringData();
        public void setStringData(String stringData);
    }

    static class ClassATypeUpper implements ITypeUpper {
        private String stringData = "ABCDE";
        public String getStringData() { return this.stringData; }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }

    static class ClassBTypeUpper implements ITypeUpper {
        private String stringData = "BBBBB";
        public String getStringData() { return this.stringData; }
        public void setStringData(String stringData) { this.stringData = stringData; }
    }

    //**********************************************************************************
    static public class ClassTypeUpperFormatToClassLowerTrimA {
        private ITypeUpper iTypeUpper = null;

        public ClassTypeUpperFormatToClassLowerTrimA(final ITypeUpper iTypeUpper) {
            this.iTypeUpper = iTypeUpper;
        }

        public String getStringData() {
            return formatToClassLowerTrimA( iTypeUpper.getStringData());
        }

        private String formatToClassLowerTrimA(final String sourceData) {
            return sourceData.toLowerCase().trim();
        }
    }

    static public class ClassTypeUpperFormatToClassLowerTrimAInJson {
        private ITypeUpper iTypeUpper = null;

        public ClassTypeUpperFormatToClassLowerTrimAInJson(final ITypeUpper iTypeUpper) {
            this.iTypeUpper = iTypeUpper;
        }

        public String getStringData() {
            return formatToClassLowerTrimAJson( iTypeUpper.getStringData());
        }

        private String formatToClassLowerTrimAJson( final String sourceData) {
            return formatToJson( sourceData.toLowerCase().trim());
        }

        private String formatToJson(final String sourceData){
            return "'{ \"stringData\":\"" + sourceData + "\" }'";
        }

    }
    //***************************************************************************************


}
