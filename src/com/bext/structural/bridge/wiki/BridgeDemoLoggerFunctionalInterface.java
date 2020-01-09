package com.bext.structural.bridge.wiki;

public class BridgeDemoLoggerFunctionalInterface {
    public static void main(String... args) {
        SimpleAccount account = new SimpleAccount(100);
        account.withdraw(75);

        if ( account.isBalanceLow()){
            account.setLogger( Logger.warning());
        }

        account.withdraw(10);
        account.withdraw(100);
    }

    @FunctionalInterface
    interface Logger {
        void log(String message);

        static Logger info() {
            return message -> System.out.println("INFO: " + message);
        }
        static Logger warning() {
            return message -> System.out.println("WARNING: " + message);
        }
    }

    static abstract class AbstractAccount {
        private Logger logger = Logger.info();

        public void setLogger(Logger logger) {
            this.logger = logger;
        }

        protected void operate(String message, boolean result) {
            logger.log( message + " result " + result);
        }
    }

    static class SimpleAccount extends AbstractAccount {
        private int balance;

        public SimpleAccount(int balance){
            this.balance = balance;
        }

        public boolean isBalanceLow() {
            return balance < 50;
        }

        public void withdraw(int amount) {
            boolean shouldPreform = balance >= amount;

            if (shouldPreform) {
                balance -= amount;
            }
            operate("withdraw " + amount, shouldPreform);
        }
    }
}
