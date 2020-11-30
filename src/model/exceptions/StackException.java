package model.exceptions;

public class StackException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // constrói um objeto StackException com a mensagem passada por parâmetro
    public StackException(String msg) {
        super(msg);
    }

    // constrói um objeto StackException com a mensagem e a causa da exceção
    public StackException(String msg, Throwable cause) {
        super(msg, cause);
    }
}