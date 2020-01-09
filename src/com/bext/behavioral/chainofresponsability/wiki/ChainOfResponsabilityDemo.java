package com.bext.behavioral.chainofresponsability.wiki;

public class ChainOfResponsabilityDemo implements Logger{
    public static void main(String... args) {
        // build an inmutable chain of responsability
        Logger logger = Logger.consoleLogger( Logger.LogLevel.all())
                .appendNext( Logger.emailLogger( Logger.LogLevel.FUNCTIONAL_MESSAGE, Logger.LogLevel.FUNCTIONAL_ERROR))
                .appendNext( Logger.fileLogger( Logger.LogLevel.WARNING, Logger.LogLevel.ERROR));

        // handled by consoleLogger since the console has the loglevel of all
        logger.message("IN function processOrder().", Logger.LogLevel.DEBUG);
        logger.message("Order record retrieved.", Logger.LogLevel.INFO);

        // handled by consoleLogger and fileLogger since fileLogger implements Warning & Error
        logger.message("Customer Address details missing in Branch DataBase", Logger.LogLevel.WARNING);
        logger.message("Customer Address details missing in Organization DataBase.", Logger.LogLevel.ERROR);

        // handeld by consoleLogger and emailLogger since emailLogger implements functional error
        logger.message("Unable to process order 23RE92 Dated 32/21322 for customer Cust922", Logger.LogLevel.FUNCTIONAL_ERROR);

        // handled by consoleLogger and emailLogger
        logger.message("Order Dispatched.", Logger.LogLevel.FUNCTIONAL_MESSAGE);

    }

    @Override
    public void message(String msg, LogLevel severity) {

    }

}
