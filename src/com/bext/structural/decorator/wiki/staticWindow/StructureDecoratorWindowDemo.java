package com.bext.structural.decorator.wiki.staticWindow;

public class StructureDecoratorWindowDemo {
    public static void main(String... args){
        Window decoratedWindow = new HorizontalScrollBarDecorator( new VerticalScrollBarDecorator( new SimpleWindow()));

        System.out.println( decoratedWindow.getDescription());
    }

    interface Window{
        void draw();
        String getDescription();
    }

    static class SimpleWindow implements Window {

        @Override
        public void draw() {
            // ...
        }

        @Override
        public String getDescription() {
            return "SimpleWindow";
        }
    }

    abstract static class WindowDecorator implements Window {
        private final Window windowToDecorate;

        protected WindowDecorator(Window windowToDecorate) {
            this.windowToDecorate = windowToDecorate;
        }

        public void draw(){
            windowToDecorate.draw();  //Delegation
        };

        public String getDescription() {
            return windowToDecorate.getDescription(); //Delegation
        };
    }

    static class VerticalScrollBarDecorator extends WindowDecorator {

        protected VerticalScrollBarDecorator(Window windowToDecorate) {
            super(windowToDecorate);
        }

        @Override
        public void draw() {
            super.draw();
            drawVerticalScrollBar();
        }

        private void drawVerticalScrollBar(){
           // draw vertical scroll bar
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", include Vertical Scroll bar.";
        }
    }

    static class HorizontalScrollBarDecorator extends WindowDecorator {
        protected HorizontalScrollBarDecorator(Window windowToDecorate) {
            super(windowToDecorate);
        }

        @Override
        public void draw() {
            super.draw();
            drawHorizontalScrollBar();
        }

        private void drawHorizontalScrollBar() {
            // draw horizontal scroll bar
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", include Horizontal Scroll bar.";
        }
    }


}
