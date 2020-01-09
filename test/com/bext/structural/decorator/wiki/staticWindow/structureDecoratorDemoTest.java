package com.bext.structural.decorator.wiki.staticWindow;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

class structureDecoratorDemoTest {
    @Test
    public void teststructureDecoratorDemoTest() {
        StructureDecoratorWindowDemo.Window decoratedWindow = new StructureDecoratorWindowDemo.HorizontalScrollBarDecorator(
                new StructureDecoratorWindowDemo.VerticalScrollBarDecorator( new StructureDecoratorWindowDemo.SimpleWindow()));
                assertEquals("SimpleWindow, include Vertical Scroll bar., include Horizontal Scroll bar.", decoratedWindow.getDescription());

    }
}