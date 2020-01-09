package com.bext.structural.facade.refacguru;

import java.io.File;
import java.nio.Buffer;

public class FacadeVideoConverterDemo {
    public static void main(String... args){
        VideoConverter videoConverter = new VideoConverter();
        File mp4file  = videoConverter.convert("miVideo.ogg","mp4" );
        System.out.println( mp4file.toString());
    }

    static class VideoFile extends File {
        String filename;

        public VideoFile(String filename) {
            super(filename);
            this.filename = filename;
            System.out.println("VideoFile :" + filename);
        }
    }

    static abstract class Codec {
        private String type;
        public abstract String getType();
        public abstract void processor();
    }

    static class OggCompressionCodec extends Codec {
        public OggCompressionCodec() { super.type = "OGG";
        }
        @Override
        public String getType() { return super.type; }
        @Override
        public void processor() {
            System.out.println("Codec Ogg processing...");
        }
    }

    static class MPEG4CompressionCodec extends Codec {
        public MPEG4CompressionCodec() {super.type = "MP4";
        }
        @Override
        public String getType() { return super.type; }
        @Override
        public void processor() {
            System.out.println("Codec MP4 processing...");
        }
    }

    static class CodecFactory {
       String extractFile( File file){
           String codedIn = file.getName().substring(
                   file.getName().indexOf(".") + 1,
                   file.getName().length()
           );
           System.out.println("CodecFactory().extractFile: " + file + " [" + codedIn + "]");  //supposing OGG
           return codedIn;
       }
    }

    static class BitrateReader {
        public static String read(String filename, String codec){
            System.out.println("BitrateReader.read filename, codec : " + filename + ", " + codec);
            return "buffer de datos de "+ filename + " " + codec;
        }
        public static String convert( String buffer, Codec codec) {
            System.out.println("BitrateReader.convert buffer, codec : " + buffer + ", " + codec.getType());
            codec.processor();
            return "result from convert" + buffer + " to " + codec.getType();
        }
    }

    static class AudioMixer {
        public String fix( String result) {
            System.out.println("AudioMixer.fixing: " + result);
            return "AudioMix fixed: " + result;
        }
    }

    static class VideoConverter { // FACADE
        String filename;
        Codec destinationCodec;
        String buffer;
        String result;
        public File convert( String filename, String format) {
            this.filename = filename;
            File file = new VideoFile( filename);
            String sourceCodec =  new CodecFactory().extractFile( file);
            if (format.equals("mp4")){
                destinationCodec = new MPEG4CompressionCodec();
            } else {
                destinationCodec = new OggCompressionCodec();
            }
            buffer = BitrateReader.read(this.filename, sourceCodec);
            result = BitrateReader.convert( buffer, destinationCodec);
            result = new AudioMixer().fix(result);
            return new File("converted." + destinationCodec.toString());
        }
    }
}


