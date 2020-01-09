 package com.bext.structural.bridge.wiki.v1;

public class BridgeDemoLogger {
    public static void main(String... args){
        Logger logInfo = new LoggerInfo();
        Logger logWarning = new LoggerWarning();
        Logger logError = new LoggerError();

        SimpleAccount account = new SimpleAccount(100);
        account.setLogger( logInfo);
        account.withdraw(75);

        if ( account.isBalanceLow()){
            account.setLogger( logWarning);
        }

        account.withdraw(10);
        account.withdraw(100);
    }

    interface Logger {
        void log(String message, boolean shouldPreform);
    }

    static class LoggerInfo implements Logger {
        @Override
        public void log(String message, boolean shouldPerform) {
            System.out.println("INFO: " + message + " " + shouldPerform);
        }
    }

    static class LoggerWarning implements Logger {
        @Override
        public void log(String message, boolean shouldPerform) {
            System.out.println("WARNING: " + message + " " + shouldPerform);
            sendWarningEmail();
        }
        private void sendWarningEmail() { /*...*/}
    }

    static class LoggerError implements Logger {
        @Override
        public void log(String message, boolean shouldPerform) {
            System.out.println("ERROR: " + message + " " + shouldPerform);
            sendErrorSMS();
        }
        private void sendErrorSMS() {/*...*/}
    }

    static class SimpleAccount  {
        private int balance;
        Logger logger;

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
            logger.log("withdraw " + amount, shouldPreform);
        }

        public void setLogger(Logger logger) {
            this.logger = logger;
        }
    }
}
