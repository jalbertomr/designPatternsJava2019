package com.bext.structural.decorator.RefacGuru.staticFileDataSource;

public class StructureDecoratorFileDataSourceDemo {
    public static void main(String... args) {
        DataSource dataSource = new FileDataSource("fileName");

        dataSource.writeData("datosImportantes");
        System.out.println( dataSource.readData());

        DataSource dataSource1 = new EncryptionDecorator( dataSource);
        dataSource1.writeData("OtrosDatosImportantes");
        System.out.println( dataSource1.readData());

        DataSource dataSource2 = new CompressionDecorator( dataSource1);
        System.out.println( dataSource2.readData());

        DataSource dataSource3 = new CompressionDecorator( dataSource1);
        dataSource3.writeData("MasDatosImportantes");
        System.out.println( dataSource3.readData());
    }
}

interface DataSource {
    void writeData(String data);
    String readData();
}

class FileDataSource implements DataSource{
    String data;
    public FileDataSource(String fileName) {
    }

    @Override
    public void writeData(String data) {
        this.data = data;
    }

    @Override
    public String readData() {
        return this.data;
    }
}

class DataSourceDecorator implements DataSource {
    private final DataSource dataSource;

    DataSourceDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeData(String data) {
        dataSource.writeData(data);
    }

    @Override
    public String readData() {
        return dataSource.readData();
    }
}

class EncryptionDecorator extends DataSourceDecorator {

    EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        super.writeData( "Encrypted[" + data + "]");
    }

    @Override
    public String readData() {
        return "Decrypted[" + super.readData() + "]";
    }
}

class CompressionDecorator extends DataSourceDecorator {

    CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        super.writeData( "Compressed[" + data + "]");
    }

    @Override
    public String readData() {
        return "Decompressed[" + super.readData() + "]";
    }

}
