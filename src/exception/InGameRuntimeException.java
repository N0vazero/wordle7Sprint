package exception;

public class InGameRuntimeException extends RuntimeException {
    public InGameRuntimeException(String message) {
        super(message);
        LogWriter.writeToFile(this);
        printError();
    }

    private void printError() {
        System.out.println("Внутренняя ошибка!");
    }
}
