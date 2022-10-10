package designPatterns.chainOfResponsibility.log;

public class ChainPatternDemo {
    public static void main(String[] args) {
        AbstractLogger chainOfLoggers = getChainOfLoggers();
        chainOfLoggers.logMessage(AbstractLogger.INFO, "Hello~");
        System.out.println("---------------------------------------");
        chainOfLoggers.logMessage(AbstractLogger.ERROR, "World~");

    }


    private static AbstractLogger getChainOfLoggers() {
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        consoleLogger.setNextLogger(errorLogger);
        return consoleLogger;
    }
}
