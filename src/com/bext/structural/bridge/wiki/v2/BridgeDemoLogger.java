 package com.bext.structural.bridge.wiki.v2;

public class BridgeDemoLogger {
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
            return message -> System.out.println("INFO: " + message );
        }
        static Logger warning() {
            sendWarningEmail();
            return message -> System.out.println("WARNING: " + message );
        }
        static Logger error() {
            sendErrorSMS();
            return message -> System.out.println("ERROR: " + message );
        }

        private static void sendWarningEmail() { /*...*/}
        private static void sendErrorSMS() {/*...*/}
    }

    static abstract class AbstractAccount {
        private Logger logger = Logger.info();

        public void setLogger(Logger logger) {
            this.logger = logger;
        }

        protected void log(String message, boolean result) {
            logger.log( message + " result " + result);
        }
    }

    static class SimpleAccount extends AbstractAccount {
        private int balance;
        Logger logger = Logger.info();

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
            logger.log("withdraw " + amount);
        }

        public void setLogger(Logger logger) {
            this.logger = logger;
        }
    }
}
