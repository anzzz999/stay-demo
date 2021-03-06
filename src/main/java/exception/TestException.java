package exception;

public class TestException extends RuntimeException  {

    public TestException(){
        super();
    }

    public TestException(String message){
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
