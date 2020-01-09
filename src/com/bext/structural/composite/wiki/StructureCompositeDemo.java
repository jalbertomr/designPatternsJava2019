package com.bext.structural.composite.wiki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StructureCompositeDemo {
    public static void main(String... args) {
        Ellipse ellipse1 = new Ellipse();
        Ellipse ellipse2 = new Ellipse();
        Ellipse ellipse3 = new Ellipse();
        Ellipse ellipse4 = new Ellipse();

        // creates two composites containing ellipses
        CompositeGraphics compositeGraphics2 = new CompositeGraphics();
        compositeGraphics2.add( ellipse1);
        compositeGraphics2.add( ellipse2);
        compositeGraphics2.add( ellipse3);

        CompositeGraphics compositeGraphics3 = new CompositeGraphics();
        compositeGraphics3.add( ellipse4);

        // creates another composite that contains two composites.
        CompositeGraphics compositeGraphics1 = new CompositeGraphics();
        compositeGraphics1.add( compositeGraphics2);
        compositeGraphics1.add( compositeGraphics3);

        compositeGraphics1.print();
    }

    // Component
    interface Graphics {
        void print();
    }

    // Composite
    static class CompositeGraphics implements Graphics{
        // Collection of child graphics
        private final List<Graphics> listGraphics = new ArrayList<>();

        public void add(Graphics graphics){
            listGraphics.add(graphics);
        }

        @Override
        public void print(){
            for (Graphics graphic: listGraphics){
                graphic.print();
            }
        }
    }

    // Leaf
    static class Ellipse implements Graphics {
        @Override
        public void print() {
            System.out.println("Ellipse");
        }
    }
}
