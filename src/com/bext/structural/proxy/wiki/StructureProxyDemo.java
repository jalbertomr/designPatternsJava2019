package com.bext.structural.proxy.wiki;

public class StructureProxyDemo {
    public static void main(String... args) {
        Image image1 = new ProxyImage("HiRes_10MB_Photo1");
        Image image2 = new ProxyImage("HiRes_10MB_Photo2");

        image1.displayImage();
        image1.displayImage();
        image2.displayImage();
        image2.displayImage();
        image1.displayImage();
    }

    interface Image {
        public void displayImage();
    }

    static class RealImage implements Image {
        private final String finalname;

        public RealImage(String finalname) {
            this.finalname = finalname;
            loadImageFromDisk();
        }
        @Override
        public void displayImage() {
            System.out.println("Displaying..." + finalname);
        }

        private void loadImageFromDisk() {
            System.out.println("Loading... " + finalname);
        }
    }

    static class ProxyImage implements Image {
        private final String filename;
        private RealImage image;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void displayImage() {
            if (image == null){
                image = new RealImage( filename);
            }
            image.displayImage();
        }
    }
}
